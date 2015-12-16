package com.poison.zmeika.engine.messaging;

import com.poison.zmeika.engine.pool.IObjectFactory;
import com.poison.zmeika.engine.pool.ObjectPool;

/**
 *
 */
public class EventPool {
    class EventFactory implements IObjectFactory<GameEvent>{
        @Override
        public GameEvent createNew() {
            return new GameEvent();
        }
    }

    public GameEvent getEvent(){
        return gameEventPool.get();
    }

    public void releaseEvent(GameEvent event){
        gameEventPool.release(event);
    }

    private ObjectPool<GameEvent> gameEventPool = buildObjectPool();

    private ObjectPool<GameEvent> buildObjectPool(){
        ObjectPool<GameEvent> result = new ObjectPool<GameEvent>(1000);
        result.setFactory(new IObjectFactory<GameEvent>() {
            @Override
            public GameEvent createNew() {
                return new GameEvent();
            }
        });
        return result;
    }

    private static EventPool poolInstance = null;

    public static EventPool instance(){
        if(poolInstance == null){
            poolInstance = new EventPool();
        }
        return poolInstance;
    }
}
