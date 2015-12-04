package com.poison.zmeika.engine.messaging;

import java.util.Objects;

/**
 * Created by Stas on 12/4/2015.
 */
public class GameEvent {
    private Object [] data;
    public GameEventType type;

    public GameEvent(Object...data){
        this.data = new Object[data.length];
        int i = 0;
        for(Object object : data){
            this.data[i] = object;
            i++;
        }
    }

    public GameEvent forThe(GameEventType eventType){
        GameEvent event = new GameEvent(data);
        event.type = eventType;
        return event;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("[");
        for(Object object : data){
            result.append(object.toString());
            result.append(",");
        }
        result.append("]");
        return result.toString();
    }
}
