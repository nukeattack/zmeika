package com.poison.zmeika.engine.collections.impl;

import com.poison.zmeika.engine.collections.CollectionIterator;
import com.poison.zmeika.engine.collections.ILinkedList;
import com.poison.zmeika.engine.pool.IObjectFactory;
import com.poison.zmeika.engine.pool.IPool;
import com.poison.zmeika.engine.pool.SimpleObjectPool;

public class SimplePooledLinkedList<T> implements ILinkedList<T> {
	static IPool<CollectionIterator> elementPool;
	
	IPool<CollectionIterator> elementsPool;
	CollectionIterator first;
	CollectionIterator last;
	long size = 0;
	
	public SimplePooledLinkedList(int initialSize){
		this.elementsPool = new SimpleObjectPool<CollectionIterator>(initialSize, new IObjectFactory<CollectionIterator>() {
			@Override
			public CollectionIterator createNew() {
				return new CollectionIterator();
			}
		});
	}
	
	@Override
	public void add(T obj) {
		CollectionIterator element = elementsPool.get();
		element.setData(obj);
		if(this.last != null){
			element.setPrev(last);
			this.last.setNext(element);
			this.last = element;
		}
		if(this.first == null){
			this.first = element;
		}
		if(this.last == null){
			this.last = element;
		}
		size++;
	}

	@Override
	public void remove(T obj) {
		CollectionIterator workingElement = first;
		while(workingElement != null){
			if(workingElement.getData() == obj){
				removeElement(workingElement);
				size--;
				return;
			}
		}
		throw new RuntimeException("Cant find element in Linked list " + obj);
	}
	
	private void removeElement(CollectionIterator element){
		CollectionIterator prev = element.getPrev();
		CollectionIterator next = element.getNext();
		if(prev != null && next != null){
			prev.setNext(next);
			next.setPrev(prev);
		}
		if(prev == null){
			this.first = next;
		}
		if(next == null){
			this.last = prev;
		}
		elementsPool.release(element);
	}
	
	public long getSize() {
		return size;
	}

	@Override
	public T getFirst() {
		return first.getData();
	}
	
	public CollectionIterator getIterator(){
		return first;
	}
}
