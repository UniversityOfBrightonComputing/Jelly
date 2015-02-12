package com.almasb.jelly;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import com.almasb.common.graphics.Color;
import com.almasb.common.graphics.GraphicsContext;
import com.almasb.common.graphics.Point2D;
import com.almasb.common.util.Out;
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

    private float dirX = 1.0f;

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

                /*KeyValue k = new KeyValue(sprite.imageProperty(), R.getImage(R.drawable.platformdown));
                KeyValue k2 = new KeyValue(sprite.imageProperty(), R.getImage(R.drawable.platformup));

                KeyFrame kf = new KeyFrame(Duration.seconds(2), k);
                KeyFrame kf2 = new KeyFrame(Duration.seconds(4), k2);

                Timeline timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().addAll(kf, kf2);
                timeline.play();*/

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
                moveX(dirX);
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
        JellyGameObject collider = (JellyGameObject) other;

        switch (type) {
            case COIN:
                if (collider.getType() == Type.PLAYER) {
                    this.setAlive(false);

                    TranslateTransition tt = new TranslateTransition(Duration.seconds(1), this);
                    tt.setFromX(getTranslateX());
                    tt.setFromY(getTranslateY());

                    tt.setToX(((Player)collider).getTranslateX() + 640);
                    tt.setToY(-50);
                    tt.setOnFinished(event -> {
                        this.setVisible(false);
                        ((Player)collider).addScore(Const.SCORE_COIN);
                    });
                    tt.play();
                }
                break;
            case ENEMY:
                if (collider.getType() == Type.PLAYER) {
                    ((Player)collider).addLives(-1);
                    collider.setX(100);
                    collider.setY(100);
                }

                if (collision == Collision.SIDE) {
                    dirX *= -1;
                    return true;
                }
                break;
            case PLATFORM:
                break;
            case PLAYER:
                if (collider.getType() == Type.PLATFORM)
                    return true;
                break;
            case PORTAL:
                break;
            case POWERUP:
                if (collider.getType() == Type.PLAYER) {
                    this.setAlive(false);
                    ScaleTransition st = new ScaleTransition(Duration.seconds(2), this);
                    st.setToX(0);
                    st.setToY(0);
                    st.setOnFinished(event -> {
                        this.setVisible(false);
                    });
                    st.play();

                    st = new ScaleTransition(Duration.seconds(1), collider);
                    st.setToX(2);
                    st.setToY(2);
                    st.setOnFinished(event -> {
                        collider.size.add(collider.size);

                        KeyFrame kf = new KeyFrame(Duration.seconds(5), e -> {
                            ScaleTransition st2 = new ScaleTransition(Duration.seconds(1), collider);
                            st2.setToX(1);
                            st2.setToY(1);
                            st2.play();
                        });

                        Timeline t = new Timeline(kf);
                        t.play();
                        t.setOnFinished(evt -> {
                            collider.size.subtract(getSize());
                            t.stop();
                        });
                    });
                    st.play();
                }
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    protected void onDraw(GraphicsContext gContext) {
        // not used in FX
    }
}
