package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * Represents an obstacle in the game.
 */
public class Obstacle {
    private int x;
    private int y;
    private int size = 128;
    private int speed;
    private boolean movingDown;
    private boolean isAnimating = false;
    private int frameCounter = 0;
    private final int[] sequence = {1, 2, 4, 6, 9, 12, 18, 22, 18, 13, 10, 6, 4, 2, 1};

    /**
     * Constructs a new Obstacle at the specified starting position.
     *
     * @param startX the starting x-coordinate of the obstacle
     * @param startY the starting y-coordinate of the obstacle
     */
    public Obstacle(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
    // Getters for x, y and isAnimating

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
    
    /**
     * Updates the obstacle's position based on the current movement flags.
     * Also handles the animation of the obstacle's movement.
     * This method should be called once per frame.
     */
    public void update() {
        if (isAnimating && frameCounter > 0) {
            frameCounter -= 1;
            speed = sequence[15 - frameCounter - 1]; // Access speed from animation sequence

            if (movingDown) {
                y += speed; // Move down
            }
        }

        // Snap to nearest multiple of 128 when animation stops
        if (frameCounter == 0 && isAnimating) {
            isAnimating = false;
            y = ((y + 64) / 128) * 128; // Snap to grid
            movingDown = false;         // Reset moving flag
        }
    }
    /**
     * Draws the obstacle on the screen.
     *
     * @param g the Graphics object to draw with
     * @param xOffset the x-coordinate offset to apply
     * @param yOffset the y-coordinate offset to apply
     * @param scale the scale to draw the obstacle at
     */

    public void draw(Graphics g, int xOffset, int yOffset, double scale) {
        g.setColor(Color.GRAY);
        g.fillRect((int) (x * scale) + xOffset, (int) (y * scale) + yOffset,
                (int) Math.ceil((size * scale) + 1), (int) Math.ceil((size * scale) + 1));
    }
    /**
     * Handles key press events for the obstacle.
     *
     * @param keyCode the key code of the key that was pressed
     */
    
    public void keyPressed(int keyCode) {
        if (!isAnimating && keyCode == KeyEvent.VK_UP) {
            movingDown = true;          // Set downward movement flag
            isAnimating = true;
            frameCounter = 15;          // Start animation
        }
    }

    public void keyReleased(int keyCode) {
        // No need to handle key release
    }
}
