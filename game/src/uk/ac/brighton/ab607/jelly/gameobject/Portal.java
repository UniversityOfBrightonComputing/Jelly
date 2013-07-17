package uk.ac.brighton.ab607.jelly.gameobject;

import uk.ac.brighton.ab607.jelly.GameResources;

public class Portal extends GameObject {
    public static final char ID = '9';
    public Portal(int x, int y) {
        super(x, y, GameResources.IMG_PORTAL);
    }
}
