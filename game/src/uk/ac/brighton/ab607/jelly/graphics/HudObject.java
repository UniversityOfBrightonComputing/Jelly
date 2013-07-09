package uk.ac.brighton.ab607.jelly.graphics;

import java.awt.image.BufferedImage;

public class HudObject extends GraphicObject {
    private BufferedImage image;
    
    public HudObject(int x, int y, BufferedImage image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }
}
