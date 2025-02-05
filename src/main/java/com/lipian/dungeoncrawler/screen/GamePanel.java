package com.lipian.dungeoncrawler.screen;

import com.lipian.dungeoncrawler.GameLogic;
import com.lipian.dungeoncrawler.InputHandler;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private static final int DELAY = 16;
    private final GameLogic gameLogic;
    private final InputHandler inputHandler;

    public GamePanel() {
        setLayout(null);
        setFocusable(true);
        setBackground(Color.BLACK);
        gameLogic = new GameLogic();
        inputHandler = new InputHandler(gameLogic);
        addKeyListener(inputHandler);
        start();
    }

    private void start() {
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        gameLogic.paint(g);
    }
}
