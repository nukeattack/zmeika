package com.poison.zmeika.game.controller.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.messaging.GameEvent;
import com.poison.zmeika.engine.messaging.GameEventListener;
import com.poison.zmeika.engine.messaging.MessagingManager;
import com.poison.zmeika.engine.view.LayerManager;
import com.poison.zmeika.game.controller.logic.BoardController;
import com.poison.zmeika.game.controller.view.renderers.CellRenderer;
import com.poison.zmeika.game.model.CellModel;
import net.engio.mbassy.listener.Handler;

import java.util.List;

/**
 * Created by Stanislav_Rogovskyi on 12/3/2015.
 */
public class ViewController extends GameObject {
    private BoardController boardController;
    private Sprite cellSprite;
    private GameEventListener eventListener;
    private CellRenderer cellRenderer;

    private LayerManager layerManager = new LayerManager();

    public ViewController(){

    }

    @Override
    public void construct() {
        MessagingManager.instance().registerListener(this);

        layerManager.addLayer("squares");
        layerManager.addLayer("effects");
        addChild(layerManager);
    }

    @Override
    public void destruct() {
        super.destruct();
        MessagingManager.instance().bus().unsubscribe(this);
    }

    @Override
    public boolean update(float delta) {
        return super.update(delta);
    }

    @Handler
    public void handle(GameEvent event){
        System.out.println("******************* EVENT");
        System.out.println(event.toString());
        System.out.println("******************* END EVENT");
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
        layerManager.getLayer("squares").addChild(cellRenderer = new CellRenderer(boardController));
    }
}
