package com.poison.zmeika.engine.view;

/**
 * Created by Stas on 12/5/2015.
 */
public class LayerDescription {
    private String name;
    private int index = 0;

    public LayerDescription(String name, int index){
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
