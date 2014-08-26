package com.almasb.jelly.graphics.hud;

import com.almasb.jelly.graphics.Renderer;

public class HudText extends HudObject {
    private boolean changed = false;
    private String text = "";

    public HudText(int x, int y, String text) {
        super(x, y, Renderer.createGraphics(text));
    }

    public HudText(double x, double y, String text) {
        this((int) x, (int) y, text);
    }

    public void setText(String text) {
        if (!this.text.equals(text)) {
            this.text = text;
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
            this.image = Renderer.createGraphics(text);
            changed = false;
        }
    }
}
