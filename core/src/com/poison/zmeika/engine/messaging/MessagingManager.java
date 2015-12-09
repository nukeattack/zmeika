package com.poison.zmeika.engine.messaging;

/**
 * Created by Stas on 12/4/2015.
 */
public class MessagingManager {
    private static MessagingManager instance;
    private MessageBus bus = new MessageBus();

    public MessagingManager(){
    }

    public static MessagingManager instance(){
        if(instance == null){
            instance = new MessagingManager();
        }
        return instance;
    }

    public void executeEvents(){
        bus.startHandlers();
    }

    public void cleanupEvents(){

    }

    public Object registerHandler(Object handler){
        bus.registerHandler(handler);
        return handler;
    }

    public void unregisterListener(Object handler){
        bus.unregisterHandler(handler);
    }

    public void publishEvent(GameEvent event){
        bus.publishEvent(event);
    }
}
