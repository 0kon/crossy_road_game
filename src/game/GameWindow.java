package game;

import java.awt.*;
import javax.swing.*;
import panels.BasePanel;
import panels.GameOverPanel;
import panels.GamePanel;
import panels.OptionsPanel;
import panels.StartPanel;



/**
 * The GameWindow class represents the main window of the game.
 * It handles the different panels and the game state transitions.
 */
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

    /**
     * Constructor to create a new GameWindow.
     * Initializes the window properties and panels.
     */

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

        addKeyListener(new KeyHandler(this));

        // Set focus to allow key input
        
        setFocusable(true);
        requestFocusInWindow();  // Ensure window is focused for key events
        
        requestFocusInWindow();
    }

    /**
     * Restarts game. Changes panel to StartPanel.
     */
    public void restartGame() {
        gamePanel.restartGame();
        setContentPane(startPanel);
        revalidate();
        repaint();
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

    /**
     * Toggles the window between full-screen and windowed mode.
     * If the window is currently in full-screen mode, it will be restored to windowed mode.
     */

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

    /**
     * Shows the game panel and starts the game logic.
     * This method should be called when the game is started.
     */
    public void showGamePanel() {
        setCurrentPanel(gamePanel);
        gameState = GameState.IN_PLAY;
        gamePanel.startGame(); // Start the game logic
    }

    /**
     * Shows the game over panel with the final score.
     * @param finalScore the final score to display
     */
    public void showGameOverPanel(int finalScore) {
        gameOverPanel.setFinalScore(finalScore);
        gameState = GameState.GAME_OVER;
        setCurrentPanel(gameOverPanel);
    }
    /**
     * Shows the start panel with the main menu.
     */

    public void showStartPanel() {
        gameState = GameState.MENU;
        setCurrentPanel(startPanel);
    }
    /**
     * Shows the options panel with the settings.
     */

    public void showOptionsPanel() {
        gameState = GameState.OPTIONS;
        setCurrentPanel(optionsPanel);
    }
    /**
     * Returns the current game panel.
     * @return the game panel
     */
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
    /**
     * Returns the current game state.
     * @return the game state
     */
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
