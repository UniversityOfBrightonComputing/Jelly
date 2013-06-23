package jelly;

import java.awt.image.BufferedImage;

/**
 * Represents a static game object.
 * i.e. a background, origin
 * If you want to create an object you can interact with (level object)
 * use GameEntity instead
 * @author Almas
 * @version 1.0
 */
public class GameObject implements Renderable
{
  private int x = 0, y = 0;
	private BufferedImage image;
	
	public GameObject(int x, int y, BufferedImage image)
	{
		this.x = x; this.y = y;
		this.image = image;
	}
	
	public GameObject(int x, int y)	{
		this(x, y, GameResources.IMG_NULL);
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void updateXY(int x, int y) {
		this.x += x; this.y += y;
	}
}
