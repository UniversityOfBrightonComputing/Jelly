package com.almasb.jelly;

import javafx.beans.property.SimpleIntegerProperty;

public class Player extends JellyGameObject {

    private SimpleIntegerProperty score = new SimpleIntegerProperty();
    private SimpleIntegerProperty lives = new SimpleIntegerProperty(Const.LIVES);

    public Player(int x, int y) {
        super(Type.PLAYER, x, y);
    }

    public void addScore(int value) {
        score.set(score.get() + value);
        if (score.get() % 1000 == 0)
            addLives(1);
    }

    public void addLives(int value) {
        lives.set(lives.get() + value);
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public SimpleIntegerProperty livesProperty() {
        return lives;
    }
}
