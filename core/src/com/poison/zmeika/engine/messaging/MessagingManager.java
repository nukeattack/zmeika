package com.poison.zmeika.engine.messaging;

/**
 *
 */
public class MessagingManager {
    private static MessagingManager manager;
    public static MessagingManager instance(){
        if(manager == null){
            manager = new MessagingManager();
        }
        return manager;
    }

    private MessageBus messageBus = new MessageBus();

    public void publishEvent(IEvent event){
        messageBus.publishEvent(event);
    }

    public void registerListener(IEventListener listener){
        messageBus.registerHandler(listener);
    }

    public void removeListener(IEventListener listener){
        messageBus.unregisterHandler(listener);
    }

    public void executeEvents(){
        messageBus.startHandlers();
    }
}
