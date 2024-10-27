package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * The Player class represents a player in the game.
 * It handles the player's position, movement, and rendering.
 */
public class Player {
    private int x;
    private int y;
    private int size = 100;
    private int speed;
    private boolean movingUp;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean isAnimating = false; 
    // Flag to track animation status
 
    private int frameCounter = 0;

    // Sequence of speeds for animation
    private final int[] sequence = {1, 2, 4, 6, 9, 12, 18, 22, 18, 13, 10, 6, 4, 2, 1}; 

    /**
     * Constructs a new Player object with the specified starting position.
     * @param startX the starting x-coordinate of the player
     * @param startY the starting y-coordinate of the player
     */

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void reset(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
    /**
     * Updates the player's position based on the current movement flags.
     * Also handles the animation of the player's movement.
     * This method should be called once per frame.
     */

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
    /**
     * Returns the x-coordinate of the player.
     * @return the x-coordinate of the player
     */

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    /**
     * Draws the player on the screen.
     * @param g the Graphics object to draw with
     * @param xOffset the x-coordinate offset to apply
     * @param yOffset the y-coordinate offset to apply
     * @param scale the scale to draw the player at
     */

    public void draw(Graphics g, int xOffset, int yOffset, double scale) {
        g.setColor(Color.BLUE);
        g.fillRect((int) (x * scale + xOffset + 14  * scale), 
                   (int) (y * scale + yOffset + 14 * scale),
                   (int) Math.round(size * scale),
                   (int) Math.round(size * scale));
    }
    /**
     * Handles key press events for the player.
     * @param keyCode the key code of the key that was pressed
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
