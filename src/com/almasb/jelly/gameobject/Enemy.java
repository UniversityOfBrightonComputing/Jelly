package com.almasb.jelly.gameobject;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.almasb.jelly.GameResources;

public class Enemy extends GameObject {
    public static final char ID = '3';

    private int moveTimer = 0;

    public enum EnemyType {
        FLY(GameResources.IMG_ENEMY);

        final BufferedImage image;

        EnemyType(BufferedImage image) {
            this.image = image;
        }
    };

    public Enemy(int x, int y, EnemyType type) {
        super(x, y, type.image);
    }

    // will do atm
    public void moveX() {
        if (moveTimer < 175)
            moveX(1);
        else
            moveX(-1);

        moveTimer++;

        if (moveTimer == 350)
            moveTimer = 0;
    }
}
