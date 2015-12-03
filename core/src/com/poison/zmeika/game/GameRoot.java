package com.poison.zmeika.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.game.controller.BoardController;
import com.poison.zmeika.game.controller.ViewController;

/**
 * Created by Stas on 11/22/2015.
 */
public class GameRoot extends GameObject {
    private BoardController boardController;
    private ViewController viewController;

    public GameRoot(){
        boardController = new BoardController();
        boardController.setRootObject(this);
        boardController.init();
        addChild(boardController);

        viewController = new ViewController();
        viewController.setBoardController(boardController);
        addChild(viewController);
    }

    @Override
    public void draw(float delta, SpriteBatch spriteBatch) {
        super.draw(delta, spriteBatch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
