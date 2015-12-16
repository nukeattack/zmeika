package com.poison.zmeika.engine.pool;
/**
 * Represents a cached pool of objects.
 *
 * @author Swaranga
 *
 * @param < T > the type of object to pool.
 */
public interface IPool< T >
{
    T get();
    void release(T t);
    void shutdown();
}

