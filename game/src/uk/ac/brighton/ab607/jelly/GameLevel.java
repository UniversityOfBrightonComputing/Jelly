package uk.ac.brighton.ab607.jelly;

import java.awt.Point;
import java.util.ArrayList;

import static uk.ac.brighton.ab607.jelly.global.Global.*;
import uk.ac.brighton.ab607.jelly.graphics.GraphicObject;
import uk.ac.brighton.ab607.jelly.graphics.hud.HudObject;
import uk.ac.brighton.ab607.jelly.io.LevelReader;
import static uk.ac.brighton.ab607.jelly.GameResources.*;

public class GameLevel
{
	public enum LevelObject
	{
		PLAYER, COIN, PLATFORM, ENEMY, PORTAL
	};
	
	private LevelReader lr;

	private int value = 0, length = 0;
	
	private Point origin;
	
	/**Objects that belong to this level. Can add custom objects like bonuses, mobs etc**/
	private ArrayList<GameObject> platforms = new ArrayList<GameObject>();
	private ArrayList<GameObject> portals = new ArrayList<GameObject>();
	private ArrayList<GameObject> coins = new ArrayList<GameObject>();
	private ArrayList<GameObject> enemies = new ArrayList<GameObject>();
	private ArrayList<GameObject> players = new ArrayList<GameObject>();
	
	public GameLevel(int level)
	{
		value = level;
		init();
	}
	
	private void init()
	{
		lr = new LevelReader(value);
		length = lr.getLevelLength();
		origin = new Point();
		populate();
	}
	
	private void populate()
	{
		//create player
		players.add(new Player(0, H - 2*SPRITE_SIZE, origin, IMG_ANIMATION_PLAYER));
		
		for (int i = 0; i < 3; i++)
		{
			String lines = lr.getLine(i);
			
			for (int j = 0; j < lines.length(); j++)
			{
				char c = lines.charAt(j);
				
				switch (c)
				{
					case '1':
						platforms.add(new GameObject(j*SPRITE_SIZE, H-(3-i)*SPRITE_SIZE, origin, i == 2 ? IMG_PLATFORM_DOWN : IMG_PLATFORM_UP));
						break;
						
					case '2':
						coins.add(new GameObject(j*SPRITE_SIZE, H-(3-i)*SPRITE_SIZE, origin, IMG_ANIMATION_COIN));
						break;
						
					case '3':
						enemies.add(new GameObject(j*SPRITE_SIZE, H-(3-i)*SPRITE_SIZE, origin, IMG_ANIMATION_ENEMY));
						break;
						
					case '9':
						portals.add(new GameObject(j*SPRITE_SIZE, H-(3-i)*SPRITE_SIZE, origin, IMG_PORTAL));
						break;
						
					default:
						break;
				}
			}
		}
	}
	
	public GameObject[] getGameObject(LevelObject type)
	{
		ArrayList<GameObject> list = new ArrayList<GameObject>();
		
		switch (type)
		{
			case COIN:
				list = coins;
				break;
				
			case ENEMY:
				list = enemies;
				break;
				
			case PLATFORM:
				list = platforms;
				break;
				
			case PLAYER:
				list = players;
				break;
				
			case PORTAL:
				list = portals;
				break;
		}
		
		GameObject[] tmp = new GameObject[list.size()];
		list.toArray(tmp);
		return tmp;
	}
	
	public Player getPlayer() {
	    return (Player) players.get(0);
	}
	
	public Point getOrigin() {
	    return origin;
	}
	
	/**
	 * @return - numerical value of the current level
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return - length of the current level
	 */
	public int getLength() {
		return length;
	}
}
