package com.almasb.jelly.graphics.hud;

import java.awt.image.BufferedImage;

import com.almasb.jelly.graphics.Renderer;

public class HudStatBar extends HudObject {
    private boolean changed = false;
    private int times = 0;
    private BufferedImage original;

    public HudStatBar(int x, int y, BufferedImage image, int times) {
        super(x, y, Renderer.createGraphics(image, times));
        this.original = image;
        this.times = times;
    }
    
    public HudStatBar(double x, double y, BufferedImage image, int times) {
        this((int)x, (int)y, image, times);
    }
    
    public void setTimes(int times) {
        if (this.times != times) {
            this.times = times;
            changed = true;
        }
    }
    
    @Override
    public boolean hasChanged() {
        return changed;
    }

    @Override
    public void updateGraphics() {
        if (hasChanged()) {
            this.image = Renderer.createGraphics(original, times);
            changed = false;
        }
    }
}
