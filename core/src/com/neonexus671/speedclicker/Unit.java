package com.neonexus671.speedclicker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import java.util.Random;


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
    private Array<Color> colorArray;
    Random random;
    Rectangle rectangle;
    public Unit(int shape, Vector2 vector2, int height,int width) {
        this.color = Color.WHITE;
        colorArray = new Array<Color>();
        colorArray.add(Color.BLUE);
        colorArray.add(Color.RED);
        colorArray.add(Color.GREEN);
        colorArray.add(Color.GOLD);
        random = new Random();
        setPosition(vector2.x,vector2.y);
        setWidth(width);
        setHeight(height);
        rectangle = new Rectangle(vector2.x,vector2.y,width,height);
        createTexture(height,width, color, shape);
        this.sprite = new Sprite(texture);
        this.alpha = 1.0f;
        this.shape = shape;
        this.vector2 = vector2;
        addListener(new ClickListener());

    }

    private void createTexture(int height,int width, Color color, int shape) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch,float parentalpha) {
        if (color != Color.WHITE) {
            alpha = alpha - .01f;
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
        createTexture((int)getHeight(),(int)getWidth(), color, shape);
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public void randomizeColor(){
        setColor(colorArray.get(random.nextInt(colorArray.size)));
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
}
