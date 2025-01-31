package com.lipian.dungeoncrawler;

import com.lipian.dungeoncrawler.screen.Frame;
import com.lipian.dungeoncrawler.screen.GamePanel;

public class DungeonCrawler {
    public static void main(String[] args) {
        Frame frame = Frame.getInstance();
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}
