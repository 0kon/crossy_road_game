package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Player {
    private int x;
    private int y;
    private int speed = 5;
    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update() {
        if (movingUp) {
            y -= speed;
        }
        if (movingDown) {
            y += speed;
        }
        if (movingLeft) {
            x -= speed;
        }
        if (movingRight) {
            x += speed;
        }

        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > 1920) {
            x = 1920;
        }
        if (y > 1080) {
            y = 1080;
        }
    }

    public void draw(Graphics g,int xOffset, int yOffset, double scale) {
        g.setColor(Color.BLUE);
        g.fillRect((int) (x * scale) + xOffset, (int) (y * scale) + yOffset,
             (int) (50 * scale), (int) (50 * scale));
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            System.out.println("up");
            movingUp = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            movingDown = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            movingLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            movingRight = true;
        }
    }

    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            movingUp = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            movingDown = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            movingLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            movingRight = false;
        }
    }
}
