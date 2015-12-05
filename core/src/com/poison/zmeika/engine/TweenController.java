package com.poison.zmeika.engine;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.geometry.Vec2fTweenAccessor;
import com.poison.zmeika.engine.geometry.Vec2i;
import com.poison.zmeika.engine.geometry.Vec2iTweenAccessor;

/**
 * Created by Stas on 11/22/2015.
 */
public class TweenController {
    public TweenController(){
        manager = new TweenManager();
        Tween.registerAccessor(Vec2f.class, new Vec2fTweenAccessor());
        Tween.registerAccessor(Vec2i.class, new Vec2iTweenAccessor());
    }

    public TweenManager manager;
    private static TweenController instance;

    public static TweenController instance(){
        if(instance == null){
            instance = new TweenController();
        }
        return instance;
    }

    public static void start(Tween tween){
        tween.start(instance().manager);
    }

}
