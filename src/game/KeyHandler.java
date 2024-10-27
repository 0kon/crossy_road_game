
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private GameWindow gameWindow;

    public KeyHandler(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        System.out.println(gameWindow.getGameState());

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
                    // System.out.println("Arrow key pressed: " + key);
                    gameWindow.getGamePanel().getGame().handleKeyPress(key);
                    break;
                default:
                    // Handle other keys if needed
                    break;
            }
        }
    }

    // @Override
    // public void keyReleased(KeyEvent e) {
    //     int key = e.getKeyCode();

    //     // Handle arrow key releases during the game
    //     if (gameWindow.getGameState() == GameState.IN_PLAY) {
    //         switch (key) {
    //             case KeyEvent.VK_UP:
    //             case KeyEvent.VK_DOWN:
    //             case KeyEvent.VK_LEFT:
    //             case KeyEvent.VK_RIGHT:
    //                 // Pass arrow key release to the game logic
    //                 System.out.println("Arrow key released: " + key);
    //                 gameWindow.getGamePanel().getGame().handleKeyRelease(key);
    //                 break;
    //             default:
    //                 break;
    //         }
    //     }
    // }
}
