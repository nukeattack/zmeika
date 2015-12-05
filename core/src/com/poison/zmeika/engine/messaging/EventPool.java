package com.poison.zmeika.engine.messaging;

import com.poison.zmeika.engine.pool.ObjectPool;
import com.poison.zmeika.engine.pool.ObjectFactory;

/**
 *
 */
public class EventPool {
    class EventFactory implements ObjectFactory<GameEvent>{
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

    private ObjectPool<GameEvent> gameEventPool = new ObjectPool<GameEvent>(new EventFactory());

    private static EventPool poolInstance = null;

    public static EventPool instance(){
        if(poolInstance == null){
            poolInstance = new EventPool();
        }
        return poolInstance;
    }
}
