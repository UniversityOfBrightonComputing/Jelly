package com.almasb.jelly.gameobject;

import com.almasb.jelly.GameResources;

public class Portal extends GameObject {
    public static final char ID = '9';

    public Portal(int x, int y) {
        super(x, y, GameResources.IMG_PORTAL);
    }
}
