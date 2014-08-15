package com.almasb.jelly.graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class GraphicObject {
    /**
     * Top left X, Y coordinates
     */
    private int x, y;

    /**
     * Constructs a graphics object at x, y location
     * 
     * @param x
     *            - top left X coordinate
     * @param y
     *            - top left Y coordinate
     */
    public GraphicObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GraphicObject(Point position) {
        this(position.x, position.y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return - top left X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return - top left Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Increment top left X and Y by given values
     * 
     * @param x
     *            - X
     * @param y
     *            - Y
     */
    public void updateXY(int x, int y) {
        this.x += x;
        this.y += y;
    }

    /**
     * @return - image of this object
     */
    public abstract BufferedImage getImage();

    /**
     * @return - true if object's position is relative to the screen, false if
     *         object's position is dynamic
     */
    public abstract boolean isStatic();
}
