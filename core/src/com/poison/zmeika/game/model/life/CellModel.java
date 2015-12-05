package com.poison.zmeika.game.model.life;

import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.geometry.Vec2i;
import com.poison.zmeika.game.model.ICleanable;

public class CellModel implements ICleanable{
    public Vec2i position;
    public Vec2i size;
    public Vec2f screenPosition;
    public Vec2f opacity;

    public CellModel(){
        position = new Vec2i();
        screenPosition = new Vec2f();
        opacity = new Vec2f();
    }

    public CellModel position(int x, int y){
        position.set(x, y);
        return this;
    }
    public CellModel opacity(float x, float y){
        opacity.set(x, y);
        return this;
    }

    @Override
    public void clean() {
        position.set(0, 0);
        screenPosition.set(0.0f, 0.0f);
        opacity.set(1.0f, 1.0f);
    }
}
