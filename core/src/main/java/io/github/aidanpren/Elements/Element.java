package io.github.aidanpren.Elements;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import io.github.aidanpren.Main;

import java.util.BitSet;

public abstract class Element {
    private int pixelX;
    private int pixelY;

    private int matrixX;
    private int matrixY;

    public BitSet stepped = new BitSet(1);

    public Element(int x, int y, boolean isPixel) {
        if (isPixel) {
            setCoordinatesByPixel(x, y);
        } else {
            setCoordinatesByMatrix(x, y);
        }
        stepped.set(0, Main.stepped.get(0));
    }

    public abstract void draw(ShapeRenderer sr);

    public abstract void step(Array<Array<Element>> matrix);

    public void swapPositions(Array<Array<Element>> matrix, Element toSwap) {
        int toSwapMatrixX = toSwap.matrixX;
        int toSwapMatrixY = toSwap.matrixY;
        matrix.get(this.matrixY).set(this.matrixX, toSwap);
        toSwap.setCoordinatesByMatrix(this.matrixX, this.matrixY);
        matrix.get(toSwapMatrixY).set(toSwapMatrixX, this);
        this.setCoordinatesByMatrix(toSwapMatrixX, toSwapMatrixY);
    }

    public void setCoordinatesByMatrix(int providedX, int providedY) {
        setXByMatrix(providedX);
        setYbyMatrix(providedY);
    }

    public void setCoordinatesByPixel(int providedX, int providedY) {
        setXByPixel(providedX);
        setYByPixel(providedY);
    }

    public void setXByPixel(int providedVal) {
        this.pixelX = providedVal;
        this.matrixX = toMatrix(providedVal);
    }

    public void setYByPixel(int providedVal) {
        this.pixelY = providedVal;
        this.matrixY = toMatrix(providedVal);
    }

    public void setXByMatrix(int providedVal) {
        this.matrixX = providedVal;
        this.pixelX = toPixel(providedVal);
    }

    public void setYbyMatrix(int providedVal) {
        this.matrixY = providedVal;
        this.pixelY = toPixel(providedVal);
    }

    private int toMatrix(int pixelVal) {
        return (int) Math.floor(pixelVal / Main.pixelSizeModifier);
    }

    private int toPixel(int pixelVal) {
        return (int) Math.floor(pixelVal * Main.pixelSizeModifier);
    }



}
