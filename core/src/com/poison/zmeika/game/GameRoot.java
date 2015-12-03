package com.poison.zmeika.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.game.controller.BoardController;

/**
 * Created by Stas on 11/22/2015.
 */
public class GameRoot extends GameObject {
    private BoardController boardController;

    public GameRoot(){
        boardController = new BoardController();
        boardController.setRootObject(this);
        boardController.init();
    }

    @Override
    public void draw(float delta, SpriteBatch spriteBatch) {
        super.draw(delta, spriteBatch);
        boardController.update(delta);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
