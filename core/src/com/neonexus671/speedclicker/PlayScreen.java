package com.neonexus671.speedclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

/**
 * Created by acurr on 6/2/2017.
 */
public class PlayScreen implements Screen {
    private final SClicker game;
    private final Viewport gameViewPort;
    private int timer;
    private int score;
    private final Grid grid;
    private Color lastColorPressed;
    private final Random random;

    public PlayScreen(SClicker game) {
        this.game = game;
        random = new Random();
        timer = 100;
        score = 0;
        gameViewPort = new StretchViewport(SClicker.GAME_WIDTH * game.aspectRatio, SClicker.GAME_HEIGHT);
        grid = new Grid(2);
        lastColorPressed = Color.WHITE;
        grid.randomSpawnColor();
        grid.randomSpawnColor();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        for (Unit u : grid.getUnits()) {
            u.draw(game.batch, 0);
            if (u.getAlpha() <= 0) {
                incorrect();
            }
        }
        if (Gdx.input.justTouched()) {
            handleInput();
        }
        createRandomSpawn();
        game.batch.end();
    }

    private void incorrect() {
        System.out.println("Death");
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            for (Unit u : grid.getUnits()) {
                if (u.getRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                    if (u.getColor() == Color.WHITE || u.getColor() == lastColorPressed) {
                        incorrect();
                    } else {
                        correct(u);
                    }
                }
            }
        }
    }

    private void createRandomSpawn() {
        timer--;
        if (timer == 0) {
            timer = 100;
            int amountSpawn = 1 + random.nextInt(2);
            for (int i = 0; i < amountSpawn; i++) {
                grid.randomSpawnColor();
            }
        }
        if (grid.isFull()) {
            incorrect();
        }
    }

    private void correct(Unit u) {
        lastColorPressed = u.getColor();
        grid.resetUnit(u);
        score++;
        System.out.println(score);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
