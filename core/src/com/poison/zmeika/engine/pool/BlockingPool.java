package com.poison.zmeika.engine.pool;

public interface BlockingPool < T > extends Pool < T >
{
    /**
     * Returns an instance of type T from the pool.
     *
     * The call is a blocking call,
     * and client threads are made to wait
     * indefinitely until an object is available.
     * The call implements a fairness algorithm
     * that ensures that a FCFS service is implemented.
     *
     * Clients are advised to react to InterruptedException.
     * If the thread is interrupted while waiting
     * for an object to become available,
     * the current implementations
     * sets the interrupted state of the thread
     * to <code>true</code> and returns null.
     * However this is subject to change
     * from implementation to implementation.
     *
     * @return T an instance of the Object
     * of type T from the pool.
     */
    T get();
}
