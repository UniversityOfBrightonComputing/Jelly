package jelly;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Essentially a keyboard manipulation/input handler
 * To use add a key listener to the window with an
 * instance of this class e.g.
 * <code>jframe.addKeyListener(new KeyInput());</code>
 * @author Almas
 * @version 1.0
 */
public class KeyInput implements KeyListener
{
  public enum GameEvent
	{
		NONE,
		PLAYER_MOVE_LEFT,
		PLAYER_MOVE_RIGHT,
		PLAYER_JUMP
	}
	
	private GameEvent event = GameEvent.NONE;
	private HashMap<GameEvent, Boolean> events = new HashMap<GameEvent, Boolean>();
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{		
			case KeyEvent.VK_LEFT:
				event = GameEvent.PLAYER_MOVE_LEFT;
				break;
				
			case KeyEvent.VK_RIGHT:
				event = GameEvent.PLAYER_MOVE_RIGHT;
				break;
				
			case KeyEvent.VK_SPACE: case KeyEvent.VK_UP:
				event = GameEvent.PLAYER_JUMP;
				break;
				
			default:
				return;
		}
		
		events.put(event, true);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{		
			case KeyEvent.VK_LEFT:
				event = GameEvent.PLAYER_MOVE_LEFT;
				break;
				
			case KeyEvent.VK_RIGHT:
				event = GameEvent.PLAYER_MOVE_RIGHT;
				break;
				
			case KeyEvent.VK_SPACE: case KeyEvent.VK_UP:
				event = GameEvent.PLAYER_JUMP;
				break;
				
			default:
				return;
		}
		
		events.put(event, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * @return - the current event map
	 */
	public HashMap<GameEvent, Boolean> getEvents() {
		return events;
	}
}
