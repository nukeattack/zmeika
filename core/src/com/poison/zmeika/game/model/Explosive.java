package com.poison.zmeika.game.model;

import com.poison.zmeika.engine.geometry.Vec2f;

/**
 *
 */
public class Explosive {
    public Vec2f position = new Vec2f(1.0f, 1.0f);
    public Vec2f size = new Vec2f(1.0f, 1.0f);
    public Float opacity = 1.0f;

    public Explosive(){

    }

    public Explosive setPos(float x, float y){
        position.set(x,y);
        return this;
    }

    public void setSize(float x, float y){
        size.set(x, y);
    }

    public Vec2f getSize() {
        return size;
    }

    public boolean isFinished(){
        return opacity <= 0;
    }
}
