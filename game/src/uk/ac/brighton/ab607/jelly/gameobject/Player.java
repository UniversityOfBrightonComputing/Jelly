package uk.ac.brighton.ab607.jelly.gameobject;

import java.awt.Point;
import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.global.Global;

/**
 * The player with all his unique abilities
 * and fields. At a later stage other game objects
 * might get some of these abilities
 * @author Almas
 * @version 0.3
 */
public class Player extends GameObject {
    private int level = 0, score = 0, lives = 3;
    
    /**
     * The origin used for rendering objects on the screen
     * As player moves the origin will change, thus
     * allowing other objects appropriately draw themselves
     * because their relative position on the screen is based
     * on this origin
     */
    public final Point origin = new Point(0, 0);

    public Player(Point defaultPosition, BufferedImage[] animation) {
        super(defaultPosition.x, defaultPosition.y, animation);
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
    
    @Override
    public boolean isAlive() {
        return super.isAlive() && this.getY() < Global.H;
    }

    public void reset() {
        resetPosition();
        this.origin.x = 0;
        setAlive(true);
    }
}
