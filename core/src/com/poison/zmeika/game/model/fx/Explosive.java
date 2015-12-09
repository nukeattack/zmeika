package com.poison.zmeika.game.model.fx;

import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.game.model.ICleanable;

/**
 *
 */
public class Explosive implements ICleanable{
    public Vec2f position = new Vec2f(1.0f, 1.0f);
    public Vec2f size = new Vec2f(1.0f, 1.0f);
    public Vec2f opacity = new Vec2f(1.0f, 1.0f);
    public boolean finished = false;

    public Explosive(){
        clean();
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
    public void clean() {
        position.set(0.0f, 0.0f);
        size.set(1.0f, 1.0f);
        opacity.x = 1.0f;
        finished = false;
    }
}
