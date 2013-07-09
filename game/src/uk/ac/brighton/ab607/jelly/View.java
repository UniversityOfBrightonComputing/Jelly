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
	private ArrayList<GraphicObject> renderedObjects = new ArrayList<GraphicObject>();	//all objects that need to be rendered
	
	public View() {	
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
	public void renderPicture(Graphics2D g) {		
		for (GraphicObject obj : renderedObjects)
		    g.drawImage(obj.getImage(), obj.getRenderX(), obj.getRenderY(), this);
	}
	
	/**
	 * Called from the model when its state has changed
	 */
	@Override
	public void update(Observable aModel, Object arg) {
		Model model = (Model) aModel;
		renderedObjects = model.getRenderedObjects();
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
