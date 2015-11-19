package com.poison.zmeika.engine;

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

    public void draw(float delta){
        for(int i = 0; i < child.size(); i++){
            child.get(i).draw(delta);
        }
    }

    public void update(float delta){
        for(int i = 0; i < child.size(); i++){
            child.get(i).update(delta);
        }
    }

    public List<GameObject> getChild() {
        return child;
    }

    public void setChild(List<GameObject> child) {
        this.child = child;
    }
}
