package com.lipian.dungeoncrawler.world;

import com.lipian.dungeoncrawler.player.Enemy;
import lombok.Getter;

import java.awt.*;
import java.util.*;
import java.util.List;

@Getter
public class Location {
    private final int WIDTH = 17, HEIGHT = 11;
    private List<Tile> tiles;
    private Set<Wall> walls;
    private Set<Enemy> enemies;

    protected Location() {
        tiles = new ArrayList<>();
        walls = new HashSet<>();
        enemies = new HashSet<>();
        enemies.add(new Enemy(400, 200));
    }

    public void addTile(Tile tile) {
        if (tiles.size() >= WIDTH * HEIGHT)
            return;
        if (tile != null) {
            int row = tiles.size() % WIDTH;
            int col = tiles.size() / WIDTH;
            tile.setX(row * Tile.SIZE);
            tile.setY(col * Tile.SIZE);
            if (tile.getClass().equals(Wall.class))
                walls.add((Wall) tile);
        }
        tiles.add(tile);
    }

    public void paint (Graphics g) {
        tiles.stream().filter(Objects::nonNull)
                .forEach(tile ->  tile.paint(g));
        enemies.forEach(enemy -> enemy.paint(g));
    }
}
