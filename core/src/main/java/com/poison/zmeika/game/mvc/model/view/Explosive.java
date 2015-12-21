package com.poison.zmeika.game.mvc.model.view;

import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.pool.IPoolable;

/**
 *
 */
public class Explosive implements IPoolable{
    public Vec2f position = new Vec2f(1.0f, 1.0f);
    public Vec2f size = new Vec2f(1.0f, 1.0f);
    public Vec2f opacity = new Vec2f(1.0f, 1.0f);
    public boolean finished = false;

    public Explosive(){
    }

    public Explosive setPos(float x, float y){
        position.set(x,y);
        return this;
    }

    public Explosive setSize(float x, float y){
        size.set(x, y);
        return this;
    }

    public Explosive setOpacity(float opacity){
        opacity = opacity < 0.0 ? 0.0f : opacity;
        this.opacity.x = opacity;
        return this;
    }

    public Vec2f getSize() {
        return size;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = true;
    }


    @Override
    public void setup() {
        position.set(0.0f, 0.0f);
        size.set(1.0f, 1.0f);
        opacity.x = 1.0f;
        finished = false;
    }

    @Override
    public void cleanup() {
        position = null;
        size = null;
        opacity = null;
    }
}
