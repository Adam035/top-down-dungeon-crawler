package com.lipian.dungeoncrawler;

import com.lipian.dungeoncrawler.player.Bullet;
import com.lipian.dungeoncrawler.player.Direction;
import com.lipian.dungeoncrawler.player.Enemy;
import com.lipian.dungeoncrawler.player.Player;
import com.lipian.dungeoncrawler.world.Location;
import com.lipian.dungeoncrawler.world.LocationLoader;
import lombok.Getter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
public class GameLogic {
    private final Location location;
    private final Player player;
    private final Set<Bullet> bullets;

    public GameLogic() {
        location = LocationLoader.getMap("src/main/resources/location.txt");
        player = new Player(200, 200);
        bullets = new HashSet<>();
    }

    public void update() {
        handleMovement();
        handleAttack();
        handleBullets();
        handleEnemies();
    }

    public void paint(Graphics g) {
        location.paint(g);
        bullets.forEach(b -> b.paint(g));
        player.paint(g);
    }

    private void handleEnemies() {
        location.getEnemies().removeIf(enemy -> enemy.getHealth() <= 0);
    }

    private void handleBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (isColliding(bullet.getBounds()))
                iterator.remove();
            else bullet.move();
        }
    }

    private void handleAttack() {
        Direction attackDirection = player.getAttackDirection();
        if (attackDirection == null)
            return;

        long currentTime = System.currentTimeMillis();
        int cooldown = (10 - player.getAttackSpeed()) * 100;
        if (currentTime - player.getLastAttackTime() < cooldown)
            return;

        player.setLastAttackTime(currentTime);
        Bullet bullet = new Bullet(player.getX(), player.getY(), attackDirection);
        bullets.add(bullet);
    }

    private void handleMovement() {
        Set<Integer> movementKeys = player.getMovementKeys();
        double speed = movementKeys.size() != 2 ? 1 : 0.8;

        if (movementKeys.contains(KeyEvent.VK_W))
            if (!isColliding(player.getNextBounds(Direction.UP, speed)))
                player.move(Direction.UP, speed);

        if (movementKeys.contains(KeyEvent.VK_D))
            if (!isColliding(player.getNextBounds(Direction.RIGHT, speed)))
                player.move(Direction.RIGHT, speed);

        if (movementKeys.contains(KeyEvent.VK_S))
            if (!isColliding(player.getNextBounds(Direction.DOWN, speed)))
                player.move(Direction.DOWN, speed);

        if (movementKeys.contains(KeyEvent.VK_A)) {
            if (!isColliding(player.getNextBounds(Direction.LEFT, speed)))
                player.move(Direction.LEFT, speed);
        }
    }

    private boolean isColliding(Rectangle nextBounds) {
        return location.getEnemies().stream()
                .filter(enemy -> enemy.getBounds().intersects(nextBounds))
                .findAny()
                .map(enemy -> {
                    enemy.setHealth(enemy.getHealth() - player.getStrength());
                    return true;
                })
                .orElse(location.getWalls().stream()
                        .anyMatch(wall -> wall.getBounds().intersects(nextBounds)));

    }
}
