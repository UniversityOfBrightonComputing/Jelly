package uk.ac.brighton.ab607.jelly.global;

public class Global
{
	public static final int W = 1280;
	public static final int H = 720;
	
	public static final int SPRITE_SIZE = 64;
	
	public static final int SCORE_COIN = 100;
	public static final int SCORE_BONUS = SCORE_COIN * 10;
	
	public static final String PATH_RESOURCES = System.getProperty("user.dir") + "/game/res/";
	
	public static final String APP_AUTHOR = "Almas";
	public static final String APP_DESIGNER = "Atheryos";
	public static final String APP_VERSION = "0.57";
	public static final String APP_TITLE = "Jelly Project " + APP_VERSION + " | Author: " + APP_AUTHOR + " | Designer: " + APP_DESIGNER;
}