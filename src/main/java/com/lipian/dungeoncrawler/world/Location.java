package com.lipian.dungeoncrawler.world;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Location {
    private final int WIDTH = 17, HEIGHT = 11;
    private List<Tile> tiles;

    protected Location() {
        tiles = new ArrayList<>();
    }

    public void addTile(Tile tile) {
        if (tiles.size() >= WIDTH * HEIGHT)
            return;
        if (tile != null) {
            int row = tiles.size() % WIDTH;
            int col = tiles.size() / WIDTH;
            tile.setX(row * Tile.SIZE);
            tile.setY(col * Tile.SIZE);
        }
        tiles.add(tile);
    }

    public void paint (Graphics g) {
        tiles.stream().filter(Objects::nonNull)
                .forEach(tile -> g.drawImage(tile.getImage(), tile.getX(), tile.getY(), null));
    }
}
