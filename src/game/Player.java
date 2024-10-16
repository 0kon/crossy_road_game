package game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    private int x, y;
    private int width, height;
    private int speed = 10;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
    }

    // Update player logic
    public void update() {
        // Player-specific updates (if any)
    }


    // Draw the player
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }

     // Handle player movement with arrow keys
     public void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                y = Math.max(y - speed, 0); 
                break;
            case KeyEvent.VK_DOWN:
                y = Math.min(y + speed, 1080 - height);
                break;
            case KeyEvent.VK_LEFT:
                x = Math.max(x - speed, 0); 
                break;
            case KeyEvent.VK_RIGHT:
                x = Math.min(x + speed, 1920 - width); 
                break;
        }
    }

}
