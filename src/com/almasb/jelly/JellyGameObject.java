package com.almasb.jelly;

import javafx.scene.image.ImageView;

import com.almasb.common.graphics.Color;
import com.almasb.common.graphics.GraphicsContext;
import com.almasb.common.graphics.Point2D;
import com.almasb.java.game.Camera2D;
import com.almasb.java.game.GameObject;

/**
 * A game object in the Jelly world
 *
 * @author Almas Baimagambetov (ab607@uni.brighton.ac.uk)
 * @version 1.0
 *
 */
public class JellyGameObject extends GameObject {

    private ImageView sprite = new ImageView();

    public enum Type {
        PLAYER(-1), PLATFORM(1), COIN(2), ENEMY(3), POWERUP(4), PORTAL(9);

        public final int ID;
        private Type(int id) {
            ID = id;
        }
    }

    private final Type type;

    public JellyGameObject(Type t, int x, int y) {
        super(x, y, Const.CELL_SIZE-1, Const.CELL_SIZE-1);
        this.type = t;
        switch (type) {
            case PLAYER:
                sprite.setImage(R.getImage(R.drawable.player));
                break;
            case ENEMY:
                sprite.setImage(R.getImage(R.drawable.enemy));
                break;

            case PLATFORM:
                sprite.setImage(R.getImage(R.drawable.platformup));
                setMovable(false);
                break;
            case PORTAL:
            case COIN:
                sprite.setImage(R.getImage(R.drawable.coin));
                setMovable(false);
                break;
            case POWERUP:
                sprite.setImage(R.getImage(R.drawable.powerup));
                setMovable(false);
                break;
            default:
                break;
        }

        getChildren().add(sprite);
    }

    public Type getType() {
        return type;
    }

    @Override
    public void onUpdate() {
        switch (type) {
            case COIN:
                break;
            case ENEMY:
                break;
            case PLATFORM:
                break;
            case PLAYER:
                break;
            case PORTAL:
                break;
            case POWERUP:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCollide(GameObject other, Collision collision) {
        JellyGameObject collider = (JellyGameObject) other;

        switch (type) {
            case COIN:
                if (collider.getType() == Type.PLAYER) {
                    this.setAlive(false);
                    this.setVisible(false);
                    ((Player)collider).addScore(Const.SCORE_COIN);
                }
                break;
            case ENEMY:
                break;
            case PLATFORM:
                break;
            case PLAYER:
                break;
            case PORTAL:
                break;
            case POWERUP:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDraw(GraphicsContext gContext) {
        // not used in FX
    }
}
