package com.poison.zmeika.game.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.game.model.CellModel;

/**
 * Created by Stas on 12/3/2015.
 */
public class CellView extends GameObject{
    private CellModel model;
    private Sprite sprite;

    public CellView(CellModel cell){
        this.model = cell;
        sprite = new Sprite(TextureManager.instance().loadTexture("cell2.png"));
    }

    public CellModel getModel() {
        return model;
    }

    @Override
    public void draw(float delta, SpriteBatch spriteBatch) {
        super.draw(delta, spriteBatch);
        sprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        float x = model.getPos().getX() * sprite.getWidth();
        float y = model.getPos().getY() * sprite.getHeight();
        sprite.setPosition(x, y);
    }
}
