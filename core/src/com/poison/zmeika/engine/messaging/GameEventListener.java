package com.poison.zmeika.engine.messaging;

/**
 * Created by Stas on 12/4/2015.
 */
public class GameEventListener {
    @EventHandler
    public void handle(GameEvent event){
        System.out.println(event.toString());
    }
}
