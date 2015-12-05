package com.poison.zmeika.engine.view;

import com.poison.zmeika.engine.GameObject;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Stas on 12/5/2015.
 */
public class LayerManager extends GameObject{
    private Map<String, GameObject> layers = new LinkedHashMap<>();

    public void addLayer(String name){
        GameObject gameObject = new GameObject();
        layers.put(name, gameObject);
        addChild(gameObject);
    }

    public GameObject getLayer(String name){
        return layers.get(name);
    }
}
