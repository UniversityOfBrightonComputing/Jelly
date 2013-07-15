package uk.ac.brighton.ab607.jelly;

import java.awt.Point;
import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.global.Global;

/**
 * The player with all his unique abilities
 * and fields. At a later stage other game objects
 * might get some of these abilities
 * @author Almas
 * @version 0.2
 */
public class Player extends GameObject {
    private int level = 0, score = 0, lives = 3;

    public Player(Point defaultPosition, BufferedImage[] animation) {
        super(defaultPosition.x, defaultPosition.y, new Point(0, 0), animation);
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
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void powerUp() {
        this.maxJumpTime = 48;
    }
    
    public void resetPowers() {
        this.maxJumpTime = 16;
    }
    
    @Override
    public boolean isAlive() {
        return super.isAlive() && this.getY() < Global.H;
    }

    public void reset() {
        super.resetPosition();
        this.origin.x = 0;
        this.alive = true;
    }
    
    public Point getOrigin() {
        return this.origin;
    }
}
