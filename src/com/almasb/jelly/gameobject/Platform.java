package com.almasb.jelly.gameobject;

import com.almasb.jelly.GameResources;

public class Platform extends GameObject {
    public static final char ID = '1';

    private final PlatformType type;

    public enum PlatformType {
        UPPER, LOWER
    };

    public Platform(int x, int y, PlatformType type) {
        super(x, y, type == PlatformType.UPPER ? GameResources.IMG_PLATFORM_UP
                : GameResources.IMG_PLATFORM_DOWN);
        this.type = type;
    }
}
