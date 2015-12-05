package com.poison.zmeika.engine.messaging;

import java.util.Iterator;
import java.util.Objects;

/**
 * Created by Stas on 12/4/2015.
 */
public class GameEvent {
    public Object [] data;
    public GameEventType type;
    public boolean processed = false;

    public GameEvent(){
    }

    public GameEvent clear(){
        data = null;
        type = null;
        processed = false;
        return this;
    }

    public GameEvent withData(Object...data){
        this.data = new Object[data.length];
        int i = 0;
        for(Object object : data){
            this.data[i] = object;
            i++;
        }
        return this;
    }

    public GameEvent isProcessed(boolean processed){
        this.processed = processed;
        return this;
    }

    public GameEvent withType(GameEventType eventType){
        type = eventType;
        return this;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("{name=");
        result.append(type != null ? type.toString() : "");
        result.append(",processed=");
        result.append(processed);
        result.append(",data=[");
        for(int i = 0; i < data.length; i++){
            if(i != 0){
                result.append(",");
            }
            result.append(data[i].toString());
        }
        result.append("]");
        return result.toString();
    }
}
