package com.poison.zmeika.engine.collections;

import com.poison.zmeika.engine.pool.IPoolable;

/**
 *
 */
public class CollectionIterator implements IPoolable {
    Object data;
    CollectionIterator prev;
    CollectionIterator next;

    public CollectionIterator getNext() {
        return next;
    }

    public CollectionIterator getPrev() {
        return prev;
    }

    public void setNext(CollectionIterator element) {
        this.next = element;
    }

    public void setPrev(CollectionIterator element) {
        this.prev = element;
    }

    @SuppressWarnings("unchecked")
	public <T>T getData() {
        return (T)data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public void setup() {
        prev = null;
        next = null;
        data = null;
    }

    @Override
    public void cleanup() {
        prev = null;
        next = null;
        data = null;
    }
}
