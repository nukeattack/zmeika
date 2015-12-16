package com.poison.zmeika.engine.collections.impl;

import com.poison.zmeika.engine.collections.IQueue;
import com.poison.zmeika.engine.collections.IQueueElement;
import com.poison.zmeika.engine.pool.SimpleObjectPool;

/**
 *
 */
public class SimplePooledQueue <T> implements IQueue<T> {

    private SimpleObjectPool<IQueueElement<T>> elementPool;
    private IQueueElement<T> last;

    @Override
    public T get() {
        IQueueElement<T> queueItem = last;
        last = queueItem.getPrev();
        if(last != null){
            last.setNext(null);
        }
        T result = queueItem.getData();
        elementPool.release(queueItem);
        return result;
    }

    @Override
    public void put(T element) {
        IQueueElement<T> queueItem = elementPool.get();
        queueItem.setData(element);
        queueItem.setPrev(last);
        queueItem.setNext(null);
        if(last != null){
        	last.setNext(queueItem);
        }
        last = queueItem;
    }

    public void setElementPool(SimpleObjectPool<IQueueElement<T>> elementPool) {
        this.elementPool = elementPool;
    }
}
