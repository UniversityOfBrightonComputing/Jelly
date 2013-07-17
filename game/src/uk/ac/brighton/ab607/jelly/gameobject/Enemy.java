package uk.ac.brighton.ab607.jelly.gameobject;

import java.awt.image.BufferedImage;

import uk.ac.brighton.ab607.jelly.GameResources;

public class Enemy extends GameObject {
    public static final char ID = '3';
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

}
