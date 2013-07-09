package uk.ac.brighton.ab607.jelly;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import uk.ac.brighton.ab607.jelly.io.Out;
import static uk.ac.brighton.ab607.jelly.global.Global.PATH_RESOURCES;

/**
 * Loads and holds all the game resources
 * Can be accessed to retrieve specific res
 * @author Almas
 * @version 1.0
 */
public class GameResources
{	
	public static final BufferedImage IMG_NULL;	//a 1x1 transparent null image
	
	public static final BufferedImage IMG_PLAYER;
	
	public static final BufferedImage IMG_BACKGROUND;
	
	public static final BufferedImage IMG_PLATFORM_UP;
	public static final BufferedImage IMG_PLATFORM_DOWN;
	
	public static final BufferedImage IMG_COIN;
	public static final BufferedImage IMG_PORTAL;
	
	public static final BufferedImage[] IMG_ANIMATION_PLAYER = new BufferedImage[11];
	public static final BufferedImage[] IMG_ANIMATION_COIN = new BufferedImage[1];
	public static final BufferedImage[] IMG_ANIMATION_ENEMY = new BufferedImage[1];
	
	/**
	 * Loads all necessary in-game resources
	 * such as images and sounds
	 */
	static
	{
		/**
		 * LOADING IMAGES
		 */
		IMG_NULL = getImg("null.png");
		IMG_PLAYER = getImg("player.png");
		IMG_BACKGROUND = getImg("bg.jpg");
		IMG_PORTAL = getImg("portal.png");
		IMG_PLATFORM_UP = getImg("platformup.png");
		IMG_PLATFORM_DOWN = getImg("platformdown.png");
		IMG_COIN = getImg("coin.png");
		
		for (int i = 0; i < IMG_ANIMATION_PLAYER.length; i++)
			IMG_ANIMATION_PLAYER[i] = getImg("animation_player/walk" + (i+1) + ".png");
		
		IMG_ANIMATION_COIN[0] = IMG_COIN;
		IMG_ANIMATION_ENEMY[0] = getImg("enemy.png");
	}
	
	/**
	 * Loads an image from a file
	 * @param fileName - full or relative pathName + fileName
	 * @return - an image if loaded, exits program if can't
	 */
	private static BufferedImage getImg(String fileName)
	{
		fileName = PATH_RESOURCES + "images/" + fileName;
		
		try {
			return ImageIO.read(new File(fileName));
		}
		catch (Exception e)
		{
			try {
				return ImageIO.read(GameResources.class.getResource(fileName));
			}
			catch (Exception ex)
			{
				Out.err("Couldn't load game image: " + fileName);
				System.exit(-1);
			}
		}
		
		return null;
	}
	
	/**
	 * Loads an audiofile
	 * @param fileName - pathName + fileName
	 * @return - audiofile or exits if cant load
	 */
	@SuppressWarnings("unused")
	private static AudioClip getAudioFile(String fileName)
	{
		try 
		{
			return Applet.newAudioClip(new File(fileName).toURI().toURL());
			//return Applet.newAudioClip(Resources.class.getResource(fileName));
		}
		catch (Exception e) 
		{
			Out.err("Couldn't load game audio: " + fileName); 
			System.exit(-1);
		}
		return null;
	}

}