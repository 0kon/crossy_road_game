package game;

import javax.swing.*;

public class GameWindow extends JFrame {

    private GameLoop gameLoop;
    public GameWindow() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080); // Default window size (16:9 ratio)
        setLocationRelativeTo(null); // Center window on the screen

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        gameLoop = new GameLoop(gamePanel);
        gameLoop.start(); 

        setResizable(true); // Allow window to be resizable
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
