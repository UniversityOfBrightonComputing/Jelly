package uk.ac.brighton.ab607.jelly;

import static uk.ac.brighton.ab607.jelly.global.Global.W;

import java.awt.Point;
import java.util.HashMap;

import uk.ac.brighton.ab607.jelly.global.Global;
import uk.ac.brighton.ab607.jelly.io.KeyInput;
import uk.ac.brighton.ab607.jelly.io.KeyInput.GameEvent;
import uk.ac.brighton.ab607.jelly.GameLevel.GameObjectType;


/**
 * A dynamic representation of the game
 * @author - Almas
 * @version - 0.7
 */
public class Game implements Runnable
{
	private Model model;
	private KeyInput controller;
	
	private GameLevel level;
	private Player player;
	private GameObject[] platforms;
	
	private Point origin;
	
	private boolean running = true;
	private boolean levelRunning = true;
	
	public Game(Model model, KeyInput controller) 
	{
		this.model = model;
		this.controller = controller;
	}
	
	/**
	 * A representation of the main game loop
	 * If it stops, the game stops, program exits
	 **/
	@Override
	public void run()
	{
		while (running)
		{
			model.newLevel();
			level = model.getLevel();
			origin = model.origin;
			
			player = model.player;
			platforms = level.getGameObject(GameObjectType.PLATFORM);
			
			GameObject[] coins = level.getGameObject(GameObjectType.COIN);
			GameObject portal = level.getGameObject(GameObjectType.PORTAL)[0];
			
			levelRunning = true;
			
			while (levelRunning)
			{
				PhysicsEngine.Gravity.pull(player, platforms);
				
				if (player.getY() >= Global.H) {
				    if (player.getLives() > 0) {
				        player.setLives(player.getLives()-1);
				    }
				    player.resetPosition();
				    origin.x = 0;
				}
				
				if (player.isJumping())
					player.jump(platforms);
				
				if (player.isColliding(portal))
					levelRunning = false;
				
				for (GameObject coin : coins) {
					if (player.isColliding(coin) && coin.isAlive()) {
					    coin.setDead();
					    player.setScore(player.getScore()+Global.SCORE_COIN);
					}
				}

				
				handleGameEvents();
				model.modelChanged();
				sleep();
			}
		}
	}
	
	/**
	 * Game events handler, will do for the moment
	 */
	private void handleGameEvents()
	{
		HashMap<GameEvent, Boolean> events = controller.getEvents();

		for (GameEvent currentEvent : events.keySet())
			if (events.get(currentEvent))
				switch (currentEvent)
				{
					case NONE:
						break;
						
					case PLAYER_JUMP:
						if (player.isOnPlatform(platforms) && !player.isBelowPlatform(platforms))
							player.prepareJump();
						break;
						
					case PLAYER_MOVE_LEFT:
						updateOrigin(-player.moveX(-4, platforms));
						break;
						
					case PLAYER_MOVE_RIGHT:
						updateOrigin(player.moveX(4, platforms));
						break;
				}
	}
	
	public void updateOrigin(int dist) {
        if (player.getX() >= 0.6*W && player.getX() <= level.length - 0.4*W)
            origin.x += dist;
    }
	
	/**
	 * Stops the game from executing
	 * for 17 ms, can be made generic
	 * so gets refresh rate and sleeps
	 * 1000/refresh rate
	 */
	public void sleep() 
	{
		try {Thread.sleep(17);} 
		catch (InterruptedException ie) {
		    ie.printStackTrace();
		};
	}
	
	/**
	 * Stops the main game loop, resulting in program ending
	 **/
	public void stopGame() {
		running = false;
	}
}
