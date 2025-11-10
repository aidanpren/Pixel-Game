package io.github.aidanpren;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.aidanpren.Elements.Element;
import io.github.aidanpren.Elements.ElementTypes;

import java.util.BitSet;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    private static int screenWidth = 500;
    private static int screenHeight = 500;
    public static int pixelSizeModifier = 1;

    private Array<Array<Element>> matrix;
    private OrthographicCamera camera;
    public int outerArraySize;
    public int innerArraySize;

    public static BitSet stepped = new BitSet(1);

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);

        stepped.set(0, true);
        matrix = generateMatrix();
    }

    @Override
    public void render() {
        stepped.flip(0);


        for (int y = 0; y < matrix.size; y++) {
            Array<Element> row = matrix.get(y);
            for (int x = 0; x < row.size; x++) {
                Element element = row.get(x);
                if (element != null) {
                    element.step(matrix);
                }
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    private Array<Array<Element>> generateMatrix() {
        outerArraySize = (int) Math.floor(screenHeight / pixelSizeModifier);
        innerArraySize = (int) Math.floor(screenWidth / pixelSizeModifier);
        Array<Array<Element>> outerArray = new Array<>(true, outerArraySize);
        for (int y = 0; y < outerArraySize; y++) {
            Array<Element> innerArr = new Array<>(true, innerArraySize);
            for (int x = 0; x < innerArraySize; x++) {
                innerArr.add(ElementTypes.EMPTY_CELL.createElementByMatrix(x, y));
            }
            outerArray.add(innerArr);
        }
        return outerArray;
    }
}
