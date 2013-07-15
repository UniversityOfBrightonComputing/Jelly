package uk.ac.brighton.ab607.jelly.graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class GraphicObject {
    /**
     * Top left X, Y coordinates
     */
	protected int x, y;
	
	/**
     * The origin used for rendering objects on the screen
     * As player moves the origin will change, thus
     * allowing other objects appropriately draw themselves
     * because their relative position on the screen is based
     * on this origin
     */
	protected final Point origin;
	
	/**
     * Constructs a graphics object at x, y location
     * with the supplied origin
     * @param x - top left X coordinate
     * @param y - top left Y coordinate
     * @param origin - point of origin for this object
     */
	public GraphicObject(int x, int y, Point origin) {
		this.x = x; this.y = y;
		this.origin = origin;
	}
	
	/**
	 * Constructs a graphics object at x, y location
	 * with 0, 0 origin
	 * @param x - top left X coordinate
	 * @param y - top left Y coordinate
	 */
	public GraphicObject(int x, int y) {
	    this(x, y, new Point());
	}
	
	/**
	 * @return - top left X coordinate for drawing
	 */
	public int getRenderX() {
		return x - origin.x;
	}
	
	/**
	 * @return - top left Y coordinate for drawing
	 */
	public int getRenderY() {
		return y - origin.y;
	}
	
	/**
	 * Increment top left X and Y by given values
	 * @param x - X
	 * @param y - Y
	 */
	public void updateXY(int x, int y) {
	    this.x += x; this.y += y;
	}
	
	/**
	 * @return - image of this object
	 */
	public abstract BufferedImage getImage();
}
