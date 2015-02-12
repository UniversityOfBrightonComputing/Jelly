package com.almasb.jelly;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        ImageView background = new ImageView(R.getImage(R.drawable.bg_back));
        background.translateXProperty().bind(camera.translateXProperty());
        root.getChildren().add(background);

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
    protected void initUI(SubScene ui, Group uiRoot) {
        final HBox livesHBox = new HBox(15);
        livesHBox.setTranslateX(50);
        livesHBox.setTranslateY(70);
        final ArrayList<Animation> livesAnimation = new ArrayList<Animation>();
        for (int i = 0; i < player.livesProperty().get(); i++) {
            ImageView img = new ImageView(R.getImage(R.drawable.lives));
            livesHBox.getChildren().add(img);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(2), img);
            tt.setToY(img.getTranslateY() - 30);
            tt.setAutoReverse(true);
            tt.setCycleCount(Animation.INDEFINITE);
            tt.setDelay(Duration.seconds(0.33*i));
            tt.play();
            livesAnimation.add(tt);
        }

        player.livesProperty().addListener((obs, old, newValue) -> {
            for (Animation a : livesAnimation)
                a.stop();
            livesAnimation.clear();
            livesHBox.getChildren().clear();

            for (int i = 0; i < newValue.intValue(); i++) {
                ImageView img = new ImageView(R.getImage(R.drawable.lives));

                if (i == newValue.intValue() - 1 && newValue.intValue() > old.intValue()) {
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(2), img);
                    tt.setFromX(score.getTranslateX());
                    tt.setToX(img.getTranslateX());

                    tt.play();
                }


                livesHBox.getChildren().add(img);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(2), img);
                tt.setToY(img.getTranslateY() - 30);
                tt.setAutoReverse(true);
                tt.setCycleCount(Animation.INDEFINITE);
                tt.setDelay(Duration.seconds(0.33*i));
                tt.play();
                livesAnimation.add(tt);
            }
        });

        score.setTranslateX(1200);
        score.setTranslateY(70);
        score.textProperty().bind(player.scoreProperty().asString());
        player.scoreProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() % 1000 == 0) {
                ScaleTransition st = new ScaleTransition(Duration.seconds(1), score);
                st.setToX(2);
                st.setToY(3);
                st.setAutoReverse(true);
                st.setCycleCount(2);
                st.play();
            }
        });

        ImageView clouds = new ImageView(R.getImage(R.drawable.bg_clouds));
        clouds.setTranslateX(W);
        TranslateTransition cloudsAnimation = new TranslateTransition(Duration.seconds(20), clouds);
        cloudsAnimation.setToX(-W);
        cloudsAnimation.setCycleCount(Animation.INDEFINITE);
        cloudsAnimation.play();

        uiRoot.getChildren().addAll(livesHBox, score, clouds);
    }

    @Override
    protected void initStage(Stage primaryStage) {
        super.initStage(primaryStage);
        primaryStage.setWidth(W);
        primaryStage.setHeight(H);
        primaryStage.show();
        mainTimer.start();
    }

    @Override
    protected void onUpdate(long now) {
        // manually trigger camera translate property to fire
        if (camera.getTranslateY() == 30)
            camera.setTranslateY(30.1);
        else
            camera.setTranslateY(30);
    }
}
