package uk.ac.brighton.ab607.jelly.gameobject;

import java.awt.Point;
import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.PhysicsEngine;
import uk.ac.brighton.ab607.jelly.PhysicsEngine.Side;
import uk.ac.brighton.ab607.jelly.graphics.GraphicObject;
import uk.ac.brighton.ab607.jelly.graphics.GraphicUtils;

public abstract class GameObject extends GraphicObject {
    
    private final Point defaultPosition;
    
    private boolean alive = true;
    
    private int animationIndex = 0;
    
    private int dir = 1;
    
    private BufferedImage[] animation;
    
    private int jumpTime = 0;
    private boolean jumping = false;
    
    public GameObject(int x, int y, BufferedImage[] animation) {
        super(x, y);
        this.defaultPosition = new Point(x, y);
        this.animation = animation;
    }
    
    public GameObject(int x, int y, BufferedImage animation) {
        this(x, y, new BufferedImage[] {animation});
    }
    
    @Override
    public boolean isStatic() {
        return false;
    }
    
    @Override
    public BufferedImage getImage() {
        return dir > 0 ? animation[animationIndex] : GraphicUtils.flipImage(animation[animationIndex]);
    }
    
    public void updateAnimation() {
        animationIndex++;
        
        if (animationIndex == animation.length)
            animationIndex = 0;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    /**
     * @return - true if this object is alive,
     *          false otherwise
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Resets this object's position
     * to where it was created
     */
    public void resetPosition() {
        this.setX(defaultPosition.x);
        this.setY(defaultPosition.y);
    }
    
    /**
     * Moves an object using its own speed
     * @param dir - if > 0 move right, if < 0 - left
     */
    public void moveX(int dir) {
        this.dir = dir;
        if (!(super.getX() == 0 && dir < 0))
            updateXY(dir, 0);
    }
    
    public void moveY(int dir) {
        updateXY(0, -dir);
    }
    
    public void jump(GameObject[] platforms) {
        if (jumpTime == 16)
        {
            jumpTime = 0;
            jumping = false;
            return;
        }
            
        jumpTime++;
        moveY(12, platforms);
    }
    
    public void prepareJump() {
        jumping = true;
    }
    
    public boolean isJumping() {
        return jumping;
    }
    
    public int moveX(int dist, GameObject[] platforms) {
        int moves = 0;
        
        Side side = dist > 0 ? Side.RIGHT : Side.LEFT;  //if moving right/left check if right/left of the obj is colliding
        
        for (int i = 0; i < Math.abs(dist); i++)
        {
            for (GameObject go : platforms)
                if (PhysicsEngine.isCollidingX(this, go, side))
                    return moves;

            moveX(dist > 0 ? 1 : -1);
            moves++;
        }
        
        updateAnimation();
        return moves;
    }
    
    public void moveY(int dist, GameObject[] platforms) {
        Side side = dist > 0 ? Side.TOP : Side.BOTTOM;  //if moving up/down check if top/bottom of the obj is colliding
        
        for (int i = 0; i < Math.abs(dist); i++)
        {
            for (GameObject go : platforms)
                if (PhysicsEngine.isCollidingY(this, go, side))
                    return;

            moveY(dist > 0 ? 1 : -1);
        }
    }
    
    /**
     * @param platforms - all platforms on the level
     * @return - true if object is on any platform, false otherwise
     */
    public boolean isOnPlatform(GameObject[] platforms) {
        for (GameObject platform : platforms)
            if (PhysicsEngine.isCollidingY(this, platform, Side.BOTTOM))
                return true;
        
        return false;
    }
    
    /**
     * @param platforms - all platforms on the level
     * @return - true if object is below any platform, false otherwise
     */
    public boolean isBelowPlatform(GameObject[] platforms) {
        for (GameObject platform : platforms)
            if (PhysicsEngine.isCollidingY(this, platform, Side.TOP))
                return true;
        
        return false;
    }

    /**
     * @param obj - any game object against which the collision is checked
     * @return - true if this obj colliding with given obj, false otherwise
     */
    public boolean isColliding(GameObject obj) {
        return PhysicsEngine.isColliding(this, obj);
    }
}
