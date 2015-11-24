package com.poison.zmeika.engine;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.geometry.Vec2fTweenAccessor;

/**
 * Created by Stas on 11/22/2015.
 */
public class TweenController {
    public TweenController(){
        manager = new TweenManager();
        Tween.registerAccessor(Vec2f.class, new Vec2fTweenAccessor());
    }

    public TweenManager getManager() {
        return manager;
    }

    public void setManager(TweenManager manager) {
        this.manager = manager;
    }

    private TweenManager manager;
    private static TweenController instance;

    public static TweenController instance(){
        if(instance == null){
            instance = new TweenController();
        }
        return instance;
    }

}
