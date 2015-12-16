package com.poison.zmeika.game.utils;

import com.poison.zmeika.engine.pool.IObjectFactory;
import com.poison.zmeika.engine.pool.ObjectPool;
import com.poison.zmeika.game.mvc.controller.model.view.Explosive;

/**
 *
 */
public class PoolContainer {
    public static ObjectPool<Explosive> explosives = createExplosivesPool();

    private static ObjectPool<Explosive> createExplosivesPool(){
        ObjectPool<Explosive> pool = new ObjectPool<Explosive>(100);
        pool.setFactory(new IObjectFactory<Explosive>() {
            @Override
            public Explosive createNew() {
                return new Explosive();
            }
        });
        return pool;
    }
}
