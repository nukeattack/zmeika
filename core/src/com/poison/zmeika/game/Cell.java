package com.poison.zmeika.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.geometry.Vec2f;

public class Cell extends GameObject {
    private Vec2f pos = new Vec2f(0,0);
    private Sprite sprite;
    private float speed = 1.0f;
    private Vec2f direction = new Vec2f(0,0);
    private float deltaTime = 0.0f;

    public GameMap getLevel() {
        return level;
    }

    public void setLevel(GameMap map) {
        this.level = map;
    }

    GameMap level;

    public Cell(){
        sprite = new Sprite(TextureManager.instance().loadTexture("cell2.png"));
    }

    @Override
    public void draw(float delta, SpriteBatch spriteBatch) {
        super.draw(delta, spriteBatch);
        sprite.setPosition(pos.getX()*16, pos.getY()*16);
        sprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        log("Update ", pos);
        deltaTime += delta;
        checkBoundaries();
        if(deltaTime > speed){
            pos.add(direction);
            deltaTime = deltaTime - speed;
        }
        checkInput();
        super.update(delta);
    }

    private void checkInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            direction.setX(-1);
            log("Left");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            direction.setX(1);
            log("Right");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            direction.setY(1);
            log("UP");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            direction.setY(-1);
            log("Down");
        }
    }

    private void checkBoundaries(){
        if(pos.getX() + direction.getX() > level.getWidth()-1 || pos.getX() + direction.getX() < 0){
            direction.setX(-direction.getX());
        }

        if(pos.getY() + direction.getY() > level.getHeight()-1 || pos.getY() + direction.getY() < 0){
            direction.setY(-direction.getY());
        }
    }
    public Vec2f getPos() {
        return pos;
    }
}
