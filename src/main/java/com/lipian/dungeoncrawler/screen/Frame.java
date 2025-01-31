package com.lipian.dungeoncrawler.screen;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static final int WIDTH = 1238, HEIGHT = 829;
    public static Frame instance;
    public static Frame getInstance() {
        if (instance == null)
            instance = new Frame();
        return instance;
    }
    private Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
}
