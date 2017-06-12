package com.neonexus671.speedclicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neonexus671.speedclicker.SClicker;
import com.neonexus671.speedclicker.gridSystem.Grid;
import com.neonexus671.speedclicker.gridSystem.Unit;

import java.util.Random;

/**
 * Created by acurr on 6/2/2017.
 */
public class playScreen implements Screen {
    private final SClicker game;
    private final Viewport gameViewPort;
    private final Grid grid;
    private final Random random;
    private int timer;
    private int score;
    private Color lastColorPressed;

    public playScreen(SClicker game) {
        this.game = game;
        random = new Random();
        timer = 100;
        score = 0;
        gameViewPort = new StretchViewport(SClicker.GAME_WIDTH * game.aspectRatio, SClicker.GAME_HEIGHT);
        grid = new Grid(4);
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
        if (grid.isEmpty()) {
            grid.randomSpawnColor();
        }
        createRandomSpawn();

        game.batch.end();
    }

    public int getScore() {
        return score;
    }

    private void incorrect() {
        if(score > game.preferences.getInteger("HighScore")){
            game.preferences.putInteger("HighScore",score);
            game.preferences.flush();
        }
        game.setScreen(new gameOverScreen(game,this));
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
        if (timer <= 0) {
            timer = 100;
            int amountSpawn = 1 + random.nextInt(getMaxSpawn());
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
        u.playSound();
        grid.resetUnit(u);
        score++;
        timer = timer - 5;
        System.out.println(score);
    }

    private int getMaxSpawn() {
        float size = grid.getSize();
        return (int) Math.ceil(size * size * .3);
    }

    @Override
    public void resize(int width, int height) {
        grid.updateGridPos();
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
