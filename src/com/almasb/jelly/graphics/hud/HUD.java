package com.almasb.jelly.graphics.hud;

import static com.almasb.jelly.GameResources.*;
import static com.almasb.jelly.global.Global.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.almasb.jelly.Model;
import com.almasb.jelly.debug.Debug;
import com.almasb.jelly.gameobject.Player;

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
