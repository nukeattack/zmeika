package com.poison.zmeika.game.view;

import com.poison.zmeika.engine.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stas on 12/3/2015.
 */
public class BoardView extends GameObject {
    private List<CellView> cells = new LinkedList<CellView>();

    public BoardView(){

    }

    @Override
    public void addChild(GameObject gameObject) {
        super.addChild(gameObject);
        if(gameObject instanceof CellView){
            cells.add((CellView)gameObject);
        }
    }

    @Override
    public void removeChild(GameObject gameObject) {
        super.removeChild(gameObject);
        cells.remove(gameObject);
    }
}
