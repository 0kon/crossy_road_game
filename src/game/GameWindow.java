package game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import panels.BasePanel;
import panels.GameOverPanel;
import panels.GamePanel;
import panels.OptionsPanel;
import panels.StartPanel;



public class GameWindow extends JFrame {
    private GameState gameState = GameState.MENU;
    private StartPanel startPanel;
    private GamePanel gamePanel;
    private OptionsPanel optionsPanel;
    private GameOverPanel gameOverPanel;
    private BasePanel currentPanel;
    
    // Store the window's normal size and state before going full screen
    public boolean isFullscreen = false;
    private Rectangle windowedBounds;

    public GameWindow() {
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set initial window size
        setSize(1279, 719);
        setLocationRelativeTo(null); // Center the window

        // Enable resizing
        setResizable(true);

        // Initialize panels
        startPanel = new StartPanel(this); // Start screen
        gamePanel = new GamePanel(this);   // Main game panel
        optionsPanel = new OptionsPanel(this);
        gameOverPanel = new GameOverPanel(this);

        // Show the start panel initially
        showStartPanel();
        System.out.println(gameState);

        addKeyListener(new KeyHandler(this));

        // Set focus to allow key input
        
        setFocusable(true);
        requestFocusInWindow();  // Ensure window is focused for key events
        
        requestFocusInWindow();
    }


    private void setCurrentPanel(BasePanel panel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = panel;
        setContentPane(currentPanel);
        
        revalidate();
        requestFocusInWindow();  // Make sure focus returns to the window after switching panels
    
        repaint();
    }

    // Method to toggle between full screen (borderless) and normal windowed mode
    public void toggleFullScreen() {
        if (!isFullscreen) {
            // Enter full-screen borderless mode
            windowedBounds = getBounds(); // Store the current windowed size and position
            dispose(); // Hide the window before making it undecorated
            setUndecorated(true); // Remove window borders
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
            setVisible(true); // Show the window again
        } else {
            // Exit full-screen mode and return to windowed mode
            dispose(); // Hide the window before restoring decoration
            setUndecorated(false); // Add window borders back
            setBounds(windowedBounds); // Restore the previous window size and position
            setVisible(true); // Show the window again
        }
        isFullscreen = !isFullscreen; // Toggle the full-screen state
    }

    // Show the game panel (called when the game starts)
    public void showGamePanel() {
        setCurrentPanel(gamePanel);
        gameState = GameState.IN_PLAY;
        gamePanel.startGame(); // Start the game logic
    }

    // Show the game over panel
    public void showGameOverPanel(int finalScore) {
        gameOverPanel.setFinalScore(finalScore);
        gameState = GameState.GAME_OVER;
        setCurrentPanel(gameOverPanel);
    }

    // // Show the start panel
    public void showStartPanel() {
        gameState = GameState.MENU;
        setCurrentPanel(startPanel);
    }

    public void showOptionsPanel() {
        gameState = GameState.OPTIONS;
        setCurrentPanel(optionsPanel);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameState getGameState() {
        return gameState;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow gameWindow = new GameWindow();
            gameWindow.setVisible(true);
        });
    }
}
