package com.poison.zmeika.engine.collections.impl;

import com.poison.zmeika.engine.collections.CollectionIterator;
import com.poison.zmeika.engine.collections.IQueue;
import com.poison.zmeika.engine.pool.SimpleObjectPool;

/**
 *
 */
public class SimplePooledQueue <T> implements IQueue<T> {
    private SimpleObjectPool<CollectionIterator> elementPool;
    private CollectionIterator last;

    @Override
    public T get() {
    	CollectionIterator queueItem = last;
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
    	CollectionIterator queueItem = elementPool.get();
        queueItem.setData(element);
        queueItem.setPrev(last);
        queueItem.setNext(null);
        if(last != null){
        	last.setNext(queueItem);
        }
        last = queueItem;
    }

    public void setElementPool(SimpleObjectPool<CollectionIterator> elementPool) {
        this.elementPool = elementPool;
    }
}
