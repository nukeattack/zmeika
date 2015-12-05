package com.poison.zmeika.engine.pool;

import com.poison.zmeika.game.model.ICleanable;

import java.util.LinkedList;
import java.util.Queue;

public final class ObjectPool< T >
        extends AbstractPool < T >
        implements BlockingPool < T >
{
    private Queue< T > objects;

    private ObjectFactory < T > objectFactory;

    private volatile boolean shutdownCalled;

    private int maxCapacity = 0;

    public ObjectPool(
            ObjectFactory < T > objectFactory)
    {
        super();

        this.objectFactory = objectFactory;

        objects = new LinkedList<T>();

        shutdownCalled = false;
    }

    public T get()
    {
        if(!shutdownCalled) {
            T t = null;
            synchronized (objects) {
                if (objects.size() > maxCapacity) {
                    maxCapacity = objects.size();
                    System.out.println("Capacity grew up to " + maxCapacity);
                }

                if (objects.size() == 0) {
                    t = objectFactory.createNew();
                } else {
                    t = objects.poll();
                }
            }
            return t;
        }
        throw new RuntimeException("Blocking pool shut down");
    }


    public void shutdown()
    {
        shutdownCalled = true;
        clearResources();
    }

    private void clearResources()
    {
        synchronized (objects){
            objects.clear();
        }
    }

    @Override
    protected void returnToPool(T t)
    {
        synchronized (objects){
            if(t instanceof ICleanable){
                ((ICleanable) t).clean();
            }
            objects.add(t);
        }
    }
}
