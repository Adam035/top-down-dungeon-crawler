package com.lipian.dungeoncrawler.world;

import java.awt.*;

public class Floor extends Tile {
    private static final String FILE_PATH = "src/main/resources/images/floor.png";
    public Floor() {
        super();
        Image image = loadImage(FILE_PATH);
        setImage(image);
    }
}
