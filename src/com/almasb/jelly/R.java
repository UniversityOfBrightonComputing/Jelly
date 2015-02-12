package com.almasb.jelly;

import com.almasb.java.io.ResourceManager;

import javafx.scene.image.Image;

/**
 * Android compatibility
 *
 * @author Almas Baimagambetov (ab607@uni.brighton.ac.uk)
 * @version 1.0
 *
 */
public class R {
    public static final class drawable {
        public static final int platformup = 0;
        public static final int powerup = 1;
        public static final int enemy = 2;
        public static final int coin = 3;
        public static final int background = 4;
        public static final int player = 5;
        public static final int lives = 6;
        public static final int bg_clouds = 7;
        public static final int bg_back = 8;
        public static final int platformdown = 9;
    }

    public static final class raw {

    }

    private static final Image[] images = new Image[20];

    public static Image getImage(int resID) {
        return images[resID];
    }

    static {
        images[drawable.platformup] = ResourceManager.loadFXImage("platformup.png");
        images[drawable.platformdown] = ResourceManager.loadFXImage("platformdown.png");
        images[drawable.powerup] = ResourceManager.loadFXImage("powerup.png");
        images[drawable.enemy] = ResourceManager.loadFXImage("enemy.png");
        images[drawable.coin] = ResourceManager.loadFXImage("coin.png");
        images[drawable.background] = ResourceManager.loadFXImage("bg.png");
        images[drawable.player] = ResourceManager.loadFXImage("player.png");
        images[drawable.lives] = ResourceManager.loadFXImage("lives.png");
        images[drawable.bg_clouds] = ResourceManager.loadFXImage("bg_clouds.png");
        images[drawable.bg_back] = ResourceManager.loadFXImage("bg_back.png");
    }
}
