package uk.ac.brighton.ab607.jelly;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static uk.ac.brighton.ab607.jelly.global.Global.*;
import uk.ac.brighton.ab607.jelly.io.LevelReader;
import static uk.ac.brighton.ab607.jelly.GameResources.*;

/**
 * A level in the game, contains all
 * game objects read in from the text file
 * @author Almas
 * @version 0.5
 */
public class GameLevel {
	public enum GameObjectType {
	    COIN('2', IMG_COIN),
	    PLATFORM('1', IMG_PLATFORM_UP),
	    ENEMY('3', IMG_ENEMY),
	    PORTAL('9', IMG_PORTAL);
	    
	    final char code;
	    final BufferedImage image;
	    private ArrayList<GameObject> list = new ArrayList<GameObject>();
	    
	    public void clear() {
	        list.clear();
	    }
	    
	    public void add(GameObject obj) {
	        list.add(obj);
	    }
	    
	    public ArrayList<GameObject> getObjects() {
	        return new ArrayList<GameObject> (list);
	    }
	    
	    private GameObjectType(char code, BufferedImage image) {
	        this.code = code;
	        this.image = image;
	    }
	};
	
	/**
	 * The number of lines in the level file
	 * (from top to bottom):
	 * 1st line above player
	 * 2nd line same level above ground as player
	 * 3rd line below player
	 * 
	 * Need to changed if more level lines are introduced
	 */
	private static final int MAX_LEVEL_LINES = 3;
	
	/**
	 * <code>value</code> - numeric value of this level
	 * 1 is the first level where game starts
	 * 2 - level 2, etc
	 * 
	 * <code>length</code> - length of this level in pixels
	 */
	public final int value, length;
	
	/**
	 * After this level has been initialised, this list
	 * will contain all game objects that belong to this level
	 */
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	/**
	 * Constructs and completely populates new level
	 * @param level - value of the level (level 1, 2, etc)
	 * @param origin - the point relative to which game objects will be created
	 */
	public GameLevel(int level, Point origin) {
	    for (GameObjectType type : GameObjectType.values()) {
	        type.clear();
        }
		value = level;
		LevelReader lr = new LevelReader(value);
        length = lr.getLevelLength();
        
        String[] lines = new String[MAX_LEVEL_LINES];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lr.getLine(i);
        }
        
        populate(lines, origin);
	}
	
	/**
	 * Populates this level with objects read
	 * from the text file
	 * @param lines - contains lines read from file
	 * @param origin - origin relative to which place objects
	 */
	private final void populate(String[] lines, Point origin) {
	    for (int i = 0; i < 3; i++) 
	    {
            String line = lines[i];
            
            for (int j = 0; j < line.length(); j++) 
            {
                char c = line.charAt(j);
                
                for (GameObjectType type : GameObjectType.values()) 
                {
                    if (c == type.code) {
                        GameObject obj = new GameObject(j*SPRITE_SIZE, H-(3-i)*SPRITE_SIZE, origin, type.image);
                        type.add(obj);
                        gameObjects.add(obj);
                        break;
                    }
                }
            }
        }
	}
	
	/**
	 * Can be used mainly for rendering them, since it does
	 * contain all objects but the type and order are different
     * @return - ALL game objects on this level
     */
    public ArrayList<GameObject> getGameObjects() {
        return new ArrayList<GameObject>(gameObjects);
    }
    
    /**
     * Unlike <code>getGameObjects()</code> this can be
     * used to retrieve only 1 type of objects
     * @param type - specific type to return
     * @return - objects on this level
     */
    public GameObject[] getGameObject(GameObjectType type) {
        GameObject[] tmp = new GameObject[type.list.size()];
        type.list.toArray(tmp);
        return tmp;
    }
}
