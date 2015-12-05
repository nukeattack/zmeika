package com.poison.zmeika.game.controller.view.renderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.game.controller.logic.BoardController;
import com.poison.zmeika.game.model.CellModel;

/**
 * Created by Stas on 12/5/2015.
 */
public class CellRenderer extends GameObject {
    public static String IMAGE_NAME = "cell2.png";
    Sprite cellSprite;
    BoardController controller;

    public CellRenderer(BoardController controller){
        this.controller = controller;
    }

    @Override
    public boolean draw(float delta, SpriteBatch spriteBatch) {
        if(super.draw(delta, spriteBatch)){
            for(CellModel cell : controller.getBoardModel().getCells()){
                cellSprite.setPosition(cell.getPos().getX() * cellSprite.getWidth(), cell.getPos().getY() * cellSprite.getHeight());
                cellSprite.draw(spriteBatch);
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void construct() {
        cellSprite = TextureManager.instance().simpleSprite(IMAGE_NAME);
        super.construct();
    }

    @Override
    public void destruct() {
        super.destruct();
    }
}