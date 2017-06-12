package com.neonexus671.speedclicker.gridSystem;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.neonexus671.speedclicker.SClicker;

import java.util.Random;


/**
 * Created by acurr on 6/2/2017.
 */
public class Unit extends Actor {
    private static final float ALPHA_DECREASE_RATE = .002F;
    private final Random random;
    private boolean colorFade;
    private Rectangle rectangle;
    private Color color;
    private Sprite sprite;
    private Texture texture;
    private float alpha;
    private int shape;
    private Vector2 vector2;
    private Sound sound;

    public Unit(int shape, Vector2 vector2, int height, int width, boolean colorFade) {
        this.color = Color.WHITE;
        sound = null;
        random = new Random();
        setPosition(vector2.x, vector2.y);
        setWidth(width);
        setHeight(height);
        this.colorFade = colorFade;
        rectangle = new Rectangle(vector2.x, vector2.y, width, height);
        createTexture(height, width, color, shape);
        this.sprite = new Sprite(texture);
        this.alpha = 1.0f;
        this.shape = shape;
        this.vector2 = vector2;
    }


    private void createTexture(int height, int width, Color color, int shape) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public static float getAlphaDecreaseRate() {
        return ALPHA_DECREASE_RATE;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (colorFade && color != Color.WHITE) {
            alpha = alpha - ALPHA_DECREASE_RATE;
        }
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, alpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setAlpha(1.0f);
        this.texture.dispose();
        createTexture((int) getHeight(), (int) getWidth(), color, shape);
    }

    public void updatePos(Vector2 vector2, int height, int width) {
        rectangle.set(new Rectangle(vector2.x, vector2.y, width, height));
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void playSound() {
        sound.play(SClicker.volume);
    }

    public boolean isColorFade() {
        return colorFade;
    }

    public void setColorFade(boolean colorFade) {
        this.colorFade = colorFade;
    }

    public Random getRandom() {
        return random;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getAlpha() {
        return alpha;
    }

    private void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public Vector2 getVector2() {
        return vector2;
    }

    public void setVector2(Vector2 vector2) {
        this.vector2 = vector2;
    }
}
