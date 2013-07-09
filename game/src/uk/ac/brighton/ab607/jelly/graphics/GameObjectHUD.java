package uk.ac.brighton.ab607.jelly.graphics;

import java.awt.image.BufferedImage;


public class GameObjectHUD extends GraphicObject
{
	private BufferedImage image;
	
	public GameObjectHUD(int x, int y, BufferedImage image)
	{
		super(x, y);
		this.image = image;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void updateXY(int x, int y)
	{
		// TODO Auto-generated method stub
		
	}
}
