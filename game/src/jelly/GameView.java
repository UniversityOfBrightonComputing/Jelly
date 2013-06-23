package jelly;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

import static jelly.Global.*;

/**
 * Displays a graphical view of the game
 * @author Almas
 * @version 0.8
 */
@SuppressWarnings("serial")
public class GameView extends JFrame implements Observer
{
  private ArrayList<Renderable> renderedObjects = new ArrayList<Renderable>();	//all objects that need to be rendered
	private int xOrigin = 0;	//origin relative to which objects need to be drawn
	
	public GameView()
	{	
		setSize(W, H); // Size of window
		setTitle(APP_TITLE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Code called to create the current state of the game
	 * @param g - Graphics context to use
	 */
	public void createPicture(Graphics2D g)
	{
		g.drawImage(GameResources.IMG_BACKGROUND, 0, 0, this);	//CHANGE TO A GAME OBJECT
		
		for (Renderable obj : renderedObjects)
			render(g, obj);
	}
	
	/**
	 * Renders (but doesn't show on the screen, just creates)
	 * a renderable object
	 * @param g - graphics context on which to render
	 * @param obj - the object
	 */
	public void render(Graphics2D g, Renderable obj) {
		g.drawImage(obj.getImage(), obj.getX() - xOrigin, obj.getY(), this);
	}
	
	/**
	 * Called from the model when its state has changed
	 */
	@Override
	public void update(Observable aModel, Object arg)
	{
		GameModel model = (GameModel) aModel;
		renderedObjects = model.getRenderedObjects();
		xOrigin = renderedObjects.get(0).getX();	//we always place a dummy game object origin as the 1st element
		
		repaint();
	}
	
	/**
	 * Called by repaint
	 */
	@Override
	public void update(Graphics g) {
		drawPicture((Graphics2D) g);
	}

	/**
	 * When first shown or damaged
	 */
	@Override
	public void paint(Graphics g) {
		drawPicture((Graphics2D) g);
	}

	private BufferedImage theAI; // Alternate Image
	private Graphics2D theAG; 	 // Alternate Graphics

	/**
	 * Double buffer drawing to avoid flicker
	 * @param g - graphics context
	 */
	public void drawPicture(Graphics2D g)
	{	
		if (theAG == null)
		{
			theAI = (BufferedImage) createImage(W, H);
			theAG = theAI.createGraphics();
		}
		
		createPicture(theAG); 			// Create new picture

		g.drawImage(theAI, 0, 0, this); // Display on screen previous picture
	}
}
