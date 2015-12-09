package com.poison.zmeika.engine.messaging;

import com.badlogic.gdx.Gdx;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Stas on 12/5/2015.
 */
public class MessageBus {
    class Handler {
        Object object;
        List<Method> methods = new ArrayList<Method>();

        public Handler(Object o, List<Method> methods){
            this.object = o;
            this.methods.addAll(methods);
        }

        public void clean(){
            this.object = null;
            this.methods.clear();
            this.methods = null;
        }
    }
    private List<Object> preparedEvents = new LinkedList<Object>();
    private List<Handler> handlers = new ArrayList<Handler>();

    public MessageBus(){

    }

    public void publishEvent(Object event){
        preparedEvents.add(event);
    }

    public void startHandlers(){
        for(Object event : preparedEvents){
            for(int i = 0; i < handlers.size(); i++){
                Handler handler = handlers.get(i);
                for(int j = 0; j < handler.methods.size(); j++){
                    try {
                        handler.methods.get(j).invoke(handler.object, event);
                    }catch (Exception e){
                        Gdx.app.log(this.getClass().getName(), "Cant invoke method");
                        Gdx.app.log(this.getClass().getName(), e.getMessage());
                    }
                }

            }
        }
        preparedEvents.clear();
    }

    public void registerHandler(Object handler){
        List<Method> methods = getHandlerMethods(handler);
        if(methods.size() > 0){
            this.handlers.add(new Handler(handler, methods));
        }
    }

    public void unregisterHandler(Object handler){
        Iterator<Handler> iterator = handlers.iterator();
        while (iterator.hasNext()){
            Handler handlerObject = iterator.next();
            if(handlerObject.object == handler){
                iterator.remove();
            }
        }
    }

    private List<Method> getHandlerMethods(Object object){
        Method [] methods = object.getClass().getMethods();
        List<Method> result = new LinkedList<Method>();
        for(int i = 0; i < methods.length; i++){
            if(methods[i].isAnnotationPresent(EventHandler.class)){
                result.add(methods[i]);
            }
        }
        return result;
    }
}
