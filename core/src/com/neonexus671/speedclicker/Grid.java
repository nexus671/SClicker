package com.neonexus671.speedclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by acurr on 6/2/2017.
 */
public class Grid {
    private float size;
    private Array<Unit> units;

    public Grid(int size){
        this.size = size;
        units = new Array<Unit>();
        createGrid(size,0);
    }

    private void createGrid(int size, int shape){
        int width = (int)(Gdx.graphics.getWidth()/(size+10));
        float x;
        float y;
        for(int i = 1; i <= size; i++){
            y = (int)(Gdx.graphics.getHeight()*(1.0/size)*i);
            for (int k = 1; k <= size;k++ ){
                x = (int)(Gdx.graphics.getWidth()*(1.0/size)*k);
                units.add(new Unit(shape,new Vector2(x,y),width));
            }
        }
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Array<Unit> getUnits() {
        return units;
    }

    public void setUnits(Array<Unit> units) {
        this.units = units;
    }
}
