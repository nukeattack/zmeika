package com.poison.zmeika.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stas on 11/22/2015.
 */
public class TextureManager {
    private Map<String, Texture> textureCache = new HashMap<String, Texture>();

    public Texture loadTexture(String texture){
        if(textureCache.containsKey(texture)){
            return textureCache.get(texture);
        }
        textureCache.put(texture, new Texture(Gdx.files.internal(texture)));
        return textureCache.get(texture);
    }

    private static TextureManager instance;
    public static TextureManager instance(){
        if(instance == null){
            instance = new TextureManager();
        }
        return instance;
    }
}
