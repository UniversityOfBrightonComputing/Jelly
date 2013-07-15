package uk.ac.brighton.ab607.jelly;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

import uk.ac.brighton.ab607.jelly.graphics.GraphicObject;
import static uk.ac.brighton.ab607.jelly.global.Global.*;

/**
 * Displays a graphical view of the game
 * @author Almas
 * @version 0.9
 */
@SuppressWarnings("serial")
public class View extends JFrame implements Observer {
    private final ArrayList<GraphicObject> staticRenderedObjects;
	private ArrayList<GraphicObject> dynamicRenderedObjects = new ArrayList<GraphicObject>();
	
	public View(ArrayList<GraphicObject> staticRenderedObjects) {	
		setSize(W, H); // Size of window
		setTitle(APP_TITLE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.staticRenderedObjects = staticRenderedObjects;
	}

	/**
	 * Code called to create the current state of the game
	 * @param g - Graphics context to use
	 */
	private void renderPicture(Graphics2D g) {		
	    for (GraphicObject obj : staticRenderedObjects) {
	        render(g, obj);
        }
	       
		for (GraphicObject obj : dynamicRenderedObjects) {
		    render(g, obj);
		}
	}
	
	/**
	 * Draws an object onto graphical space(context)
	 * @param g - Graphics context to use
	 * @param obj - object to draw
	 */
	private void render(Graphics2D g, GraphicObject obj) {
	    g.drawImage(obj.getImage(), obj.getRenderX(), obj.getRenderY(), this);
	    //g.drawRect(obj.getRenderX(), obj.getRenderY(), obj.getImage().getWidth(), obj.getImage().getHeight());    //DEBUG
	}
	
	/**
	 * Called from the model when its state has changed
	 */
	@Override
	public void update(Observable model, Object arg) {
		dynamicRenderedObjects = ((Model) model).getDynamicRenderedObjects();
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

	private BufferedImage theAI; // Alternate (double buffer) Image
	private Graphics2D theAG; 	 // Alternate (double buffer) Graphics

	/**
	 * Double buffer drawing to avoid flicker
	 * @param g - graphics context
	 */
	public void drawPicture(Graphics2D g) {	
		if (theAG == null)
		{
			theAI = (BufferedImage) createImage(W, H);
			theAG = theAI.createGraphics();
		}
		
		renderPicture(theAG); 			// Create new picture

		g.drawImage(theAI, 0, 0, this); // Display on screen previous picture
	}
}
