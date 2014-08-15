package com.almasb.jelly.gameobject;

import java.awt.image.BufferedImage;

import com.almasb.jelly.GameResources;

public class Coin extends GameObject {
    public static final char ID = '2';

    public enum CoinType {
        SMALL(100, GameResources.IMG_COIN), MEDIUM(200, GameResources.IMG_COIN), BIG(
                500, GameResources.IMG_COIN);

        final int value;
        final BufferedImage image;

        CoinType(int value, BufferedImage image) {
            this.value = value;
            this.image = image;
        }
    };

    private final CoinType type;

    public Coin(int x, int y, CoinType type) {
        super(x, y, type.image);
        this.type = type;
    }

}
