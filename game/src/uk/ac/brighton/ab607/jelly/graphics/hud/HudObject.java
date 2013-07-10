package uk.ac.brighton.ab607.jelly.graphics.hud;

import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.graphics.GraphicObject;
import uk.ac.brighton.ab607.jelly.graphics.Renderer;
import uk.ac.brighton.ab607.jelly.io.Out;

/**
 * A graphic object for game interface that
 * shows game info to the user
 * @author Almas
 * @version 0.1
 */
public class HudObject extends GraphicObject {
    private BufferedImage originalImage;
    protected BufferedImage image;
    
    public HudObject(int x, int y, BufferedImage image) {
        this(x, y, image, 1);
    }
    
    public HudObject(int x, int y, BufferedImage image, int times) {
        super(x, y);
        this.originalImage = image;
        this.image = Renderer.createGraphics(originalImage, times);
    }

    public void setTimes(int times) {
       // Out.msg("called from setTimes: " + times);
        this.image = Renderer.createGraphics(originalImage, times);
    }
    
    @Override
    public BufferedImage getImage() {
        return image;
    }
}
