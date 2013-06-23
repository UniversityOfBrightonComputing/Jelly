package jelly;

import java.awt.image.BufferedImage;

/**
 * Classes that implement this interface
 * are bound to be able to display
 * their instances on the screen
 * @author Almas
 * @version 1.1
 */
public interface Renderable
{
  public int getX();
	public int getY();
	public BufferedImage getImage();
	public void updateXY(int x, int y);
}
