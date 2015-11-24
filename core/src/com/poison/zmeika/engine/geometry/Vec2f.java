package com.poison.zmeika.engine.geometry;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by Stas on 11/22/2015.
 */
public class Vec2f {
    private float y;
    private float x;

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
}
