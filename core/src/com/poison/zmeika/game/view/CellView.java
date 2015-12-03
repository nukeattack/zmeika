package com.poison.zmeika.game.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.game.model.LiveCell;

/**
 * Created by Stas on 12/3/2015.
 */
public class CellView extends GameObject{
    private LiveCell cell;
    private Sprite sprite;

    public CellView(LiveCell cell){
        this.cell = cell;
        sprite = new Sprite(TextureManager.instance().loadTexture("cell2.png"));
    }

    @Override
    public void draw(float delta, SpriteBatch spriteBatch) {
        super.draw(delta, spriteBatch);
        sprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        sprite.setPosition(cell.getPos().getX() * sprite.getWidth(), cell.getPos().getY() * sprite.getHeight());
    }
}
