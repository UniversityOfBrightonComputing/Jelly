package uk.ac.brighton.ab607.jelly;

import uk.ac.brighton.ab607.jelly.graphics.hud.HUD;
import uk.ac.brighton.ab607.jelly.io.KeyInput;

/**
 * Entry point of the game
 * Exists just to initialise game's core classes
 * and to start the game
 * @author Almas
 */
public class Main {
	public static void main(String[] args) {
		KeyInput keyboard = new KeyInput();
		
		Model model = new Model(keyboard);
		HUD hud = new HUD();
		
		View view = new View(model.player, hud.hudObjects);
		
		view.addKeyListener(keyboard);
		
		model.addObserver(view);
		model.addObserver(hud);
	}
}
