package com.lipian.dungeoncrawler.player;

import java.awt.*;

public class Bullet {
    private int x, y;
    private final int speed;
    private final Direction direction;

    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.speed = 15;
        this.direction = direction;
    }

    public void move() {
        switch (direction) {
            case UP -> y -= speed;
            case RIGHT -> x += speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 20, 20);
    }
}
