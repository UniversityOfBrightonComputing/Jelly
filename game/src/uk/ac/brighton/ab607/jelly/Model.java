package uk.ac.brighton.ab607.jelly;

import static uk.ac.brighton.ab607.jelly.global.Global.H;
import static uk.ac.brighton.ab607.jelly.global.Global.SPRITE_SIZE;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import uk.ac.brighton.ab607.jelly.graphics.GraphicObject;
import uk.ac.brighton.ab607.jelly.graphics.hud.HUD;
import uk.ac.brighton.ab607.jelly.graphics.hud.HudObject;
import uk.ac.brighton.ab607.jelly.io.KeyInput;

/**
 * Static model of the game
 * @author Almas
 * @version 1.0
 */
public class Model extends Observable {
    /**
     * The origin used for rendering objects on the screen
     * As player moves the origin will change, thus
     * allowing other objects appropriately draw themselves
     * because their relative position on the screen is based
     * on this origin
     */
    public final Point origin = new Point();
    
    /**
     * Current game level, this object holds information
     * about all game objects that belong to it except player
     */
	private GameLevel level = new GameLevel(0, origin);
	
	/**
	 * The player, this is the only instance of player during the
	 * whole game and is independent from level
	 */
	public final Player player = new Player(0, H - 2*SPRITE_SIZE, origin, GameResources.IMG_ANIMATION_PLAYER);
	
	/**
	 * HudObjects that won't change their relative screen position during game
	 */
	private final ArrayList<HudObject> staticRenderedObjects;
	
	/**
	 * Objects that will change their relative screen position during game
	 * e.g. player, platforms, monsters
	 */
	private ArrayList<GameObject> dynamicRenderedObjects = new ArrayList<GameObject>();
	
	private final Game game;
	
	/**
	 * Constructs an instance of GameModel and runs the thread associated
	 * with the dynamic part of this model
	 * @param controller - the keyboard input used by the view observing this model
	 */
	public Model(KeyInput controller) {
	    game = new Game(this, controller);
	    new Thread(game).start();  //run using separate thread	
		HUD hud = new HUD(this);  //pass this object to hud, so hud can observe it
		staticRenderedObjects = hud.getObjects(); //get objects from hud
	}
	
	/**
	 * Changes state of the model to a new level
	 */
	public void newLevel() {
	    resetState();
		level = new GameLevel(level.value + 1, origin);	//create new level
		dynamicRenderedObjects = level.getGameObjects();  //get game objects from new level
		dynamicRenderedObjects.add(player);   //add player to them
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
        
        //check if game objects alive, we don't want to display dead ones
        for (GameObject obj : dynamicRenderedObjects)
            if (obj.isAlive())
                tmp.add(obj);

        return new ArrayList<GraphicObject>(tmp);
    }
    
    /**
     * Ask model to place the player back to where he started
     * and reset origin
     */
    public void resetState() {
        player.resetPosition();    //reset player's position
        origin.x = 0;  //reset origin
    }
	
	/**
	 * @return - current level
	 */
	public GameLevel getLevel() {
		return level;
	}
	
	public boolean isGameRunning() {
	    return game.running;
	}
	
	/**
	 * Update state of all observers of this class
	 */
	public void modelChanged() {
		setChanged();
		notifyObservers();
	}
}
