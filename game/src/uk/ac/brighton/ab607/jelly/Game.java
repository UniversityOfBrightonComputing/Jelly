package uk.ac.brighton.ab607.jelly;

import static uk.ac.brighton.ab607.jelly.global.Global.W;

import java.awt.Point;
import java.util.HashMap;

import uk.ac.brighton.ab607.jelly.debug.Debug;
import uk.ac.brighton.ab607.jelly.gameobject.GameObject;
import uk.ac.brighton.ab607.jelly.gameobject.Player;
import uk.ac.brighton.ab607.jelly.global.Global;
import uk.ac.brighton.ab607.jelly.io.KeyInput;
import uk.ac.brighton.ab607.jelly.io.KeyInput.GameEvent;


/**
 * A dynamic representation of the game
 * @author - Almas
 * @version - 0.8
 */
public class Game implements Runnable {
	private Model model;
	private KeyInput controller;
	
	private GameLevel level;
	private Player player;
	private GameObject[] platforms;
	
	//TO DO integrate origin into player, so when player moves, origin moves too
	private Point origin;
	
	private boolean running = true;
	private boolean levelRunning = true;
	
	public Game(Model model, KeyInput controller) {
		this.model = model;
		this.controller = controller;
        player = model.player;
        origin = player.origin;
	}
	
	/**
	 * A representation of the main game loop
	 * If it stops, the game stops, program exits
	 **/
	@Override
	public void run() {
		while (running)
		{
			level = model.newLevel();
			player.reset();
			
			platforms = new GameObject[level.platforms.size()];
			level.platforms.toArray(platforms);
			
			levelRunning = true;
			
			while (levelRunning)		    
			{
			    long start = System.currentTimeMillis();
			     
				PhysicsEngine.Gravity.pull(player, platforms);
				
				for (GameObject enemy : level.enemies) {
                    if (player.isColliding(enemy)) {
                        player.setDead();
                        break;
                    }
                }
				
				if (!player.isAlive()) {
				    if (player.getLives() > 0) {
                        player.addToLives(-1);
                        player.reset();
                    }
                    else {
                        stopLevel();
                        stopGame();
                    }
				}

				
				if (player.isJumping())
					player.jump(platforms);
				
				if (player.isColliding(level.portals.get(0))) {
				    stopLevel();
				}
				
				for (GameObject coin : level.coins) {
					if (player.isColliding(coin) && coin.isAlive()) {
					    coin.setDead();
					    player.addToScore(Global.SCORE_COIN);
					}
				}

				
				handleGameEvents();

				model.modelChanged();
				
				long finish = System.currentTimeMillis()-start;
				if (finish > 0)
				    Debug.msg("Performance report: " + finish + " ms");
			
				sleep();
			}
		}
	}
	
	/**
	 * Game events handler, will do for the moment
	 */
	private void handleGameEvents() {
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
	public void sleep() {
		try {Thread.sleep(17);} 
		catch (InterruptedException ie) {
		    ie.printStackTrace();
		};
	}
	
	public boolean isRunning() {
	    return running;
	}
	
	/**
	 * Stops current level
	 */
	public void stopLevel() {
	    levelRunning = false;
	}
	
	/**
	 * Stops the main game loop, resulting in program ending
	 **/
	public void stopGame() {
		running = false;
	}
}
