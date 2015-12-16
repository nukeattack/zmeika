package com.poison.zmeika.engine.pool;

import java.util.Arrays;

/**
 *
 */
public class ObjectPool <T extends IPoolable> implements IPool<T> {
    private Object [] elements;
    private long balance = 0;
    private long slotsCount = 0;

    private IObjectFactory<T> factory;

    public ObjectPool(int initialSize){
        elements = new Object[initialSize];
        slotsCount = initialSize;
    }

    private void insertElement(T element){
        for(int i = 0; i < elements.length; i++){
            if(elements[i] == null){
                elements[i] = element;
                return;
            }
        }
        elements = Arrays.copyOf(elements, elements.length * 2);
        slotsCount = elements.length;
        insertElement(element);
    }

    @Override
    public T get() {
        T result = null;
        balance--;
        for(int i = 0; i < elements.length; i++){
            if(elements[i] != null){
                result = (T)elements[i];
                elements[i] = null;
                result.setup();
                return result;
            }
        }
        result = factory.createNew();
        result.setup();
        return result;
    }

    @Override
    public void release(T t) {
    	balance++;
        insertElement(t);
    }

    @Override
    public void shutdown() {
        for(int i = 0; i < elements.length; i++){
            if(elements[i] != null){
                ((IPoolable)(elements[i])).cleanup();
                elements[i] = null;
            }
        }
    }

    public long getBalance() {
        return balance;
    }

    public void setFactory(IObjectFactory<T> factory) {
        this.factory = factory;
    }

    public long getSlotsCount() {
        return slotsCount;
    }
}
