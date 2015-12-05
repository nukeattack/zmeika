package com.poison.zmeika.game.controller.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.messaging.GameEvent;
import com.poison.zmeika.engine.messaging.GameEventListener;
import com.poison.zmeika.engine.messaging.MessagingManager;
import com.poison.zmeika.engine.view.LayerManager;
import com.poison.zmeika.game.controller.logic.BoardController;
import com.poison.zmeika.game.controller.view.renderers.CellRenderer;
import com.poison.zmeika.game.controller.view.renderers.ExplosiveRenderer;
import net.engio.mbassy.listener.Handler;

/**
 * Created by Stanislav_Rogovskyi on 12/3/2015.
 */
public class ViewController extends GameObject {
    private BoardController boardController;
    private Sprite cellSprite;
    private GameEventListener eventListener;

    private LayerManager layerManager = new LayerManager();

    public ViewController(){

    }

    @Override
    public void construct() {
        MessagingManager.instance().registerListener(this);

        layerManager.addLayer("squares");
        layerManager.addLayer("effects");
        addChild(layerManager);

        fillLayers();
        super.construct();
    }

    private void fillLayers(){
        layerManager.getLayer("squares").addChild(new CellRenderer(boardController));
        layerManager.getLayer("effects").addChild(new ExplosiveRenderer(boardController));
    }

    @Override
    public void destruct() {
        MessagingManager.instance().unregisterListener(this);
        super.destruct();
    }

    @Override
    public boolean update(float delta) {
        return super.update(delta);
    }

    @Handler
    public void handle(GameEvent event){

    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
