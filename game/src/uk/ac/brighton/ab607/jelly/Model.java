package uk.ac.brighton.ab607.jelly;

import java.util.ArrayList;
import java.util.Observable;

import uk.ac.brighton.ab607.jelly.gameobject.GameObject;
import uk.ac.brighton.ab607.jelly.gameobject.Player;
import uk.ac.brighton.ab607.jelly.global.Global;
import uk.ac.brighton.ab607.jelly.graphics.GraphicObject;
import uk.ac.brighton.ab607.jelly.io.KeyInput;

/**
 * Static model of the game
 * @author Almas
 * @version 1.3
 */
public class Model extends Observable {
	/**
	 * This is the only instance of player during the
	 * whole game and is independent from level
	 */
	public final Player player = new Player(Global.POINT_PLAYER_DEFAULT, GameResources.IMG_ANIMATION_PLAYER);
	
	/**
	 * Contains references to objects specific to particular level
	 */
	private ArrayList<GameObject> dynamicRenderedObjects = new ArrayList<GameObject>();
	
	/**
	 * Dynamic part of the game associated with this static model
	 */
	private final Game game;
	
	/**
	 * Constructs an instance of GameModel and runs the thread associated
	 * with the dynamic part of this model
	 * @param controller - the keyboard input used by the view observing this model
	 */
	public Model(KeyInput controller) {
	    game = new Game(this, controller);
	    new Thread(game).start();
	}
	
	/**
	 * Changes state of the model to a new level
	 */
	public GameLevel newLevel() {
		GameLevel level = new GameLevel(player.getLevel() + 1);
		player.setLevel(level.value);
		dynamicRenderedObjects = level.getGameObjects();
		return level;
	}
	
	/**
	 * @return list of alive objects from dynamic objects
	 */
	public ArrayList<GraphicObject> getAliveObjects() {
	    ArrayList<GraphicObject> tmp = new ArrayList<GraphicObject>();
	    for (GameObject obj : dynamicRenderedObjects)
            if (obj.isAlive())
                tmp.add(obj);
	    
	    return tmp;
	}

	/**
     * The list returned will only contain objects that are renderable
     * at this moment (i.e. not dead)
     * @return - list of dynamic objects that need to be rendered now
     */
    public ArrayList<GraphicObject> getDynamicRenderedObjects() {
        return getAliveObjects();
    }
	
	/**
	 * @return true if dynamic part is running, else false
	 */
	public boolean isGameRunning() {
	    return game.isRunning();
	}
	
	/**
	 * Update state of all observers of this class
	 */
	public void modelChanged() {
		setChanged();
		notifyObservers();
	}
}
