package com.poison.zmeika.engine.geometry;

/**
 * Created by Stas on 12/3/2015.
 */
public class Vec2i {
    public int y = 0;
    public int x = 0;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vec2i(){
        this.x = (0);
        this.y = (0);
    }

    public Vec2i set(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    public void add(Vec2i vec) {
        x += vec.x;
        y += vec.y;
    }
}
