
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Handles key press events for the game.
 * This class is responsible for processing key press events and passing them to the game logic.
 */

public class KeyHandler extends KeyAdapter {
    private GameWindow gameWindow;

    public KeyHandler(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }
    
    /**
     * Handles key press events.
     * 
     */
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Handle F11 key for fullscreen toggle
        if (key == KeyEvent.VK_F11) {
            gameWindow.toggleFullScreen();
            return;
        }

        // Handle arrow keys when the game is in play
        if (gameWindow.getGameState() == GameState.IN_PLAY) {
            switch (key) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    // Pass arrow key to the game logic
                    gameWindow.getGamePanel().getGame().handleKeyPress(key);
                    break;
                default:
                    // Handle other keys if needed
                    break;
            }
        }
    }
}
