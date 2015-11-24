package com.poison.zmeika.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;

import java.util.ArrayList;
import java.util.List;

public class GameMap extends GameObject {
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private int width = 10;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private int height = 10;
    private List<Sprite> cells = new ArrayList<Sprite>();

    public GameMap(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Sprite cell = new Sprite(TextureManager.instance().loadTexture("cell.png"));
                cell.setPosition(x*16, y*16);
                cells.add(cell);
            }
        }
    }

    @Override
    public void draw(float delta, SpriteBatch spriteBatch) {
        for(Sprite cell : cells){
            cell.draw(spriteBatch);
        }
        super.draw(delta, spriteBatch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
