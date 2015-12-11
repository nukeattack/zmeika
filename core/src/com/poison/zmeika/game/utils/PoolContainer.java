package com.poison.zmeika.game.utils;

import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.pool.ObjectFactory;
import com.poison.zmeika.engine.pool.ObjectPool;
import com.poison.zmeika.game.model.fx.Explosive;
import com.poison.zmeika.game.model.life.Cell;

/**
 *
 */
public class PoolContainer {
    public static ObjectPool<Explosive> explosives = new ObjectPool<Explosive>(new ObjectFactory<Explosive>() {
        @Override
        public Explosive createNew() {
            return new Explosive();
        }
    });
    public static ObjectPool<Vec2f> vec2f = new ObjectPool<Vec2f>(new ObjectFactory<Vec2f>() {
        @Override
        public Vec2f createNew() {
            return new Vec2f();
        }
    });
}
