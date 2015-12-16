package com.poison.zmeika.engine.collections;

import com.poison.zmeika.engine.pool.IPoolable;

/**
 *
 */
public interface IQueueElement<T> extends IPoolable {
    IQueueElement getNext();
    IQueueElement getPrev();
    void setNext(IQueueElement<T> element);
    void setPrev(IQueueElement<T> element);
    T getData();
    void setData(T data);
}
