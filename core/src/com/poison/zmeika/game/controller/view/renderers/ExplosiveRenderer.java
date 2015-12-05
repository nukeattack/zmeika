package com.poison.zmeika.game.controller.view.renderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.messaging.GameEvent;
import com.poison.zmeika.engine.messaging.GameEventType;
import com.poison.zmeika.engine.messaging.MessagingManager;
import com.poison.zmeika.game.controller.logic.BoardController;
import com.poison.zmeika.game.model.life.CellModel;
import com.poison.zmeika.game.model.fx.Explosive;
import com.poison.zmeika.game.controller.utils.PoolController;
import net.engio.mbassy.listener.Handler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ExplosiveRenderer extends GameObject {
    public static String IMAGE_NAME = "cell.png";
    Sprite cellSprite;
    BoardController controller;
    private List<Explosive> explosives = new LinkedList<Explosive>();

    public ExplosiveRenderer(BoardController controller){
        this.controller = controller;
    }

    @Override
    public boolean draw(float delta, SpriteBatch spriteBatch) {
        if(super.draw(delta, spriteBatch)){
            for(Explosive explosive : explosives){
                explosive.opacity = explosive.opacity - 0.7f * delta;
                explosive.opacity = explosive.opacity <= 0.0f ? 0.0f : explosive.opacity;
                explosive.size.add(1.0f*delta, 1.0f*delta);
                cellSprite.setCenter(cellSprite.getWidth() * 0.5f, cellSprite.getHeight() * 0.5f);
                cellSprite.setPosition(explosive.position.x, explosive.position.y);
                cellSprite.setAlpha(explosive.opacity);
                cellSprite.setScale(explosive.size.x, explosive.size.y);
                cellSprite.draw(spriteBatch);
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(float delta) {
        Iterator<Explosive> iterator = explosives.iterator();
        while(iterator.hasNext()){
            Explosive explosive = iterator.next();
            if(explosive.isFinished()){
                iterator.remove();
                removeExplosive(explosive);
            }
        }
        return super.update(delta);
    }

    private void removeExplosive(Explosive e){
        PoolController.explosives.release(e);
    }

    private void createExplosive(float x, float y){
        System.out.println(x + " " + y);
        explosives.add(PoolController.explosives.get().setPos(x, y));
    }

    @Handler
    public void handle(GameEvent event){
        if(event.type == GameEventType.OBJECT_DELETED){
            Vec2f model = (Vec2f) event.data[0];
            createExplosive(model.x, model.y);
            PoolController.vec2f.release(model);
        }
    }

    @Override
    public void construct() {
        cellSprite = new Sprite(TextureManager.instance().getTexture(getContextName(), IMAGE_NAME));
        MessagingManager.instance().registerListener(this);
        super.construct();
    }

    @Override
    public void destruct() {
        MessagingManager.instance().unregisterListener(this);
        super.destruct();
    }
}
