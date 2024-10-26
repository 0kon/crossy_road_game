package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player {
    private int x;
    private int y;
    private int size = 128;
    private int speed = 128;
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean isAnimating = false; // New flag to track animation status
 
    private int frameCounter = 0;
    private final int[] sequence = {1, 2, 4, 6, 9, 12, 18, 22, 18, 13, 10, 6, 4, 2, 1};

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update() {
        if (isAnimating && frameCounter > 0) {
            frameCounter -= 1;
            speed = sequence[frameCounter];

            // if (movingUp) {
            //     y -= speed;
            // }
            // if (movingDown) {
            //     y += speed;
            // }
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

    public void draw(Graphics g, int xOffset, int yOffset, double scale) {
        g.setColor(Color.BLUE);
        g.fillRect((int) (x * scale) + xOffset, (int) (y * scale) + yOffset,
                (int) (size * scale), (int) (size * scale));
    }

    public void keyPressed(int keyCode) {
        if (!isAnimating) {
            if (keyCode == KeyEvent.VK_UP) {
                movingUp = true;
            } else if (keyCode == KeyEvent.VK_DOWN) {
                movingDown = true;
            } else if (keyCode == KeyEvent.VK_LEFT) {
                movingLeft = true;
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                movingRight = true;
            }
            if (movingUp || movingDown || movingLeft || movingRight) {
                isAnimating = true;
                frameCounter = 15;
            }
        }
    }

    public void keyReleased(int keyCode) {
        // Ignore key releases while animating to ensure animation finishes
    }

    // Helper method to reset movement flags
    private void resetMovementFlags() {
        movingUp = false;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
    }
}
