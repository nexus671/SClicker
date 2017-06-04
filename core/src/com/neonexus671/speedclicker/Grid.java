package com.neonexus671.speedclicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by acurr on 6/2/2017.
 */
public class Grid {
    private int size;
    private Array<Unit> units;
    private Random random;
    private  int numberOfWhiteSquares;
    private Array<colorNumber> countOfColors;
    private Array<Color> colorArray;

    public Grid(int size){
        this.random = new Random();
        this.size = size;
        this.numberOfWhiteSquares = size*size;
        units = new Array<Unit>();
        colorArray = new Array<Color>();
        countOfColors = new Array<colorNumber>();
        populateColorArray();
        populateCountArray();
        createGrid(size,0);
    }

    private void createGrid(int size, int shape){
        int width = (int)Math.ceil(Gdx.graphics.getWidth()/(float)(size));
        int height = (int)Math.ceil(Gdx.graphics.getHeight()/(float)(size));
        float x;
        float y;
        for(int i = 0; i < size; i++){
            y = (int)Math.ceil((Gdx.graphics.getHeight()*(1.0/size)*i));
            for (int k = 0; k < size;k++){
                x = (int)Math.ceil((Gdx.graphics.getWidth()*(1.0/size)*k));
                units.add(new Unit(shape,new Vector2(x,y),height,width));
            }
        }
    }

    public boolean randomSpawnColor(){
        if(numberOfWhiteSquares != 0) {
            int oldNumberOfWhiteSquares = numberOfWhiteSquares;
            int randomNumber = random.nextInt((size * size));
            do {
                if(units.get(randomNumber).getColor() == Color.WHITE){
                    units.get(randomNumber).setColor(randomizeColor());
                    numberOfWhiteSquares--;
                }else {
                    randomNumber = random.nextInt((size * size) );
                }
            } while (numberOfWhiteSquares == oldNumberOfWhiteSquares);
        } else {
            return false;
        }
        return true;
    }
    public void resetUnit(int unitNumber){
        for (colorNumber c:countOfColors) {
            if(c.getColor() == units.get(unitNumber).getColor()){
                c.setCount(c.getCount()-1);
            }
        }
        units.get(unitNumber).setColor(Color.WHITE);
        numberOfWhiteSquares++;
    }
    public void resetUnit(Unit unit){
        for (colorNumber c:countOfColors) {
            if(c.getColor() == unit.getColor()){
                c.setCount(c.getCount()-1);
            }
        }
        unit.setColor(Color.WHITE);
        numberOfWhiteSquares++;
    }

    public boolean isFull(){
        return numberOfWhiteSquares == 0;
    }
    public void populateColorArray(){
        colorArray.add(Color.BLUE);
        colorArray.add(Color.RED);
        colorArray.add(Color.VIOLET);
        colorArray.add(Color.ORANGE);
    }
    public void populateCountArray(){
        for (Color c:colorArray) {
            countOfColors.add(new colorNumber(c));
        }
    }
    public Color randomizeColor(){
        Color color;
        int total = 0;
        boolean notSpawned = true;
        for (colorNumber c : countOfColors) {
            total += c.getCount();
        }
        do {
            color = colorArray.get(random.nextInt(colorArray.size));
            for (colorNumber c : countOfColors) {
                if (c.getColor() == color) {
                    if (c.getCount() <= total - c.getCount()) {
                        notSpawned = false;
                        break;
                    }
                }
            }
        }while (notSpawned);
        for (colorNumber c:countOfColors) {
            if(c.getColor() == color){
                c.setCount(c.getCount()+1);
            }
        }
        return color;
    }
    public float getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Array<Unit> getUnits() {
        return units;
    }

    public void setUnits(Array<Unit> units) {
        this.units = units;
    }
}
