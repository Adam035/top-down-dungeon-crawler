package com.lipian.dungeoncrawler.world;

import java.awt.*;

public class Wall extends Tile {
    public Wall(String type) {
        super();
        String filePath = String.format("src/main/resources/images/map/%s.png", type);
        Image image = loadImage(filePath);
        setImage(image);
    }
}
