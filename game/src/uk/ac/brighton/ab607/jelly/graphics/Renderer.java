package uk.ac.brighton.ab607.jelly.graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.GameResources;

public class Renderer
{
	public static BufferedImage createGraphics(String text)
	{
		BufferedImage img = new BufferedImage(150, 125, GameResources.IMG_NULL.getType());
		
		Graphics2D g = img.createGraphics();
		g.setFont(new Font("Neuropol", Font.BOLD, 20));
		g.drawString(text, 0, 50);
		g.dispose();
		
		return img;
	}
}
