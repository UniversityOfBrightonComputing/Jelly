package uk.ac.brighton.ab607.jelly;

import java.util.ArrayList;
import java.util.Observable;

import uk.ac.brighton.ab607.jelly.graphics.GraphicObject;
import uk.ac.brighton.ab607.jelly.graphics.hud.HUD;
import uk.ac.brighton.ab607.jelly.io.KeyInput;
import uk.ac.brighton.ab607.jelly.GameLevel.LevelObject;

/**
 * Static model of the game
 * @author Almas
 * @version 0.9
 */
public class Model extends Observable
{	
	private GameLevel level = new GameLevel(0);	//current level
	
	/**
	 * Objects that won't change their screen position during game
	 * e.g. HUD
	 */
	private ArrayList<GraphicObject> staticRenderedObjects = new ArrayList<GraphicObject>();
	
	/**
	 * Objects that will change their screen position during game
	 * e.g. player, platforms, monsters
	 */
	private ArrayList<GameObject> dynamicRenderedObjects = new ArrayList<GameObject>();
	
	/**
	 * Constructs an instance of GameModel and runs the thread associated
	 * with the dynamic part of this model
	 * @param controller - the keyboard input used by the view observing this model
	 */
	public Model(KeyInput controller) {
		Thread currentGameThread = new Thread(new Game(this, controller));
		currentGameThread.start();
		
		HUD hud = new HUD();
		this.addObserver(hud);

		staticRenderedObjects.addAll(hud.getObjects());
	}
	
	/**
	 * Changes state of the model to a new level
	 */
	public void newLevel() {
		dynamicRenderedObjects.clear();	//clear old level's objects from rendered list
		
		level = new GameLevel(level.getValue() + 1);	//create new level

		//add objects of the new level to the rendered list
		for (LevelObject obj : LevelObject.values())
			for (GameObject gameObj : level.getGameObject(obj))
				dynamicRenderedObjects.add(gameObj);
	}
	
	/**
	 * @return - current level
	 */
	public GameLevel getLevel() {
		return level;
	}
	
	/**
	 * Returns a copy (not the same reference) of rendered objects to avoid concurrentModificationException
	 * It won't be necessary but you are allowed to modify the list, but NOT the objects within the list
	 * because although the list reference is different, the objects' references inside the list
	 * refer to the original objects
	 * 
	 * The list returned will only contain objects that are renderable
	 * at this moment (i.e. not dead)
	 * @return - list of all objects that need to be rendered now
	 */
	public ArrayList<GraphicObject> getRenderedObjects() {
	    ArrayList<GraphicObject> tmp = new ArrayList<GraphicObject>(staticRenderedObjects);
	    
	    for (GameObject obj : dynamicRenderedObjects)
	        if (obj.isAlive())
	            tmp.add(obj);

		return new ArrayList<GraphicObject>(tmp);
	}
	
	/**
	 * Update state of all observers of this class
	 */
	public void modelChanged() {
		setChanged();
		notifyObservers();
	}
}
