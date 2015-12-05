package com.poison.zmeika.engine.messaging;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.publication.SyncAsyncPostCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Stas on 12/4/2015.
 */
public class MessagingManager {
    private MBassador<GameEvent> bus;

    private static MessagingManager instance;
    private List<SyncAsyncPostCommand> commands = new LinkedList<SyncAsyncPostCommand>();
    private List<GameEvent> events = new LinkedList<GameEvent>();

    public MessagingManager(){
        bus = new MBassador();
    }

    public static MessagingManager instance(){
        if(instance == null){
            instance = new MessagingManager();
        }
        return instance;
    }

    public void executeEvents(){
        for(SyncAsyncPostCommand command : commands){
            command.now();
        }
        cleanupEvents();
    }

    public void cleanupEvents(){
        for(GameEvent event : events){
            EventPool.instance().releaseEvent(event);
        }
        commands.clear();
        events.clear();
    }

    public Object registerListener(Object handler){
        bus.subscribe(handler);
        return handler;
    }

    public void unregisterListener(Object handler){
        bus.unsubscribe(handler);
    }

    public void publishEvent(GameEvent event){
        commands.add(bus.post(event));
        events.add(event);
    }
}
