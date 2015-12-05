package com.poison.zmeika.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.IAsyncLoaded;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.game.controller.logic.BoardController;
import com.poison.zmeika.game.controller.view.ViewController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Stas on 11/22/2015.
 */
public class LevelRoot extends GameObject implements IAsyncLoaded{
    private BoardController boardController;
    private ViewController viewController;
    private static List<String> TEXTURES = Arrays.asList(new String [] {"cell2.png"});
    public static String CONTEXT_NAME = "level_context";

    public LevelRoot(){
        setContextName(CONTEXT_NAME);
    }

    public List<String> getTextures(){
        return TEXTURES;
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
    public void destruct() {

    }

    @Override
    public boolean draw(float delta, SpriteBatch spriteBatch) {
        return super.draw(delta, spriteBatch);
    }

    @Override
    public boolean update(float delta) {
        return super.update(delta);
    }

    @Override
    public void startLoading() {
        TextureManager.instance().preloadTextures(getContextName(), getTextures());
    }

    @Override
    public float getProgress() {
        return TextureManager.instance().getAssetContext(getContextName()).getProgress();
    }
}
