package uk.ac.brighton.ab607.jelly.graphics;

import java.awt.Point;
import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.io.Out;

public abstract class GraphicObject
{
	protected int x, y;
	private Point origin;
	
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
	
	public void updateXY(int x, int y) {
	    this.x += x; this.y += y;
	}
	
	public abstract BufferedImage getImage();
}
