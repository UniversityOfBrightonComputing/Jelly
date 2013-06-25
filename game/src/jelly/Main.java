package jelly;

/**
 * Entry point of the game
 * Exists just to initialise game's core classes
 * and to start the game
 * @author Almas
 * @version 1.0
 */
public class Main
{
  public static void main(String[] args)
	{
		KeyInput keyboard = new KeyInput();
		
		GameModel model = new GameModel(keyboard);
		GameView view = new GameView();
		
		view.addKeyListener(keyboard);
		
		model.addObserver(view);
	}
}
