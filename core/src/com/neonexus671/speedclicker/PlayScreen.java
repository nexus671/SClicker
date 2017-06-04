package com.neonexus671.speedclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by acurr on 6/2/2017.
 */
public class PlayScreen implements Screen {
    private final SClicker game;
    private final Viewport gameViewPort;
    private Stage stage;
    private Grid grid;

    public PlayScreen(SClicker game) {
        this.game = game;
        gameViewPort = new StretchViewport(SClicker.GAME_WIDTH * game.aspectRatio, SClicker.GAME_HEIGHT);
        stage = new Stage(gameViewPort);
        grid = new Grid(4);
        for (Unit u:grid.getUnits()) {
            stage.addActor(u);
        }
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
        for (Unit u:grid.getUnits()) {
            u.draw(game.batch,0);
        }
        if(Gdx.input.justTouched()) {
            handleInput();
        }
        game.batch.end();
        //stage.act();
        //stage.draw();
    }

    public  void handleInput(){
             if (Gdx.input.isTouched()) {
                     for (Unit u:grid.getUnits()) {
                         if(u.getRectangle().contains(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY())){
                            if(u.getColor() == Color.WHITE){
                                System.out.println("Lose");
                            }else {
                                grid.resetUnit(u);

                                grid.randomSpawnColor();

                            }
                         }
                     }

             }
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
