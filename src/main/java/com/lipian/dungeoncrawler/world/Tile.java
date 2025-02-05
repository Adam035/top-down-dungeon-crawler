package com.lipian.dungeoncrawler.world;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@Getter
@Setter
public abstract class Tile {
    protected static final int SIZE = 72;
    private int x, y;
    private Image image;

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
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

    protected void paint(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
