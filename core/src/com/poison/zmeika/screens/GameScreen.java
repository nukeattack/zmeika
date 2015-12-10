package com.poison.zmeika.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.poison.zmeika.engine.InputHelper;
import com.poison.zmeika.engine.TextureManager;
import com.poison.zmeika.engine.TweenController;
import com.poison.zmeika.engine.geometry.Vec2f;
import com.poison.zmeika.engine.geometry.Vec2fTweenAccessor;
import com.poison.zmeika.engine.messaging.MessagingManager;
import com.poison.zmeika.game.LevelRoot;


public class GameScreen implements Screen {
    public static final String CONTEXT = "gameScreenContext";

    LevelRoot levelRoot;
    OrthographicCamera camera;
    OrthographicCamera alternativeCamera;
    SpriteBatch mainBatch;
    SpriteBatch alternativeBatch;

    Sprite cursor;
    Vector3 mouse = new Vector3(0,0,0);
    private boolean levelCreated = false;
    private Vec2f posVec = new Vec2f(0.0f, 0.0f);
    private Vec2f scaleVec = new Vec2f(1.0f, 1.0f);


    public GameScreen(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.x = Gdx.graphics.getWidth()/2;
        camera.position.y = Gdx.graphics.getHeight()/2;

        alternativeCamera = new OrthographicCamera(Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight()*2);
        alternativeCamera.position.x = Gdx.graphics.getWidth()/2;
        alternativeCamera.position.y = Gdx.graphics.getHeight()/2;

        mainBatch = new SpriteBatch();
        alternativeBatch = new SpriteBatch();
        levelRoot = getLevelRoot(1);

        cursor = new Sprite(new Texture(Gdx.files.local("cell2.png")));


        TextureManager.instance().preloadTextures(levelRoot.getContextName(), levelRoot.getTextures());
        TweenController.instance();
    }

    @Override
    public void show() {
        Gdx.app.log(this.getClass().getSimpleName(), "showing");
        //TweenController.start(Tween.to(scaleVec, Vec2fTweenAccessor.POSITION_XY, 4.0f).target(1.0f / 16.0f, 1.0f/16.0f));
    }

    @Override
    public void render(float delta) {
        if(levelCreated){
            float speed = 100.0f;
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                posVec.x += delta * speed;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                posVec.x -= delta * speed;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                posVec.y += delta * speed;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                posVec.y -= delta * speed;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                scaleVec.x -= delta ;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                scaleVec.x += delta ;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                scaleVec.y += delta;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                scaleVec.y -= delta;
            }

            TweenController.instance().manager.update(delta);
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            alternativeCamera.setToOrtho(false, Gdx.graphics.getWidth()*scaleVec.x, Gdx.graphics.getHeight()*scaleVec.y);
            System.out.println("Scale " + scaleVec);
            System.out.println("Position " + posVec);

            alternativeCamera.position.x = posVec.x * scaleVec.x;//Gdx.graphics.getWidth()*scaleVec.x;
            alternativeCamera.position.y = posVec.y * scaleVec.x;//Gdx.graphics.getHeight()*scaleVec.y;
            alternativeCamera.update();
            camera.update();
            mainBatch.setProjectionMatrix(camera.combined);
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            Vector3 realPos = camera.unproject(mouse);
            InputHelper.mousePos.set(realPos.x, realPos.y, 0);
            mainBatch.begin();
            //levelRoot.draw(delta, mainBatch);
            cursor.setPosition(InputHelper.mousePos.x, InputHelper.mousePos.y);
            cursor.draw(mainBatch);
            mainBatch.end();
            alternativeBatch.setProjectionMatrix(alternativeCamera.combined);
            alternativeBatch.begin();
            levelRoot.draw(delta, alternativeBatch);
            alternativeBatch.end();
            MessagingManager.instance().executeEvents();
            levelRoot.update(delta);
        }else{
            if(levelRoot.getProgress() == 1.0){
                levelRoot.construct();
                levelCreated = true;
                Gdx.app.log("Loaded " + levelRoot.getContextName(), "Progress : 100%");
            }else{
                TextureManager.instance().update(delta);
                Gdx.app.log("Loading " + levelRoot.getContextName(), "Progress : " + levelRoot.getProgress()*100);
            }
        }
    }

    private LevelRoot getLevelRoot(int id){
        return new LevelRoot();
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
