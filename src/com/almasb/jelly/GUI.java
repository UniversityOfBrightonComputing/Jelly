package com.almasb.jelly;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;

import com.almasb.common.util.Out;
import com.almasb.java.game.GameObject;
import com.almasb.java.game.Physics;
import com.almasb.java.game.UserEvent;
import com.almasb.java.io.ResourceManager;
import com.almasb.java.ui.FXWindow;
import com.almasb.jelly.JellyGameObject.Type;

/**
 * The view of the game
 *
 * @author Almas Baimagambetov (ab607@uni.brighton.ac.uk)
 * @version 1.0
 *
 */
public class GUI extends FXWindow {

    private Player player = new Player(40, 100);

    private AnimationTimer mainTimer;

    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public GUI() {
        super();
        init();
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1280, 720);
        root.setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        root.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        List<String> levelData = ResourceManager.loadText("levels/level1.txt");
        for (int i = 0; i < 3; i++) {
            String line = levelData.get(i);
            for (int j = 0; j < line.length(); j++) {
                char id = line.charAt(j);

                int x = j*(Const.CELL_SIZE);
                int y = Physics.GROUND - (3-i)*(Const.CELL_SIZE);

                GameObject object = null;

                switch (id) {
                    case Const.ID_COIN:
                        object = new JellyGameObject(Type.COIN, x, y);
                        break;
                    case Const.ID_ENEMY:
                        object = new JellyGameObject(Type.ENEMY, x, y);
                        break;
                    case Const.ID_PLATFORM:
                        object = new JellyGameObject(Type.PLATFORM, x, y);
                        break;
                    case Const.ID_PORTAL:
                        object = new JellyGameObject(Type.PORTAL, x, y);
                        break;
                    case Const.ID_POWERUP:
                        object = new JellyGameObject(Type.POWERUP, x, y);
                        break;
                    default:
                        break;
                }

                if (object != null) {
                    if (gameObjects == null) Out.i("null obj");

                    gameObjects.add(object);
                    root.getChildren().add(object);
                }
            }
        }

        gameObjects.add(player);
        root.getChildren().add(player);


        return root;
    }


    int count = 0;

    public static final long delay = 1000000000 / 60;
    long prevFrame = 0;

    boolean left, right, up;

    @Override
    protected void init(Stage primaryStage) {
        Camera camera = new ParallelCamera();

        camera.translateXProperty().bind(player.translateXProperty().subtract(640));
        camera.translateYProperty().bind(player.translateYProperty().subtract(player.translateYProperty()));

        mainTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < gameObjects.size(); i++) {
                    GameObject o = gameObjects.get(i);
                    for (int j = 0; j < gameObjects.size(); j++) {
                        GameObject o2 = gameObjects.get(j);

                        if (o == o2) continue;

                        if (o.isColliding(o2)) {
                            o.collide(o2);
                        }
                    }
                }

                for (GameObject o : gameObjects)
                    o.update(gameObjects);

                /*synchronized (events) {
                        for (UserEvent e : events)
                            if (e.isRaised())
                                e.handle();
                    }*/

                if (left) {
                    player.moveX(-1);
                }

                if (right) {
                    player.moveX(1);
                }

                if (up) {
                    player.jump();
                }

                //Out.d("x", player.getTranslateX() + "");
                //Out.d("y", player.getTranslateY() + "");



                //camera.setTranslateX(player.getTranslateX() - 640);

                //onUpdate();
                //}


            }
        };

        //        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
        //            System.out.println("FPS: " + count);
        //            count = 0;
        //        }, 0, 1, TimeUnit.SECONDS);



        Scene scene = new Scene(createContent());


        scene.setCamera(camera);


        scene.setOnKeyPressed(event -> {
            //camera.setTranslateX(camera.getTranslateX() + 5.0);
            if (event.getCode() == KeyCode.LEFT) {
                left = true;
            }

            if (event.getCode() == KeyCode.RIGHT) {
                right = true;
            }

            if (event.getCode() == KeyCode.UP) {
                up = true;
            }
        });

        scene.setOnKeyReleased(event -> {
            //camera.setTranslateX(camera.getTranslateX() + 5.0);
            if (event.getCode() == KeyCode.LEFT) {
                left = false;
            }

            if (event.getCode() == KeyCode.RIGHT) {
                right = false;
            }

            if (event.getCode() == KeyCode.UP) {
                up = false;
            }
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jelly 0.8");
        primaryStage.show();

        mainTimer.start();
    }
}
