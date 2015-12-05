package com.poison.zmeika.engine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stas on 11/22/2015.
 */
public class TextureManager {
    private Map<String, AssetManager> assetContexts = new HashMap<String, AssetManager>();

    public void preloadTextures(String context, Iterable<String> names){
        AssetManager assetContext = getAssetContext(context);
        for(String name : names){
            assetContext.load(name, Texture.class);
        }
    }

    public AssetManager getAssetContext(String contextName){
        synchronized (assetContexts){
            if(assetContexts.containsKey(contextName)){
                return assetContexts.get(contextName);
            }else{
                assetContexts.put(contextName, new AssetManager());
                return assetContexts.get(contextName);
            }
        }
    }

    public void preloadTexture(String context, String name){
        AssetManager assetContext = getAssetContext(context);
        assetContext.load(name, Texture.class);
    }

    private void freeContext(String contextName){
        if(assetContexts.containsKey(contextName)){
            assetContexts.get(contextName).clear();
            assetContexts.remove(contextName);
        }
    }

    public Texture getTexture(String context, String name){
        if(!getAssetContext(context).isLoaded(name)){
            throw new RuntimeException("Cnt find texture on context : " + context + " name : " + name);
        }
        return getAssetContext(context).get(name);
    }

    public void update(float dt){
        int time = (int)(dt*1000);
        for(AssetManager manager : assetContexts.values()){
            manager.update(time);
        }
    }


    private static TextureManager instance;
    public static TextureManager instance(){
        if(instance == null){
            instance = new TextureManager();
        }
        return instance;
    }
}
