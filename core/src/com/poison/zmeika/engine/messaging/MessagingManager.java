package com.poison.zmeika.engine.messaging;

import net.engio.mbassy.bus.MBassador;

/**
 * Created by Stas on 12/4/2015.
 */
public class MessagingManager {
    private MBassador bus;

    private static MessagingManager instance;

    public MessagingManager(){
        bus = new MBassador();
    }

    public static MessagingManager instance(){
        if(instance == null){
            instance = new MessagingManager();
        }
        return instance;
    }

    public Object registerListener(Object handler){
        bus.subscribe(handler);
        return handler;
    }

    public MBassador bus(){
        return this.bus;
    }
}
