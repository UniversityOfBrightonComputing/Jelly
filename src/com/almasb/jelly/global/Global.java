package com.almasb.jelly.global;

import java.awt.Point;

public class Global {
	public static final int W = 1280;
	public static final int H = 720;
	
	public static final int SPRITE_SIZE = 64;
	
	public static final Point POINT_PLAYER_DEFAULT = new Point(0, H - 2*SPRITE_SIZE);
	
	public static final int SCORE_COIN = 100;
	public static final int SCORE_BONUS = SCORE_COIN * 10;
	
	public static final String PATH_RESOURCES = System.getProperty("user.dir") + "/game/res/";
	
	public static final String APP_AUTHOR = "Almas";
	public static final String APP_DESIGNER = "Atheryos";
	public static final String APP_VERSION = "dev0.65c";
	public static final String APP_TITLE = "Jelly Project " + APP_VERSION + " | Author: " + APP_AUTHOR + " | Designer: " + APP_DESIGNER;
}