package com.poison.zmeika.game.controller;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.messaging.GameEvent;
import com.poison.zmeika.engine.messaging.GameEventListener;
import com.poison.zmeika.engine.messaging.MessagingManager;
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

    public ViewController(){
        cellSprite = new Sprite(TextureManager.instance().loadTexture("cell2.png"));
        MessagingManager.instance().registerListener(this);
    }

    @Handler
    public void handle(GameEvent event){
        System.out.println(event.toString());
    }

    @Override
    public void draw(float delta, SpriteBatch spriteBatch) {
        super.draw(delta, spriteBatch);
        List<CellModel> cells = boardController.getBoardModel().getCells();
        for(CellModel cell : cells){
            cellSprite.setPosition(cellSprite.getWidth() * cell.getPos().getX(), cellSprite.getHeight() * cell.getPos().getY());
            cellSprite.draw(spriteBatch);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }
}
