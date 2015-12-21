package com.poison.zmeika.engine.pool;
/**
 * Represents a cached pool of objects.
 *
 */
public interface IPool< T >
{
    T get();
    void release(T t);
    void shutdown();
}

