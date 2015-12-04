package com.poison.zmeika.engine.messaging;

import net.engio.mbassy.listener.Handler;

/**
 * Created by Stas on 12/4/2015.
 */
public class GameEventListener {
    @Handler
    public void handle(GameEvent event){
        System.out.println(event.toString());
    }
}
