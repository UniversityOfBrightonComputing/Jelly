package jelly;

import java.util.ArrayList;
import java.util.Observable;

import jelly.GameLevel.LevelObject;

/**
 * Static model of the game
 * @author Almas
 * @version 0.8
 */
public class GameModel extends Observable
{  
	private GameLevel level = new GameLevel(0);	//current level

	private ArrayList<Renderable> renderedObjects = new ArrayList<Renderable>();	//holds all objects that need to be displayed
	
	public GameModel(KeyInput controller) 
	{
		Thread currentGameThread = new Thread(new Game(this, controller));
		currentGameThread.start();
	}
	
	/**
	 * Changes state of the model to a new level
	 */
	public void newLevel()
	{
		renderedObjects.clear();	//clear old level's objects from rendered list
		renderedObjects.add(new GameObject(0, 0));	//place 1st object as the screen origin
		
		level = new GameLevel(level.getValue()+1);	//create new level

		//add objects of the new level to the rendered list
		for (LevelObject obj : LevelObject.values())
			for (Renderable renderedObj : level.getGameEntity(obj))
				renderedObjects.add(renderedObj);
	}
	
	/**
	 * Cleans up the dead game entities from rendered objects
	 * In the future will probably replace with 2 different lists
	 * static and dynamic because there's no point in iterating through
	 * static objects as they'll never be dead/altered
	 */
	public void updateRenderedObjects() 
	{
		ArrayList<Renderable> tmp = new ArrayList<Renderable>(renderedObjects);
		renderedObjects.clear();
		
		/**
		 * Logical OR, so if it isn't instance of GameEntity will evaluate to true
		 * without need for checking the rest, thus exception in casting shouldn't occur
		 */
		for (Renderable obj : tmp)
			if (!(obj instanceof GameEntity) || ((GameEntity) obj).isAlive())
				renderedObjects.add(obj);
	}
	
	/**
	 * @return - current level
	 */
	public GameLevel getLevel() {
		return level;
	}
	
	/**
	 * Returns a copy (not the same reference) of rendered objects to avoid concurrentModificationException
	 * It won't be necessary but you are allowed to modify the list, but not the objects within the list
	 * because although the list reference is different, the objects' references inside the list
	 * refer to the original objects
	 * @return - list of all objects that need to be rendered now
	 */
	public ArrayList<Renderable> getRenderedObjects() {
		return new ArrayList<Renderable>(renderedObjects);
	}
	
	/**
	 * Update state of all observers of this class
	 */
	public void modelChanged()
	{
		setChanged();
		notifyObservers();
	}
}
