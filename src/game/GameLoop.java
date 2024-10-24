package game;

import javax.swing.*;

import panels.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop {

    private static final int TARGET_FPS = 120; // Target frames per second
    private static final int FRAME_TIME = 1000 / TARGET_FPS; // Time per frame in milliseconds

    private Timer gameTimer;
    private GamePanel gamePanel;

    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // Initialize the game loop using a Swing Timer
        gameTimer = new Timer(FRAME_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                
                // Repaint the gamePanel
                gamePanel.repaint();
            }
        });
    }

    // Start the game loop
    public void start() {
        gameTimer.start();
    }

    // Stop the game loop
    public void stop() {
        gameTimer.stop();
    }
}
