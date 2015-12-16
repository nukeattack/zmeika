package com.poison.zmeika.engine.collections.impl;

import com.poison.zmeika.engine.collections.IQueue;
import com.poison.zmeika.engine.collections.IQueueElement;
import com.poison.zmeika.engine.pool.ObjectPool;

/**
 *
 */
public class SimplePooledQueue <T> implements IQueue<T> {

    private ObjectPool<IQueueElement<T>> elementPool;
    private IQueueElement<T> last;

    @Override
    public T get() {
        if(last == null){
            return null;
        }
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
        IQueueElement<T> prev = last != null ? last : null;
        queueItem.setData(element);
        queueItem.setPrev(prev);
        queueItem.setNext(null);
        last = queueItem;
    }

    public void setElementPool(ObjectPool<IQueueElement<T>> elementPool) {
        this.elementPool = elementPool;
    }
}
