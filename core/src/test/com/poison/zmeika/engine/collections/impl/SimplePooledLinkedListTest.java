package com.poison.zmeika.engine.collections.impl;

import org.junit.Assert;
import org.junit.Test;

import com.poison.zmeika.engine.pool.IPoolable;

public class SimplePooledLinkedListTest {
	class TestObject implements IPoolable{

		@Override
		public void setup() {
		}

		@Override
		public void cleanup() {
		}
		
	}
	
	@Test
	public void testPooledLinkedList(){
		SimplePooledLinkedList<TestObject> testList = new SimplePooledLinkedList<TestObject>(100);
		testList.add(new TestObject());
		Assert.assertNotNull(testList.first);
		Assert.assertNotNull(testList.last);
		testList.remove(testList.getFirst());
		Assert.assertNull(testList.first);
		Assert.assertNull(testList.last);
		Assert.assertEquals(0, testList.getSize());
		testList.add(new TestObject());
		Assert.assertEquals(1, testList.getSize());
		testList.add(new TestObject());
		Assert.assertEquals(2, testList.getSize());
		Assert.assertNotEquals(testList.first, testList.last);
		testList.remove(testList.getFirst());
		Assert.assertEquals(1, testList.getSize());
		Assert.assertNotNull(testList.first);
		Assert.assertNotNull(testList.last);
		Assert.assertEquals(testList.first, testList.last);
		
	}
}
