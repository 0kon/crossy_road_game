package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * 
 */
public class Player {
    private int x;
    private int y;
    private int size = 100;
    private int speed;
    private boolean movingUp;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean isAnimating = false; // Flag to track animation status
 
    private int frameCounter = 0;

    // Sequence of speeds for animation
    private final int[] sequence = {1, 2, 4, 6, 9, 12, 18, 22, 18, 13, 10, 6, 4, 2, 1}; 

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void reset(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update() {
        if (isAnimating && frameCounter > 0) {
            frameCounter -= 1;
            speed = sequence[frameCounter];

            if (movingLeft) {
                x -= speed;
            }
            if (movingRight) {
                x += speed;
            }
        }

        // Ensure the object stays within bounds
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x >= 1920 - size) {
            x = 1920 - size;
        }
        if (y >= 1024 - size) {
            y = 1024 - size;
        }

        // Snap to nearest multiple of 128 when animation stops
        if (frameCounter == 0 && isAnimating) {
            isAnimating = false;
            x = ((x + 64) / 128) * 128;
            y = ((y + 64) / 128) * 128;
            resetMovementFlags();
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public void draw(Graphics g, int xOffset, int yOffset, double scale) {
        g.setColor(Color.BLUE);
        g.fillRect((int) (x * scale + xOffset + 14  * scale), 
                   (int) (y * scale + yOffset + 14 * scale),
                   (int) Math.round(size * scale),
                   (int) Math.round(size * scale));
    }
    /**
     * Handels 
     * @param keyCode key code of
     */
    public void keyPressed(int keyCode) {
        if (!isAnimating) {
            if (keyCode == KeyEvent.VK_UP) {
                movingUp = true;
            } else if (keyCode == KeyEvent.VK_LEFT) {
                movingLeft = true;
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                movingRight = true;
            }
            if (movingUp || movingLeft || movingRight) {
                isAnimating = true;
                frameCounter = 15;
            }
        }
    }


    // Helper method to reset movement flags
    private void resetMovementFlags() {
        movingUp = false;
        movingLeft = false;
        movingRight = false;
    }
}
