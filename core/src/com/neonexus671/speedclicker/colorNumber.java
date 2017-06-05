package com.neonexus671.speedclicker;


import com.badlogic.gdx.graphics.Color;

/**
 * Created by acurr on 6/4/2017.
 */
public class colorNumber {
    private Color color;
    private int count;

    public colorNumber(Color color) {
        this.color = color;
        this.count = 0;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
