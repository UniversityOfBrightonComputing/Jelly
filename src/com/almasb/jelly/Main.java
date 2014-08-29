package com.almasb.jelly;

import com.almasb.java.game.Physics;
import com.almasb.java.io.Resources;

/**
 * Entry point to the game
 *
 * @author Almas Baimagambetov (ab607@uni.brighton.ac.uk)
 * @version 1.0
 *
 */
public class Main {
    public static void main(String[] args) {
        // load resources
        //Resources.init(R.drawable.class, R.raw.class);

        // set up world's physics
        Physics.ON = true;
        Physics.GRAVITY_ON = true;
        Physics.GROUND = 720;

        new GUI().init();
    }
}
