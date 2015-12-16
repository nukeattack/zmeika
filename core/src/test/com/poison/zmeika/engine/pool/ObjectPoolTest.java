package com.poison.zmeika.engine.pool;

import org.junit.Assert;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import java.util.*;

/**
 *
 */
public class ObjectPoolTest {
    class TestPoolable implements IPoolable{
        String id = null;
        String name = null;

        public TestPoolable(){
            id = UUID.randomUUID().toString();
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void setup() {

        }

        @Override
        public void cleanup() {
            id = null;
        }
    }

    class ObjectFactory implements IObjectFactory<TestPoolable>{
        @Override
        public TestPoolable createNew() {
            return new TestPoolable();
        }
    }
    
    @Test
    public void testObjectPool(){
        final int initialSize = 10;

        ObjectPool<TestPoolable> pool = new ObjectPool(initialSize);
        pool.setFactory(new ObjectFactory());
        TestPoolable testObject = pool.get();
        Assert.assertNotNull(testObject);
        pool.release(testObject);
        Assert.assertEquals(0, pool.getBalance());
        
        List<TestPoolable> gotElements = new LinkedList<TestPoolable>();
        long time = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++){
            gotElements.add(pool.get());
        }
        for(TestPoolable t : gotElements){
            Assert.assertNotNull(t);
            pool.release(t);
        }
        System.out.println("First run " + (System.currentTimeMillis() - time)/1000.0f);
        gotElements.clear();
        time = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++){
            gotElements.add(pool.get());
        }
        for(TestPoolable t : gotElements){
            Assert.assertNotNull(t);
            pool.release(t);
        }
        System.out.println("Second run " + (System.currentTimeMillis() - time)/1000.0f);
        gotElements.clear();
        time = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++){
            gotElements.add(pool.get());
        }
        for(TestPoolable t : gotElements){
            Assert.assertNotNull(t);
            pool.release(t);
        }
        System.out.println("third run " + (System.currentTimeMillis() - time)/1000.0f);
        Assert.assertNotEquals(initialSize, pool.getSlotsCount());
        Assert.assertEquals(0, pool.getBalance());

    }
}
