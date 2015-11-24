package com.poison.zmeika.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;

/**
 * Created by Stas on 11/22/2015.
 */
public class GameRoot extends GameObject {
    private GameMap level;
    private Cell cell;

    public GameRoot(){
        addChild(level = new GameMap());
        addChild(cell = new Cell());
        cell.setLevel(level);
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
