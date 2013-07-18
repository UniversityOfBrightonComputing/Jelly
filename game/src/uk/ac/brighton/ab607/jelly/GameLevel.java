package uk.ac.brighton.ab607.jelly;

import java.util.ArrayList;

import static uk.ac.brighton.ab607.jelly.global.Global.*;
import uk.ac.brighton.ab607.jelly.gameobject.Coin;
import uk.ac.brighton.ab607.jelly.gameobject.Enemy;
import uk.ac.brighton.ab607.jelly.gameobject.Coin.CoinType;
import uk.ac.brighton.ab607.jelly.gameobject.Enemy.EnemyType;
import uk.ac.brighton.ab607.jelly.gameobject.GameObject;
import uk.ac.brighton.ab607.jelly.gameobject.Platform;
import uk.ac.brighton.ab607.jelly.gameobject.Platform.PlatformType;
import uk.ac.brighton.ab607.jelly.gameobject.Portal;
import uk.ac.brighton.ab607.jelly.gameobject.Powerup;
import uk.ac.brighton.ab607.jelly.io.LevelReader;

/**
 * A level in the game, contains all
 * game objects read in from the text file
 * @author Almas
 * @version 0.8
 */
public class GameLevel {
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
	public final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public final ArrayList<Coin> coins = new ArrayList<Coin>();
	public final ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public final ArrayList<Platform> platforms = new ArrayList<Platform>();
	public final ArrayList<Portal> portals = new ArrayList<Portal>();
	public final ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	
	/**
	 * Constructs and completely populates new level
	 * @param value - value of the level
	 */
	public GameLevel(int value) {
	    this.value = value;
		LevelReader lr = new LevelReader(value);
        length = lr.getLevelLength();
        
        String[] lines = new String[MAX_LEVEL_LINES];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lr.getLine(i);
        }
        
        populate(lines);
	}
	
	/**
	 * Populates this level with objects read
	 * from the text file
	 * @param lines - contains lines read from file
	 */
	private final void populate(String[] lines) {
	    for (int i = 0; i < 3; i++) 
	    {
            String line = lines[i];
            
            for (int j = 0; j < line.length(); j++) 
            {
                createGameObject(line.charAt(j), i, j);
            }
        }
	    
	    gameObjects.addAll(coins);
	    gameObjects.addAll(enemies);
	    gameObjects.addAll(platforms);
	    gameObjects.addAll(portals);
	    gameObjects.addAll(powerups);
	}
	
	private void createGameObject(char code, int line, int position) {
	    int x = position*SPRITE_SIZE;
	    int y = H - (MAX_LEVEL_LINES - line)*SPRITE_SIZE;
	    
	    switch (code) {
	        case Coin.ID:
	            coins.add(new Coin(x, y, CoinType.MEDIUM));
	            break;
	            
	        case Enemy.ID:
	            enemies.add(new Enemy(x, y, EnemyType.FLY));
	            break;
	            
	        case Platform.ID:
	            platforms.add(new Platform(x, y, line == MAX_LEVEL_LINES-1 ? PlatformType.LOWER : PlatformType.UPPER));
	            break;
	            
	        case Portal.ID:
	            portals.add(new Portal(x, y));
	            break;
	            
	        case Powerup.ID:
	            powerups.add(new Powerup(x, y));
	            break;
	           
            default: 
                break;
	    }
	}
}
