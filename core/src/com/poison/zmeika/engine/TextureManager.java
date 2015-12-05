package com.poison.zmeika.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stas on 11/22/2015.
 */
public class TextureManager {
    class TextureItem{
        public int usageCount = 1;
        public Texture texture;

        public TextureItem(Texture texture){
            this.texture = texture;
            this.usageCount = 1;
        }
    }
    private Map<String, TextureItem> textureCache = new HashMap<>();

    public Texture loadTexture(String texture) {
        if (textureCache.containsKey(texture)) {
            textureCache.get(texture).usageCount++;
            return textureCache.get(texture).texture;
        }
        textureCache.put(texture, new TextureItem(new Texture(Gdx.files.internal(texture))));
        return textureCache.get(texture).texture;
    }

    public Sprite simpleSprite(String texture){
        return new Sprite(loadTexture(texture));
    }

    private static TextureManager instance;
    public static TextureManager instance(){
        if(instance == null){
            instance = new TextureManager();
        }
        return instance;
    }
}
