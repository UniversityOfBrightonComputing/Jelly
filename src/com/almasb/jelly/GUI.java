package com.almasb.jelly;

import com.almasb.common.graphics.Color;
import com.almasb.common.graphics.GraphicsContext;
import com.almasb.java.game.GameView;

/**
 * The view of the game
 *
 * @author Almas Baimagambetov (ab607@uni.brighton.ac.uk)
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class GUI extends GameView {

    public GUI() {
        super(1280, 720, "Jelly v0.7.1");
    }

    @Override
    protected void createBackground(GraphicsContext gContext) {
        gContext.setColor(Color.WHITE);
        gContext.fillRect(0, 0, W, H);
    }

    @Override
    protected void createUI(GraphicsContext gContext) {
    }
}
