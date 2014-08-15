package com.almasb.jelly.graphics.hud;

import java.awt.image.BufferedImage;

import com.almasb.jelly.graphics.GraphicObject;

/**
 * A graphic object for game interface that
 * shows game info to the user
 * @author Almas
 * @version 0.3
 */
public abstract class HudObject extends GraphicObject {
    
    /**
     * Image to be used for rendering this object
     */
    protected BufferedImage image;
    
    /**
     * @param x - top left X coordinate
     * @param y - top left Y coordinate
     * @param image - initial image to be used for render
     */
    public HudObject(int x, int y, BufferedImage image) {
        super(x, y);
        this.image = image;
    }
    
    @Override
    public BufferedImage getImage() {
        return image;
    }
    
    @Override
    public boolean isStatic() {
        return true;
    }
    
    /**
     * Can be used to retrieve info about changes
     * of this object
     * @return - true if object changed since last poll,
     *          false otherwise
     */
    public abstract boolean hasChanged();
    
    /**
     * Ask this object to update its image
     * e.g. redraw number of lives, redraw
     * new text, etc
     */
    public abstract void updateGraphics();
}
