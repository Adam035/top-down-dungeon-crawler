package com.lipian.dungeoncrawler;

import com.lipian.dungeoncrawler.player.Direction;
import com.lipian.dungeoncrawler.player.Player;
import lombok.AllArgsConstructor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

@AllArgsConstructor
public class InputHandler extends KeyAdapter {
    private static final Set<Integer> MOVEMENT_KEYS = Set.of(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D),
            ATTACK_KEYS = Set.of(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

    private final GameLogic gameLogic;


    @Override
    public void keyPressed(KeyEvent e) {
        Player player = gameLogic.getPlayer();
        if (MOVEMENT_KEYS.contains(e.getKeyCode())) {
            player.addMovementKey(e.getKeyCode());
        } else if (ATTACK_KEYS.contains(e.getKeyCode())) {
            player.setAttackDirection(mapAttackDirection(e));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Player player = gameLogic.getPlayer();
        if (MOVEMENT_KEYS.contains(e.getKeyCode())) {
            player.removeMovementKey(e.getKeyCode());
        } else if (ATTACK_KEYS.contains(e.getKeyCode())) {
            player.setAttackDirection(null);
        }
    }

    private Direction mapAttackDirection(KeyEvent e) {
        return switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> Direction.UP;
            case KeyEvent.VK_RIGHT -> Direction.RIGHT;
            case KeyEvent.VK_DOWN -> Direction.DOWN;
            case KeyEvent.VK_LEFT -> Direction.LEFT;
            default -> null;
        };
    }
}
