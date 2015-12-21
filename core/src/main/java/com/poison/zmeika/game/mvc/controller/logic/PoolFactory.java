package com.poison.zmeika.game.mvc.controller.logic;

import com.poison.zmeika.engine.pool.IObjectFactory;
import com.poison.zmeika.engine.pool.IPool;
import com.poison.zmeika.engine.pool.IPoolable;
import com.poison.zmeika.engine.pool.SimpleObjectPool;
import com.poison.zmeika.game.mvc.model.view.Explosive;

public class PoolFactory {
	private static PoolFactory instance;
	
	public static PoolFactory getInstance(){
		if(instance == null){
			instance = new PoolFactory();
		}
		return instance;
	}
	
	private IObjectFactory<Explosive> explosiveFactory = new IObjectFactory<Explosive>() {
		@Override
		public Explosive createNew() {
			return new Explosive();
		}
	};
	
	public IPool<Explosive> buildExplosivePool(){
		SimpleObjectPool<Explosive> result = new SimpleObjectPool<>(1000);
		result.setFactory(explosiveFactory);
		return result;
	}
	
	public <T extends IPoolable> void clearPool(IPool<T> pool){
		pool.shutdown();
	}
	
}
