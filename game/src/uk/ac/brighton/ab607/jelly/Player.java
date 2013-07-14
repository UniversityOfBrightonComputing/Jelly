package uk.ac.brighton.ab607.jelly;

import static uk.ac.brighton.ab607.jelly.global.Global.H;
import static uk.ac.brighton.ab607.jelly.global.Global.SPRITE_SIZE;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * The player with all his unique abilities
 * and fields. At a later stage other game objects
 * might get some of these abilities
 * @author Almas
 * @version 0.1
 */
public class Player extends GameObject {
    private int score = 0, lives = 3;

    public Player(int x, int y, Point origin, BufferedImage[] animation) {
        super(x, y, origin, animation);
    }
    
    public void addToLives(int lives) {
        this.lives += lives;
    }
    
    public void addToScore(int score) {
        this.score += score;
    }

    public int getLives() {
        return lives;
    }
    
    public int getScore() {
        return score;
    }
    
    @Override
    public boolean isAlive() {
        return this.getY() < H;
    }

    public void resetPosition() {
        this.x = 0;
        this.y = H - 2*SPRITE_SIZE;
    }
}
