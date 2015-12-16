package com.poison.zmeika.engine.geometry;

/**
 * Created by Stas on 11/22/2015.
 */
public class Vec2f {
    public float y;
    public float x;

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vec2f(float x, float y) {
        setX(x);
        setY(y);
    }

    public Vec2f(){
        x = 0.0f;
        y = 0.0f;
    }

    public Vec2f set(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    public void add(Vec2f vec){
        x += vec.x;
        y += vec.y;
    }

    public void add(float x, float y){
        this.x += x;
        this.y += y;
    }
}
