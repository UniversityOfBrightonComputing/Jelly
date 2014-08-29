package com.almasb.jelly;

import javafx.beans.property.SimpleIntegerProperty;

public class Player extends JellyGameObject {

    private SimpleIntegerProperty score = new SimpleIntegerProperty();

    public Player(int x, int y) {
        super(Type.PLAYER, x, y);
    }

    public void addScore(int value) {
        score.set(score.get() + value);
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
}
