package com.neonexus671.speedclicker.gridSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by acurr on 6/2/2017.
 */
public class Grid {
    private final Random random;
    private final Array<colorNumber> countOfColors;
    private final Array<Color> colorArray;
    private final Array<Sound> soundArray;
    private int size;
    private Array<Unit> units;
    private int numberOfWhiteSquares;

    public Grid(int size) {
        this.random = new Random();
        this.size = size;
        this.numberOfWhiteSquares = size * size;
        units = new Array<Unit>();
        colorArray = new Array<Color>();
        soundArray = new Array<Sound>();
        countOfColors = new Array<colorNumber>();
        populateColorArray();
        populateSoundArray();
        populateCountArray();
        createGrid(size, 0);
    }

    private void populateColorArray() {
        colorArray.add(Color.BLUE);
        colorArray.add(Color.RED);
        colorArray.add(Color.VIOLET);
        colorArray.add(Color.ORANGE);
    }

    private void populateSoundArray() {
        soundArray.add(Gdx.audio.newSound(Gdx.files.internal("c5.ogg")));
        soundArray.add(Gdx.audio.newSound(Gdx.files.internal("d5.ogg")));
        soundArray.add(Gdx.audio.newSound(Gdx.files.internal("f5.ogg")));
        soundArray.add(Gdx.audio.newSound(Gdx.files.internal("g5.ogg")));
    }

    private void populateCountArray() {
        for (Color c : colorArray) {
            countOfColors.add(new colorNumber(c));
        }
    }

    private void createGrid(int size, int shape) {
        int width = (int) Math.ceil(Gdx.graphics.getWidth() / (float) (size));
        int height = (int) Math.ceil(Gdx.graphics.getHeight() / (float) (size));
        float x;
        float y;
        for (int i = 0; i < size; i++) {
            y = (int) Math.ceil((Gdx.graphics.getHeight() * (1.0 / size) * i));
            for (int k = 0; k < size; k++) {
                x = (int) Math.ceil((Gdx.graphics.getWidth() * (1.0 / size) * k));
                units.add(new Unit(shape, new Vector2(x, y), height, width, false));
            }
        }
    }

    public void updateGridPos() {
        int width = (int) Math.ceil(Gdx.graphics.getWidth() / (float) (size));
        int height = (int) Math.ceil(Gdx.graphics.getHeight() / (float) (size));
        int number = 0;
        float x;
        float y;
        for (int i = 0; i < size; i++) {
            y = (int) Math.ceil((Gdx.graphics.getHeight() * (1.0 / size) * i));
            for (int k = 0; k < size; k++) {
                x = (int) Math.ceil((Gdx.graphics.getWidth() * (1.0 / size) * k));
                units.get(number).updatePos(new Vector2(x, y), height, width);
                number++;
            }
        }
    }

    public void randomSpawnColor() {
        if (numberOfWhiteSquares != 0) {
            int oldNumberOfWhiteSquares = numberOfWhiteSquares;
            int randomNumber = random.nextInt((size * size));
            do {
                if (units.get(randomNumber).getColor() == Color.WHITE) {
                    Color color = randomizeColor();
                    units.get(randomNumber).setColor(color);
                    units.get(randomNumber).setSound(soundArray.get(colorArray.indexOf(color, true)));
                    numberOfWhiteSquares--;
                } else {
                    randomNumber = random.nextInt((size * size));
                }
            } while (numberOfWhiteSquares == oldNumberOfWhiteSquares);
        }
    }

    private Color randomizeColor() {
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
        } while (notSpawned);
        for (colorNumber c : countOfColors) {
            if (c.getColor() == color) {
                c.setCount(c.getCount() + 1);
            }
        }
        return color;
    }

    public void resetUnit(int unitNumber) {
        for (colorNumber c : countOfColors) {
            if (c.getColor() == units.get(unitNumber).getColor()) {
                c.setCount(c.getCount() - 1);
            }
        }
        units.get(unitNumber).setColor(Color.WHITE);
        numberOfWhiteSquares++;
    }

    public void resetUnit(Unit unit) {
        for (colorNumber c : countOfColors) {
            if (c.getColor() == unit.getColor()) {
                c.setCount(c.getCount() - 1);
            }
        }
        unit.setColor(Color.WHITE);
        numberOfWhiteSquares++;
    }

    public Random getRandom() {
        return random;
    }

    public boolean isEmpty() {
        return numberOfWhiteSquares == size * size;
    }

    public int getNumberOfWhiteSquares() {
        return numberOfWhiteSquares;
    }

    public void setNumberOfWhiteSquares(int numberOfWhiteSquares) {
        this.numberOfWhiteSquares = numberOfWhiteSquares;
    }

    public Array<colorNumber> getCountOfColors() {
        return countOfColors;
    }

    public Array<Color> getColorArray() {
        return colorArray;
    }

    public boolean isFull() {
        return numberOfWhiteSquares == 0;
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
