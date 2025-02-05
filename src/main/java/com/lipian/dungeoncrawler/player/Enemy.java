package com.lipian.dungeoncrawler.player;

import java.awt.*;

public class Enemy extends Entity {

    private static final int STRENGTH = 40, HEALTH = 100, ATTACK_SPEED = 1, SPEED = 7;
    public Enemy(int x, int y) {
        super(x, y, STRENGTH, ATTACK_SPEED, HEALTH, SPEED, Direction.RIGHT);
        Image image = loadImage("src/main/resources/images/player/enemy.png");
        setImage(image);
    }
}
