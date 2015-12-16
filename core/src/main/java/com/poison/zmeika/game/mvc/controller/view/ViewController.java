package com.poison.zmeika.game.mvc.controller.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.view.LayerManager;
import com.poison.zmeika.game.mvc.controller.logic.BoardController;
import com.poison.zmeika.game.mvc.controller.view.renderers.CellRenderer;

/**
 * Created by Stanislav_Rogovskyi on 12/3/2015.
 */
public class ViewController extends GameObject {
    private BoardController boardController;
    private Sprite cellSprite;

    private LayerManager layerManager = new LayerManager();

    public ViewController(){

    }

    @Override
    public void construct() {
        layerManager.addLayer("squares");
        layerManager.addLayer("effects");
        addChild(layerManager);

        fillLayers();
        super.construct();
    }

    private void fillLayers(){
        layerManager.getLayer("squares").addChild(new CellRenderer(boardController));
//        layerManager.getLayer("effects").addChild(new ExplosiveRenderer(boardController));
    }

    @Override
    public void destruct() {
        super.destruct();
    }

    @Override
    public boolean update(float delta) {
        return super.update(delta);
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
