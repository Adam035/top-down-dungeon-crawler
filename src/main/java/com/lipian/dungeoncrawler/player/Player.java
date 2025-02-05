package com.lipian.dungeoncrawler.player;

import lombok.Getter;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Player extends Entity {
    private static final int STRENGTH = 40, HEALTH = 100, ATTACK_SPEED = 1, SPEED = 7;
    private final Set<Integer> movementKeys;
    public Player(int x, int y) {
        super(x, y, STRENGTH, ATTACK_SPEED, HEALTH, SPEED, Direction.RIGHT);
        Image image = loadImage("src/main/resources/images/player/player.png");
        setImage(image);
        this.movementKeys = new HashSet<>();
    }

    public void addMovementKey(int keyCode) {
        movementKeys.add(keyCode);
    }

    public void removeMovementKey(int keyCode) {
        movementKeys.remove(keyCode);
    }
}
