package com.poison.zmeika.game.model.pools;

import com.poison.zmeika.engine.geometry.Vec2i;
import com.poison.zmeika.engine.pool.BlockingPool;
import com.poison.zmeika.engine.pool.ObjectFactory;
import com.poison.zmeika.engine.pool.ObjectPool;
import com.poison.zmeika.game.model.CellModel;
import com.poison.zmeika.game.model.Explosive;

/**
 *
 */
public class PoolsContainer {
    public static ObjectPool<CellModel> cellModels = new ObjectPool<CellModel>(new ObjectFactory<CellModel>() {
        @Override
        public CellModel createNew() {
            return new CellModel();
        }
    });
    public static ObjectPool<Explosive> explosives = new ObjectPool<Explosive>(new ObjectFactory<Explosive>() {
        @Override
        public Explosive createNew() {
            return new Explosive();
        }
    });
}
