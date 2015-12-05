package com.poison.zmeika.engine.pool;

import java.util.concurrent.*;

public final class BoundedBlockingPool < T >
        extends AbstractPool < T >
        implements BlockingPool < T >
{
    private int initialSize;

    private BlockingQueue < T > objects;

    private Validator < T > validator;
    private ObjectFactory < T > objectFactory;

    private ExecutorService executor =
            Executors.newCachedThreadPool();

    private volatile boolean shutdownCalled;

    private int maxCapacity = 0;

    public BoundedBlockingPool(
            int size,
            Validator < T > validator,
            ObjectFactory < T > objectFactory)
    {
        super();

        this.objectFactory = objectFactory;
        this.initialSize = size;
        this.validator = validator;

        objects = new LinkedBlockingQueue< T >(size);

        initializeObjects();

        shutdownCalled = false;
    }

    public T get()
    {
        if(!shutdownCalled)
        {
            T t = null;
            if(objects.size() > maxCapacity){
                maxCapacity = objects.size();
                System.out.println("Capacity grew up to " + maxCapacity);
            }

            try
            {
                if(objects.remainingCapacity() == 0){
                    t = objectFactory.createNew();
                    return t;
                }else{
                    t = objects.take();
                }
            }
            catch(InterruptedException ie)
            {
                Thread.currentThread().interrupt();
            }

            return t;
        }

        throw new IllegalStateException(
                "Object pool is already shutdown");
    }

    public void shutdown()
    {
        shutdownCalled = true;

        executor.shutdownNow();

        clearResources();
    }

    private void clearResources()
    {
        for(T t : objects)
        {
            validator.invalidate(t);
        }
    }

    @Override
    protected void returnToPool(T t)
    {
        if(validator.isValid(t))
        {
            executor.submit(new ObjectReturner(objects, t));
        }
    }

    @Override
    protected void handleInvalidReturn(T t)
    {

    }

    @Override
    protected boolean isValid(T t)
    {
        return validator.isValid(t);
    }

    private void initializeObjects() {
        for (int i = 0; i < initialSize; i++) {
            objects.add(objectFactory.createNew());
        }
    }

    private class ObjectReturner < E >
            implements Callable< Void >
    {
        private BlockingQueue < E > queue;
        private E e;

        public ObjectReturner(BlockingQueue< E > queue, E e)
        {
            this.queue = queue;
            this.e = e;
        }

        public Void call()
        {
            while(true)
            {
                try
                {
                    queue.put(e);
                    break;
                }
                catch(InterruptedException ie)
                {
                    Thread.currentThread().interrupt();
                }
            }

            return null;
        }
    }
}
