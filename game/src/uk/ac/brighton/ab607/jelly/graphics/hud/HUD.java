package uk.ac.brighton.ab607.jelly.graphics.hud;

import static uk.ac.brighton.ab607.jelly.GameResources.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import uk.ac.brighton.ab607.jelly.Model;
import uk.ac.brighton.ab607.jelly.Player;
import uk.ac.brighton.ab607.jelly.io.Out;


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
    
    private HudObject background = new HudObject(0, 0, IMG_BACKGROUND);
    private HudText levelText = new HudText(100, 150, "test");
    private HudObject hudLives = new HudObject(100, 100, IMG_HUD_LIVES, 3);
    
    public HUD() {
        hudObjects.add(background);
        hudObjects.add(levelText);
        hudObjects.add(hudLives);
    }
    
    public ArrayList<HudObject> getObjects() {
        return hudObjects;
    }

    @Override
    public void update(Observable model, Object arg) {
        final Player player = ((Model) model).getLevel().getPlayer();
        levelText.setText("rend. test");
       // Out.msg("called from update: " + player.getLives());
        hudLives.setTimes(player.getLives());
    }
}
