package com.neonexus671.speedclicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by acurr on 6/2/2017.
 */
public class Unit extends Actor {
    private Color color;
    private Sprite sprite;
    private Texture texture;
    private float alpha;
    private int shape;
    private Vector2 vector2;
    private int size;

    public Unit(int shape, Vector2 vector2, int size) {
        this.color = Color.WHITE;
        setPosition(vector2.x,vector2.y);
        setWidth(size);
        setHeight(size);
        createTexture(size, color, shape);
        this.sprite = new Sprite(texture);
        this.alpha = 1.0f;
        this.shape = shape;
        this.vector2 = vector2;
        this.size = size;

    }

    private void createTexture(int height, Color color, int shape) {
        Pixmap pixmap = new Pixmap(height, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        if (shape == 0) {
            pixmap.fillRectangle(0, 0, height, height);
        } else {
            pixmap.fillCircle((int)(height / 2.0), (int)(height / 2.0),(int)(height / 2.0));
        }
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch,float parentalpha) {
        if (color != Color.WHITE) {
            alpha = alpha - .05f;
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
        createTexture(size, color, shape);
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

    public void setAlpha(float alpha) {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
