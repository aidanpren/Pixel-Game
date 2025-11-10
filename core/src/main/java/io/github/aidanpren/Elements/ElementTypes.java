package io.github.aidanpren.Elements;

public enum ElementTypes {
    EMPTY_CELL {
        @Override
        public Element createElementByMatrix(int x, int y) {
            return new EmptyCell(x, y, false);
        }

//        @Override
        public Element createElementByPixel(int x, int y) {
            return new EmptyCell(x, y, true);
        }
    };

    public abstract Element createElementByMatrix(int x, int y);
}
