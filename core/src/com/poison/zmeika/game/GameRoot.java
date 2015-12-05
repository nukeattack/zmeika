package com.poison.zmeika.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.game.controller.logic.BoardController;
import com.poison.zmeika.game.controller.view.ViewController;

/**
 * Created by Stas on 11/22/2015.
 */
public class GameRoot extends GameObject {
    private BoardController boardController;
    private ViewController viewController;

    public GameRoot(){


    }

    @Override
    public void construct() {
        boardController = new BoardController();
        boardController.setRootObject(this);
        boardController.init();
        addChild(boardController);

        viewController = new ViewController();
        viewController.setBoardController(boardController);
        addChild(viewController);
        super.construct();
    }

    @Override
    public boolean draw(float delta, SpriteBatch spriteBatch) {
        return super.draw(delta, spriteBatch);
    }

    @Override
    public boolean update(float delta) {
        return super.update(delta);
    }
}
