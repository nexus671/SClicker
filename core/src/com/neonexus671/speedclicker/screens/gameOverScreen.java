package com.neonexus671.speedclicker.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neonexus671.speedclicker.SClicker;


/**
 * Created by acurr on 6/7/2017.
 */
public class gameOverScreen implements Screen{
    private final SClicker game;
    private final Viewport gameViewPort;
    private playScreen playScreen;
    private Stage stage;
    private Label score;
    private Label highScore;
    private Label playAgain;
    private Label mainMenu;
    private TextButton play;
    private TextButton menu;

    gameOverScreen(SClicker game, playScreen playScreen){
        this.game = game;
        this.playScreen = playScreen;
        gameViewPort = new StretchViewport(SClicker.GAME_WIDTH * game.aspectRatio, SClicker.GAME_HEIGHT);
        stage = new Stage(gameViewPort,game.batch);
        createLabels();
        createButtons();
        setupStage();
    }
    private  void setupStage(){
        stage.addActor(score);
        stage.addActor(highScore);
        stage.addActor(play);
        stage.addActor(menu);
    }

    private void createButtons(){
        play = new TextButton("Replay",game.skin);
        play.setPosition(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        menu = new TextButton("Main Menu",game.skin);
    }
    private void createLabels(){
        score = new Label("Score: "+playScreen.getScore(),game.skin);
        highScore = new Label("Best: "+game.preferences.getInteger("HighScore"),game.skin);
        playAgain = new Label("Replay",game.skin);
        mainMenu = new Label("Main Menu",game.skin);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
