package com.poison.zmeika.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stas on 11/18/2015.
 */
public class GameObject {
    protected List<GameObject> children;
    protected boolean isDeleted = false;
    protected boolean isConstructed = false;

    public GameObject(){
        children = new LinkedList<>();
    }

    public void construct(){
        isConstructed = true;
        Iterator<GameObject> iterator = children.iterator();
        while (iterator.hasNext()){
            GameObject o = iterator.next();
            o.construct();
        }
    }

    public void destruct(){
        isConstructed = false;
        Iterator<GameObject> iterator = children.iterator();
        while (iterator.hasNext()){
            GameObject o = iterator.next();
            children.remove(o);
            o.destruct();
        }
    }

    public boolean draw(float delta, SpriteBatch spriteBatch){
        if (!isDeleted && isConstructed) {
            for(GameObject o : children){
                o.draw(delta, spriteBatch);
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean update(float delta){
        if(!isDeleted && isConstructed){
            Iterator<GameObject> iterator = children.iterator();
            while (iterator.hasNext()){
                GameObject o = iterator.next();
                if(o.isDeleted){
                    children.remove(o);
                    o.destruct();
                }
                 o.update(delta);
            }
            return true;
        }else{
            return false;
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
        getChildren().add(gameObject);
    }

    public void removeChild(GameObject gameObject){
        getChildren().remove(gameObject);
    }

    public List<GameObject> getChildren() {
        return children;
    }

    public void setChildren(List<GameObject> children) {
        this.children = children;
    }

    public void delete(){
        isDeleted = true;
    }

    public boolean isDeleted(){
        return isDeleted;
    }
}
