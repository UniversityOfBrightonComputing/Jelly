package uk.ac.brighton.ab607.jelly.graphics;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.GameResources;
import uk.ac.brighton.ab607.jelly.global.Global;

public class Renderer {
	public static BufferedImage createGraphics(String text) {
		BufferedImage img = new BufferedImage(150, 125, GameResources.IMG_NULL.getType());
		
		Graphics2D g = img.createGraphics();
		g.setFont(new Font("Neuropol", Font.BOLD, 20));
		g.drawString(text, 0, 50);
		g.dispose();
		
		return img;
	}

    public static BufferedImage createGraphics(BufferedImage image, int times) {
        BufferedImage img = new BufferedImage(image.getWidth()*times, image.getHeight(), GameResources.IMG_NULL.getType());
        
        Graphics2D g = img.createGraphics();
        for (int i = 0; i < times; i++) {
            g.drawImage(image, i * image.getWidth(), 0, null);
        }
        g.dispose();
        
        return img;
    }
}
