package com.poison.zmeika;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.poison.zmeika.screens.GameScreen;

public class ZmeikaGame extends ApplicationAdapter {
    Screen screen;
    @Override
    public void create() {
        super.create();
        Gdx.graphics.setContinuousRendering(true);
        Gdx.graphics.requestRendering();

        Gdx.app.log(this.getClass().getName(), "Created");
        setScreen(new GameScreen());
    }

    @Override
    public void render() {
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
        super.render();
    }

    @Override
    public void dispose() {
        if (screen != null) screen.hide();
    }

    @Override
    public void pause() {
        if (screen != null) screen.pause();
    }

    @Override
    public void resume() {
        if (screen != null) screen.resume();
    }

    @Override
    public void resize(int width, int height) {
        if (screen != null) screen.resize(width, height);
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        if (this.screen != null) this.screen.hide();

        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }
}
