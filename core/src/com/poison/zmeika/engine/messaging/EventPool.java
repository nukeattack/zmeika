package com.poison.zmeika.engine.messaging;

import com.poison.zmeika.engine.pool.BoundedBlockingPool;
import com.poison.zmeika.engine.pool.ObjectFactory;
import com.poison.zmeika.engine.pool.Pool;

/**
 *
 */
public class EventPool {
    class EventValidator implements Pool.Validator<GameEvent>{

        @Override
        public boolean isValid(GameEvent event) {
            return true;
        }

        @Override
        public void invalidate(GameEvent event) {
        }
    }
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

    private BoundedBlockingPool<GameEvent> gameEventPool = new BoundedBlockingPool<GameEvent>(10, new EventValidator(), new EventFactory());

    private static EventPool poolInstance = null;

    public static EventPool instance(){
        if(poolInstance == null){
            poolInstance = new EventPool();
        }
        return poolInstance;
    }
}
