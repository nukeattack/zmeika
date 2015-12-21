package com.poison.zmeika.game.mvc.model.logic;

import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.geometry.Vec2i;
import com.poison.zmeika.engine.pool.IPoolable;

public class Cell implements IPoolable{
    public Vec2i position;
    public Vec2f screenPosition;
    public boolean isAlive = false;

    public Cell(){
        position = new Vec2i();
        screenPosition = new Vec2f();
    }

    public Cell setPosition(int x, int y){
        position.set(x, y);
        return this;
    }

    public Cell setAlive(boolean isLive){
        this.isAlive = isLive;
        return this;
    }

    @Override
    public void cleanup() {
        position.set(0, 0);
        screenPosition.set(0.0f, 0.0f);
    }
    
    @Override
    public void setup() {
        position.set(0, 0);
        screenPosition.set(0.0f, 0.0f);
    }
}