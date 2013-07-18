package uk.ac.brighton.ab607.jelly.gameobject;

import uk.ac.brighton.ab607.jelly.GameResources;
import uk.ac.brighton.ab607.jelly.global.Global;

public class Powerup extends GameObject {
    public static final char ID = '4';
    
    private boolean active = false;
    
    public Powerup(int x, int y) {
        super(x, y, GameResources.IMG_POWERUP);
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return active;
    }
    
    @Override
    public boolean isStatic() {
        return active;
    }
    
    @Override
    public int getX() {
        if (active) {
            if (super.getX() != 50) {
                this.moveX(super.getX() > 50 ? -2 : 2);
            }
        }
        
        return super.getX();
    }
    
    @Override
    public int getY() {
        if (active) {
            if (super.getY() != 150) {
                this.moveY(super.getY() > 150 ? 2 : -2);
            }
        }
        
        return super.getY();
    }
}