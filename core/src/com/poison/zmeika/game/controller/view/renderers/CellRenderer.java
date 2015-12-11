package com.poison.zmeika.game.controller.view.renderers;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.TweenController;
import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.geometry.Vec2fTweenAccessor;
import com.poison.zmeika.game.controller.logic.BoardController;
import com.poison.zmeika.game.model.life.Cell;

public class CellRenderer extends GameObject {
    public static String IMAGE_NAME = "cell2.png";
    Sprite cellSprite;
    BoardController controller;
    Vec2f innerScale = new Vec2f(16.0f / 800.0f, 16.0f / 600.0f);
    Vec2f outerScale = new Vec2f(1.0f, 1.0f);
    Vec2f anc = new Vec2f(400.0f, 300.0f);
    float time = 2.0f;
    TweenEquation equation = TweenEquations.easeInExpo;


    public CellRenderer(BoardController controller){
        this.controller = controller;
    }

    @Override
    public boolean draw(float delta, SpriteBatch spriteBatch) {
        if(super.draw(delta, spriteBatch)){
            draw(spriteBatch, innerScale.x, innerScale.y, anc.x, anc.y, anc.x, anc.y);
            draw(spriteBatch, outerScale.x, outerScale.y, anc.x, anc.y, anc.x, anc.y);
            return true;
        }else{
            return false;
        }
    }

    private void draw(SpriteBatch spriteBatch, float scaleX, float scaleY, float x, float y, float ancorX, float ancorY){
        for(Cell cell : controller.getBoard().getCells()){
            if(cell.isAlive){
                float screenX = cell.position.x * cellSprite.getWidth() * scaleX + x - ancorX * scaleX;
                float screenY = cell.position.y * cellSprite.getHeight() * scaleY + y - ancorY * scaleY;
                cell.screenPosition.set(screenX, screenY);
                cellSprite.setScale(scaleX, scaleY);
                cellSprite.setPosition(screenX, screenY);
                cellSprite.draw(spriteBatch);
            }
        }
    }

    @Override
    public void construct() {
        cellSprite = new Sprite(TextureManager.instance().getTexture(getContextName(), IMAGE_NAME));
        TweenController.start(Tween.to(innerScale, Vec2fTweenAccessor.POSITION_XY, time).target(1.0f, 1.0f).setCallbackTriggers(TweenCallback.END).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                if(type == TweenCallback.END){
                    anc.x = 200 + (float)(Math.random() * 400) ;
                    anc.y = 100 + (float)(Math.random() * 300) ;
                }
            }
        }).repeat(Tween.INFINITY, 0.0f).ease(equation));
        TweenController.start(Tween.to(outerScale, Vec2fTweenAccessor.POSITION_XY, time).target(16.0f, 16.0f).repeat(Tween.INFINITY, 0.0f).ease(equation));
        super.construct();
    }

    @Override
    public void destruct() {
        super.destruct();
    }
}
