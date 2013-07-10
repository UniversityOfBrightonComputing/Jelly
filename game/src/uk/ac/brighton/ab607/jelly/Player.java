package uk.ac.brighton.ab607.jelly;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    private int score = 0, lives = 3;

    public Player(int x, int y, Point origin, BufferedImage[] animation) {
        super(x, y, origin, animation);
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }
}
