package com.poison.zmeika.engine.collections.impl;

import com.poison.zmeika.engine.collections.IQueueElement;

/**
 *
 */
public class QueueElement<T> implements IQueueElement<T> {
    T data;
    IQueueElement<T> prev;
    IQueueElement<T> next;

    @Override
    public IQueueElement<T> getNext() {
        return next;
    }

    @Override
    public IQueueElement<T> getPrev() {
        return prev;
    }

    @Override
    public void setNext(IQueueElement<T> element) {
        this.next = element;
    }

    @Override
    public void setPrev(IQueueElement<T> element) {
        this.prev = element;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
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
