package com.poison.zmeika.game.model;

import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.geometry.Vec2i;

public class CellModel implements ICleanable {
    public Vec2i position;
    public Vec2i size;
    public Vec2f screenPosition;

    public CellModel(){
        position = new Vec2i();
        size = new Vec2i();
        screenPosition = new Vec2f();
    }

    public CellModel position(int x, int y){
        position.set(x, y);
        return this;
    }

    public CellModel size(int x, int y){
        size.set(x,y);
        return this;
    }

    @Override
    public void clean() {
    }
}
