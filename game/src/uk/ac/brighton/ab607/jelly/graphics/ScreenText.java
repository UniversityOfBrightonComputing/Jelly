package uk.ac.brighton.ab607.jelly.graphics;

import java.awt.image.BufferedImage;


public class ScreenText extends GraphicObject
{
	private String text;
	private BufferedImage image;
	
	public ScreenText(int x, int y, String text) 
	{
		super(x, y);
		setText(text);
	}
	
	public void setText(String text) 
	{
		this.text = text;
		updateGraphics();
	}
	
	public String getText() {
		return text;
	}
	
	public void updateGraphics() {
		image = Renderer.createGraphics(text);
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
