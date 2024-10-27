package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Obstacle {
    private int x;
    private int y;
    private int size = 128;
    private int speed;
    private boolean movingDown;
    private boolean isAnimating = false;

    private int frameCounter = 0;
    private final int[] sequence = {1, 2, 4, 6, 9, 12, 18, 22, 18, 13, 10, 6, 4, 2, 1};

    public Obstacle(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

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

    public void draw(Graphics g, int xOffset, int yOffset, double scale) {
        g.setColor(Color.GRAY);
        g.fillRect((int) (x * scale) + xOffset, (int) (y * scale) + yOffset,
                (int) Math.ceil((size * scale) + 1), (int) Math.ceil((size * scale) + 1));
    }

    public void keyPressed(int keyCode) {
        if (!isAnimating && keyCode == KeyEvent.VK_UP) {
            movingDown = true;          // Set downward movement flag
            isAnimating = true;
            frameCounter = 15;          // Start animation
        }
    }
}
