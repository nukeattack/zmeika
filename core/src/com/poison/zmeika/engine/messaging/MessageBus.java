package com.poison.zmeika.engine.messaging;

import com.badlogic.gdx.Gdx;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Stas on 12/5/2015.
 */
public class MessageBus {
    private List<IEvent> preparedEvents = new LinkedList<IEvent>();
    private List<IEventListener> handlers = new ArrayList<IEventListener>();

    public MessageBus(){

    }

    public void publishEvent(IEvent event){
        preparedEvents.add(event);
    }

    public void startHandlers(){
        for(IEvent event : preparedEvents){
            for(int i = 0; i < handlers.size(); i++){
                handlers.get(i).handleEvent(event);
            }
        }
        preparedEvents.clear();
    }

    public void registerHandler(IEventListener handler){
        handlers.add(handler);
    }

    public void unregisterHandler(Object handler){
        handlers.remove(handler);
    }


}
