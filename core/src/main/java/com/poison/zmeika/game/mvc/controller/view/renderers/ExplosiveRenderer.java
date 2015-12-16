package com.poison.zmeika.game.mvc.controller.view.renderers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.messaging.*;
import com.poison.zmeika.game.mvc.controller.logic.BoardController;
import com.poison.zmeika.game.mvc.controller.model.view.Explosive;
import com.poison.zmeika.game.mvc.controller.model.logic.Cell;
import com.poison.zmeika.game.utils.PoolContainer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ExplosiveRenderer extends GameObject implements IEventListener{
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
                cellSprite.setCenter(cellSprite.getWidth() * 0.5f, cellSprite.getHeight() * 0.5f);
                cellSprite.setPosition(explosive.position.x, explosive.position.y);
                cellSprite.setAlpha(explosive.opacity.x);
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
        if(super.update(delta)){
            Iterator<Explosive> iterator = explosives.iterator();
            while (iterator.hasNext()){
                Explosive explosive = iterator.next();
                if(explosive.isFinished()){
                    iterator.remove();
                    PoolContainer.explosives.release(explosive);
                }
            }
            return true;
        }else{
            return false;
        }
    }

    private void createExplosive(float x, float y){
        final Explosive explosive = PoolContainer.explosives.get().setPos(x, y);

//        TweenController.start(Tween.to(explosive.size, Vec2fTweenAccessor.POSITION_XY, duration).target(xscale, yscale).ease(TweenEquations.easeOutExpo).setCallback(new TweenCallback() {
//            @Override
//            public void onEvent(int type, BaseTween<?> source) {
//                if(type == TweenCallback.COMPLETE){
//                    explosive.setFinished(true);
//                }
//            }
//        }));
//        TweenController.start(Tween.to(explosive.opacity, Vec2fTweenAccessor.POSITION_XY, duration*0.8f).target(0.0f, 0.0f).ease(TweenEquations.easeOutExpo));
        explosives.add(explosive);
    }

    private void handleObjectDeleted(IEvent event){
        GameEvent gameEvent = (GameEvent)event;
        Cell cell = (Cell) gameEvent.data[0];
        createExplosive(cell.screenPosition.x, cell.screenPosition.y);
    }

    @Override
    public void construct() {
        cellSprite = new Sprite(TextureManager.instance().getTexture(getContextName(), IMAGE_NAME));
        MessagingManager.instance().registerListener(this);
        super.construct();
    }

    @Override
    public void destruct() {
        MessagingManager.instance().removeListener(this);
        super.destruct();
    }

    @Override
    public void handleEvent(IEvent event) {
        if(event.getType() == GameEventType.OBJECT_DELETED){
            handleObjectDeleted(event);
        }
    }
}
