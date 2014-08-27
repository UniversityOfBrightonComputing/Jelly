package com.almasb.jelly;

import com.almasb.common.graphics.Color;
import com.almasb.common.graphics.GraphicsContext;
import com.almasb.java.game.GameObject;

/**
 * A game object in the Jelly world
 *
 * @author Almas Baimagambetov (ab607@uni.brighton.ac.uk)
 * @version 1.0
 *
 */
public class JellyGameObject extends GameObject {

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
                break;
            case ENEMY:
                break;

            case PLATFORM:
            case PORTAL:
            case COIN:
            case POWERUP:
                setMovable(false);
                break;
            default:
                break;
        }
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
    protected boolean onCollide(GameObject other, Collision collision) {
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

        return false;
    }

    @Override
    protected void onDraw(GraphicsContext gContext) {
        switch (type) {
            case COIN:
                gContext.drawImage(R.drawable.coin, getX(), getY());
                //break;
                return;
            case ENEMY:
                gContext.drawImage(R.drawable.enemy, getX(), getY());
                //break;
                return;
            case PLATFORM:
                gContext.drawImage(R.drawable.platformup, getX(), getY());
                return;
                //break;
            case PLAYER:
                gContext.drawImage(R.drawable.player, getX(), getY());
                return;
                //break;
            case PORTAL:
                break;
            case POWERUP:
                gContext.drawImage(R.drawable.powerup, getX(), getY());
                return;
                //break;
            default:
                break;
        }

        gContext.setColor(Color.RED);
        gContext.drawRect(getX(), getY(), getWidth(), getHeight());
    }
}
