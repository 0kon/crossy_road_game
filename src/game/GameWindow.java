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
    private boolean isFullscreen = false;
    private Rectangle windowedBounds;

    public GameWindow() {
        setTitle("Game Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set initial window size
        setSize(720, 480);
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

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });


        // Set focus to allow key input
        setFocusable(true);
        requestFocusInWindow();
    }


    private void setCurrentPanel(BasePanel panel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = panel;
        setContentPane(currentPanel);
        revalidate();
        repaint();
    }


    // Method to handle all key presses
    private void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();

        // F11 to toggle fullscreen - Always
        if (key == KeyEvent.VK_F11) {
            toggleFullScreen();
        }

        // Handle arrow keys if game is IN_PLAY
        if (gameState == GameState.IN_PLAY) {
            handleArrowKeyPress(key);
        }
    }

    // Method to handle arrow key inputs
    private void handleArrowKeyPress(int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
        }
    }
       
    private void moveLeft() {
        System.out.println("Move Left");
    }

    private void moveRight() {
        System.out.println("Move Right");
    }

    private void moveUp() {
        System.out.println("Move Up");   
    }

    private void moveDown() {
        System.out.println("Move Down");
    }

    // Method to toggle between full screen (borderless) and normal windowed mode
    private void toggleFullScreen() {
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
    public void showGameOverPanel() {
        setCurrentPanel(gameOverPanel);
    }

    // // Show the start panel
    public void showStartPanel() {
        setCurrentPanel(startPanel);
    }

    public void showOptionsPanel() {
        setCurrentPanel(optionsPanel);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow gameWindow = new GameWindow();
            gameWindow.setVisible(true);
        });
    }
}
