package com.poison.zmeika.engine.messaging;

/**
 * Created by Stas on 12/4/2015.
 */
public enum  GameEventType {
    DEFAULT,
    OBJECT_CREATED,
    OBJECT_DELETED;
    public static GameEventType byName(String name){
        if(name.equals("object_created")){
            return  OBJECT_CREATED;
        }
        if(name.equals("object_deleted")){
            return OBJECT_DELETED;
        }
        return DEFAULT;
    }
}
