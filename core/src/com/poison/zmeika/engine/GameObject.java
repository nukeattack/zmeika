package com.poison.zmeika.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stas on 11/18/2015.
 */
public class GameObject {
    private List<GameObject> child;

    public GameObject(){
        child = new LinkedList<GameObject>();
    }

    public void construct(){

    }

    public void destruct(){

    }

    public void draw(float delta, SpriteBatch spriteBatch){
        for(int i = 0; i < child.size(); i++){
            child.get(i).draw(delta, spriteBatch);
        }
    }

    public void update(float delta){
        for(int i = 0; i < child.size(); i++){
            child.get(i).update(delta);
        }
    }

    public void log(Object... args){
        StringBuffer sb = new StringBuffer();
        for(Object o : args){
            sb.append(o.toString());
        }
        Gdx.app.log(this.getClass().getSimpleName(), sb.toString());
    }

    public void addChild(GameObject gameObject){
        getChild().add(gameObject);
    }

    public void removeChild(GameObject gameObject){
        getChild().remove(gameObject);
    }

    public List<GameObject> getChild() {
        return child;
    }

    public void setChild(List<GameObject> child) {
        this.child = child;
    }
}
