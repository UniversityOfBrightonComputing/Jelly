package com.almasb.jelly.v2;

import java.awt.event.KeyEvent;
import java.util.List;

import com.almasb.java.game.GameModel;
import com.almasb.java.game.GameView;
import com.almasb.java.game.Physics;
import com.almasb.java.game.UserEvent;
import com.almasb.java.io.ResourceManager;
import com.almasb.java.io.Resources;
import com.almasb.jelly.Const;
import com.almasb.jelly.v2.JellyGameObject.Type;

public class Game extends GameModel {

    private Player player = new Player(40, 100);

    public Game(GameView view) {
        super(view);

        // populate world
        List<String> levelData = ResourceManager.loadText("levels/level1.txt");
        for (int i = 0; i < 3; i++) {
            String line = levelData.get(i);
            for (int j = 0; j < line.length(); j++) {
                char id = line.charAt(j);

                int x = j*(Const.CELL_SIZE);
                int y = Physics.GROUND - (3-i)*(Const.CELL_SIZE);

                switch (id) {
                    case Const.ID_COIN:
                        addGameObject(new JellyGameObject(Type.COIN, x, y));
                        break;
                    case Const.ID_ENEMY:
                        addGameObject(new JellyGameObject(Type.ENEMY, x, y));
                        break;
                    case Const.ID_PLATFORM:
                        addGameObject(new JellyGameObject(Type.PLATFORM, x, y));
                        break;
                    case Const.ID_PORTAL:
                        addGameObject(new JellyGameObject(Type.PORTAL, x, y));
                        break;
                    case Const.ID_POWERUP:
                        addGameObject(new JellyGameObject(Type.POWERUP, x, y));
                        break;
                    default:
                        break;
                }
            }
        }

        addGameObject(player);

        // add user events
        this.addUserEvent(KeyEvent.VK_UP, new UserEvent("jump") {
            @Override
            public void handle() {
                player.jump();
            }
        });

        this.addUserEvent(KeyEvent.VK_LEFT, new UserEvent("left") {
            @Override
            public void handle() {
                player.moveX(-1);
            }
        });

        this.addUserEvent(KeyEvent.VK_RIGHT, new UserEvent("right") {
            @Override
            public void handle() {
                player.moveX(1);
            }
        });
    }

    @Override
    public void onUpdate() {
        // TODO Auto-generated method stub

    }
}
