package com.poison.zmeika.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.InputHelper;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.TweenController;
import com.poison.zmeika.game.GameRoot;


public class GameScreen implements Screen {
    GameObject rootObject;
    OrthographicCamera camera;
    SpriteBatch mainBatch;
    Sprite sprite;
    Vector3 mouse = new Vector3(0,0,0);

    public GameScreen(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth()/2;
        camera.position.y = Gdx.graphics.getHeight()/2;
//        camera.lookAt(-400, -300, 0);
        mainBatch = new SpriteBatch();
        rootObject = new GameRoot();
        sprite = new Sprite(TextureManager.instance().loadTexture("cell2.png"));
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
        mainBatch.setProjectionMatrix(camera.combined);
        mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        Vector3 realPos = camera.unproject(mouse);
        InputHelper.mousePos.set(realPos.x, realPos.y, 0);
        mainBatch.begin();
        rootObject.draw(delta, mainBatch);
        sprite.setPosition(InputHelper.mousePos.x, InputHelper.mousePos.y);
        sprite.draw(mainBatch);
        mainBatch.end();
        rootObject.update(delta);
        TweenController.instance().getManager().update(delta);
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
