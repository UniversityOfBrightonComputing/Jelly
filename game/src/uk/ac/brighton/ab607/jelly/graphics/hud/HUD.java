package uk.ac.brighton.ab607.jelly.graphics.hud;

import static uk.ac.brighton.ab607.jelly.GameResources.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import uk.ac.brighton.ab607.jelly.Model;
import uk.ac.brighton.ab607.jelly.Player;
import uk.ac.brighton.ab607.jelly.global.Global;

/**
 * A set of HudObjects
 * @author Almas
 * @version 0.1
 */
public class HUD implements Observer {
    /**
     * Holds all hud objects to be shown to screen
     */
    private ArrayList<HudObject> hudObjects = new ArrayList<HudObject>();
    
    private HudStatBar background = new HudStatBar(0, 0, IMG_BACKGROUND, 1);
    private HudStatBar hudLives = new HudStatBar(100, 100, IMG_HUD_LIVES, 3);
    
    private HudText levelText = new HudText(100, 150, "");
    private HudText scoreText = new HudText((int) (0.9*Global.W), (int) (0.1*Global.H), "");
    
    public HUD(Model model) {
        model.addObserver(this);
        
        hudObjects.add(background);
        hudObjects.add(hudLives);
        hudObjects.add(levelText);
        hudObjects.add(scoreText);
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
        
        levelText.setText("dynamic text rendering test: OK");
        hudLives.setTimes(player.getLives());
        scoreText.setText(player.getScore()+"");
        
        for (HudObject obj : hudObjects) {
            obj.updateGraphics();
        }
    }
}
