package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Game {
    private Player player;
    private List<Obstacle> obstacles;
    private int fieldEndX;
    private int screenHeight;
    private int rowHeight = 128;      // Height of each row for obstacle placement
    private Random random;
    private boolean rowGenerated = false;

    public Game() {
        player = new Player(896, 640);
        obstacles = new ArrayList<>();
        fieldEndX = 1920;
        screenHeight = 1080; // Set to the height of your game screen
        random = new Random();
    }

    public void update() {
        player.update();

        // Update each obstacle's movement and remove if it reaches y = 1920
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.update();

            // Remove obstacle if it moves off the screen at y = 1080
            if (obstacle.getY() >= 1080) {
                iterator.remove();
            }
        }

        // Check if all obstacles have finished animating
        if (rowGenerated && allObstaclesFinishedAnimating()) {
            spawnObstacles();
            rowGenerated = false; // Reset to allow UP key to trigger again
        }
    }

    // Helper method to check if all obstacles are done animating
    private boolean allObstaclesFinishedAnimating() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isAnimating()) {
                return false;
            }
        }
        return true;
    }

    // Method to spawn a row of obstacles slightly above the top of the screen with a clear path
    private void spawnObstacles() {
        int numObstacles = random.nextInt(13) + 1; // Random number of obstacles (1 to 13)
        int pathX = random.nextInt(15) * 128;      // Reserved path position

        Set<Integer> usedPositions = new HashSet<>();
        usedPositions.add(pathX); // Ensure pathX remains clear

        for (int i = 0; i < numObstacles; i++) {
            int randomX;
            do {
                randomX = random.nextInt(15) * 128;
            } while (usedPositions.contains(randomX));

            usedPositions.add(randomX);
            Obstacle newObstacle = new Obstacle(randomX, -128); // Place obstacles slightly above the screen
            obstacles.add(newObstacle);
        }
    }

    public void render(Graphics g, int xOffset, int yOffset, double scale) {
        player.draw(g, xOffset, yOffset, scale);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g, xOffset, yOffset, scale);
        }
    }

    public void handleKeyPress(int keyCode) {
        player.keyPressed(keyCode);

        // Only generate a new row if UP arrow is pressed and no row is currently animating
        if (keyCode == KeyEvent.VK_UP && !rowGenerated) {
            for (Obstacle obstacle : obstacles) {
                obstacle.keyPressed(keyCode); // Trigger downward animation for all obstacles
            }
            rowGenerated = true; // Set flag to indicate a row is being animated
        }
    }

    public void handleKeyRelease(int keyCode) {
        player.keyReleased(keyCode);
        for (Obstacle obstacle : obstacles) {
            obstacle.keyReleased(keyCode);
        }
    }
}
