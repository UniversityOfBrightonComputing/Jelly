package uk.ac.brighton.ab607.jelly.graphics.hud;

import static uk.ac.brighton.ab607.jelly.GameResources.*;
import static uk.ac.brighton.ab607.jelly.global.Global.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import uk.ac.brighton.ab607.jelly.Model;
import uk.ac.brighton.ab607.jelly.debug.Debug;
import uk.ac.brighton.ab607.jelly.gameobject.Player;

/**
 * A set of HudObjects
 * @author Almas
 * @version 0.3
 */
public class HUD implements Observer {
    /**
     * Holds all hud objects to be shown to screen
     */
    public final ArrayList<HudObject> hudObjects = new ArrayList<HudObject>();
    
    private HudStatBar background = new HudStatBar(0, 0, IMG_BACKGROUND, 1);
    private HudStatBar hudLives = new HudStatBar(0.05*W, 0.1*H, IMG_HUD_LIVES, 3);
    
    private HudText levelText = new HudText(0.05*W, 0.15*H, "");
    private HudText scoreText = new HudText(0.9*W, 0.1*H, "");
    private HudText endGameText = new HudText(0.5*W, 0.5*H, "");
    private HudText debug = new HudText(0.6*W, 0.15*H, "");
    
    public HUD() {
        hudObjects.add(background);
        hudObjects.add(hudLives);
        hudObjects.add(levelText);
        hudObjects.add(scoreText);
        hudObjects.add(endGameText);
        hudObjects.add(debug);
    }
    
    public ArrayList<HudObject> getObjects() {
        return hudObjects;
    }

    /**
     * Called when model has changed
     */
    @Override
    public void update(Observable model, Object arg) {
        final Player player = ((Model) model).player;
        
        levelText.setText("Level " + player.getLevel());
        hudLives.setTimes(player.getLives());
        scoreText.setText(player.getScore()+"");
        debug.setText(Debug.getLastMessage());
        
        if (!((Model) model).isGameRunning()) {
            if (player.getLives() <= 0) {
                endGameText.setText("You lose");
            }
            else {
                endGameText.setText("You win");
            }
        }
        
        for (HudObject obj : hudObjects) {
            obj.updateGraphics();
        }
    }
}
