package com.neonexus671.speedclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class SClicker extends Game {
    public static final String SF_TTF = "SF.ttf";
    public static final float GAME_WIDTH = 1280;
    public static final float GAME_HEIGHT = 720;
    public static float volume = 1.0f;
    public static int speakerCurrent = 0;
    public final String firstTime = "FirstTime";
    public SpriteBatch batch;
    public BitmapFont titleFont;
    public BitmapFont textFont;
    public BitmapFont dialogFont;
    public float aspectRatio;
    public float densityIndependentSize;
    public Preferences preferences;

    @Override
    public void create() {
        batch = new SpriteBatch();
        aspectRatio = Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        densityIndependentSize = 75 * Gdx.graphics.getDensity();
        int fontSize = Math.round(densityIndependentSize);
        createFonts(fontSize);
        setScreen(new com.neonexus671.speedclicker.screens.PlayScreen(this));
    }

    private void createFonts(int fontSize) {
        FileHandle fontFile = Gdx.files.internal(SF_TTF);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) Math.round((fontSize * .70));
        textFont = generator.generateFont(parameter);
        parameter.size = fontSize;
        titleFont = generator.generateFont(parameter);
        parameter.size = (int) Math.round((fontSize * .20));
        dialogFont = generator.generateFont(parameter);
        generator.dispose();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
