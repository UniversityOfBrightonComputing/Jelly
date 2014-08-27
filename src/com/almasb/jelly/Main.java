package com.almasb.jelly.v2;

import com.almasb.java.game.Physics;
import com.almasb.java.io.Resources;
import com.almasb.jelly.R;

public class Main {
    public static void main(String[] args) {
        // load resources
        Resources.init(R.drawable.class, R.raw.class);

        // set up world's physics
        Physics.ON = true;
        Physics.GRAVITY_ON = true;
        Physics.GROUND = 720;

        GUI view = new GUI();
        Game game = new Game(view);


        game.start();
    }
}
