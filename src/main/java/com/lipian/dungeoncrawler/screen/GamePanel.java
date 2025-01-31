package com.lipian.dungeoncrawler.screen;

import com.lipian.dungeoncrawler.world.Location;
import com.lipian.dungeoncrawler.world.LocationLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private static final int DELAY = 10;
    private Location location;
    public GamePanel() {
        setLayout(null);
        setFocusable(true);
        setBackground(Color.BLACK);
        location = LocationLoader.getMap("src/main/resources/location.txt");
        start();
    }

    private void start() {
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        location.paint(g);
    }
}
