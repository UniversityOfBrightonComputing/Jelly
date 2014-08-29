package com.almasb.jelly;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.almasb.common.util.Out;
import com.almasb.java.game.GameObject;
import com.almasb.java.game.Physics;
import com.almasb.java.game.UserEvent;
import com.almasb.java.io.ResourceManager;
import com.almasb.java.ui.FXGameWindow;
import com.almasb.jelly.JellyGameObject.Type;

/**
 * The view of the game
 *
 * @author Almas Baimagambetov (ab607@uni.brighton.ac.uk)
 * @version 1.0
 *
 */
public class GUI extends FXGameWindow {

    private Text score = new Text();
    private Player player = new Player(40, 100);

    public GUI() {
        super(1280, 720, "Jelly 0.8");
    }

    @Override
    protected void createContent(Pane root) {
        super.createContent(root);

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
                    gameObjects.add(object);
                    root.getChildren().add(object);
                }
            }
        }

        gameObjects.add(player);
        root.getChildren().add(player);

        camera.translateXProperty().bind(player.translateXProperty().subtract(640));
        camera.translateYProperty().bind(player.translateYProperty().subtract(player.translateYProperty()));

        // add a text score
        //score.translateXProperty().bind(player.translateXProperty().add(540));
        score.setTranslateY(100);
        score.setTranslateX(100);
        //score.setTextOrigin(VPos.TOP);

        //VBox vBox = new VBox();
        //vBox.getChildren().add(score);
        Group parent = new Group();
        SubScene subScene = new SubScene(parent, 200, 200);

        parent.getChildren().add(score);

        subScene.translateXProperty().bind(player.translateXProperty());
        //subScene.translateYProperty().bind(player.translateYProperty());

        root.getChildren().add(subScene);
        //root.translateXProperty().bind(camera.translateXProperty());
        //root.translateYProperty().bind(camera.translateYProperty());
        //root.relocate(camera.getTranslateX(), camera.getTranslateY());


        score.textProperty().bind(player.scoreProperty().asString());
    }

    @Override
    protected void initScene(Scene scene) {
        super.initScene(scene);

        eventBindings.put(KeyCode.UP, new UserEvent("Jump") {
            @Override
            public void handle() {
                player.jump();
            }
        });

        eventBindings.put(KeyCode.LEFT, new UserEvent("Left") {
            @Override
            public void handle() {
                player.moveX(-1);
            }
        });

        eventBindings.put(KeyCode.RIGHT, new UserEvent("Right") {
            @Override
            public void handle() {
                player.moveX(1);
            }
        });
    }

    @Override
    protected void initStage(Stage primaryStage) {
        super.initStage(primaryStage);

        primaryStage.show();
        mainTimer.start();
    }

    @Override
    protected void onUpdate(long now) {

        //root.relocate(camera.getTranslateX(), camera.getTranslateY());
        //Out.d("playerX", player.getTranslateX() + "");
    }
}
