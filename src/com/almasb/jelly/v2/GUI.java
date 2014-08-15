package com.almasb.jelly.v2;

import com.almasb.common.graphics.Color;
import com.almasb.common.graphics.GraphicsContext;
import com.almasb.java.game.GameView;

@SuppressWarnings("serial")
public class GUI extends GameView {

    public GUI() {
        super(1280, 720, "Jelly v0.7");
    }

    @Override
    protected void createBackground(GraphicsContext gContext) {
        gContext.setColor(Color.WHITE);
        gContext.fillRect(0, 0, W, H);
    }

    @Override
    protected void createUI(GraphicsContext gContext) {
        // TODO Auto-generated method stub

    }
}
