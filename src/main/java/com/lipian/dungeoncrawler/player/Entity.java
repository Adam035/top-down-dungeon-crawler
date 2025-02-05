package com.lipian.dungeoncrawler.player;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class Entity {
    private int x, y, width, height, strength, attackSpeed, health, maxHealth, speed;
    private Direction direction, attackDirection;
    private Image image;
    private long lastAttackTime;

    public Entity(int x, int y, int strength, int attackSpeed, int health, int speed, Direction direction) {
        this.x = x;
        this.y = y;
        this.strength = strength;
        this.attackSpeed = attackSpeed;
        this.health = health;
        maxHealth = health;
        this.speed = speed;
        this.direction = direction;
    }

    public void move(Direction direction, double multiplier) {
        switch (direction) {
            case UP -> y -= (int) (speed * multiplier);
            case RIGHT -> x += (int) (speed * multiplier);
            case DOWN -> y += (int) (speed * multiplier);
            case LEFT -> x -= (int) (speed * multiplier);
        }
    }

    public Rectangle getNextBounds(Direction direction, double multiplier) {
        int nextX = x, nextY = y;
        switch (direction) {
            case UP -> nextY -= (int) (speed * multiplier);
            case RIGHT -> nextX += (int) (speed * multiplier);
            case DOWN -> nextY += (int) (speed * multiplier);
            case LEFT -> nextX -= (int) (speed * multiplier);
        }
        return new Rectangle(nextX, nextY, width, height);
    }

    public Bullet attack() {
        lastAttackTime = System.currentTimeMillis();
        return new Bullet(getX(), getY(), attackDirection);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }

    public void setImage(Image image) {
        this.image = image;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected Image loadImage(String filePath) {
        Image image;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            image = null;
        }
        return image;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
        g.setColor(Color.BLACK);
        g.fillRect(x, y + height + 3, width, 3);
        g.setColor(Color.GREEN);
        g.fillRect(x + 1, y + height + 4, (int) (width * ((double) health / (double) maxHealth) - 2), 1);
    }
}
