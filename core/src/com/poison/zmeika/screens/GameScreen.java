package com.poison.zmeika.screens;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.game.GameLevel;


public class GameScreen implements Screen {
    GameObject rootObject;
    OrthographicCamera camera;
    Sprite sprite;
    Texture image;
    SpriteBatch mainBatch;

    public GameScreen(){
        rootObject = new GameLevel();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        image = new Texture(Gdx.files.internal("badlogic.jpg"));
        sprite = new Sprite(image);
        mainBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        Gdx.app.log(this.getClass().getSimpleName(), "showing");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mainBatch.begin();
        sprite.draw(mainBatch);
        sprite.setPosition(400, 300);
        mainBatch.end();
        rootObject.draw(delta);
        rootObject.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(this.getClass().getSimpleName(), "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log(this.getClass().getSimpleName(), "pausing");
    }

    @Override
    public void resume() {
        Gdx.app.log(this.getClass().getSimpleName(), "resuming");
    }

    @Override
    public void hide() {
        Gdx.app.log(this.getClass().getSimpleName(), "hiding");
    }

    @Override
    public void dispose() {
        Gdx.app.log(this.getClass().getSimpleName(), "disposing");
    }
}
