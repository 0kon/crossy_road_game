package game;

import java.awt.Graphics;
import java.awt.Rectangle;
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
    private Random random;
    private boolean rowGenerated = false;
    private int currentPathStartColumn; // Track the start of the clear path for continuity
    private int pathWidth;               // Width of the clear path, which will vary
    private int score; 

    public Game() {
        player = new Player(896, 640);
        obstacles = new ArrayList<>();
        random = new Random();
        currentPathStartColumn = random.nextInt(14 - pathWidth)+1;
        pathWidth = 3;         
        score = 0;  

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
            rowGenerated = false; // Set to false to allow UP key to trigger again
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
        pathWidth = random.nextInt(7) + 2;
        score++;
        System.out.println(score);
        // Adjust current path start to slightly move left, right, or stay the same within bounds
        int direction = random.nextInt(3) - 1; // -1 (left), 0 (same), 1 (right)
        currentPathStartColumn = Math.max(1, 
            Math.min(currentPathStartColumn + direction, 14 - pathWidth));
    
        // Reserve the columns for the clear path
        Set<Integer> pathColumns = new HashSet<>();
        for (int i = currentPathStartColumn; i < currentPathStartColumn + pathWidth; i++) {
            pathColumns.add(i);
        }
    
        // Generate obstacles in columns not part of the path
        for (int col = 0; col < 15; col++) {
            if (!pathColumns.contains(col)) {
                int obstacleX = col * 128;
                // Place obstacles above the screen
                Obstacle newObstacle = new Obstacle(obstacleX, -128); 
                obstacles.add(newObstacle);
            }
        }
    }
    /**
     * Checks if the player collides with an obstacle.
     * @return true if the player collides with the obstacle, false otherwise
     */
    public boolean checkCollision() {
        Rectangle playerBounds = player.getBounds();

        for (Obstacle obstacle : obstacles) {
            Rectangle obstacleBounds = obstacle.getBounds();
            if (playerBounds.intersects(obstacleBounds)) {
                return true; 
            }
        }
        return false; 
    }

    

    public void render(Graphics g, int xOffset, int yOffset, double scale) {
        player.draw(g, xOffset, yOffset, scale);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g, xOffset, yOffset, scale);
        }
    }

    public boolean isGameOver() {
        return checkCollision();
    }

    public boolean playerMovedUp() {
        return rowGenerated;
    }

    public int getScore() {
        return score;
    }

    public void handleKeyPress(int keyCode) {
        player.keyPressed(keyCode);
        

        // Only generate a new row if UP arrow is pressed and no row is currently animating
        if (keyCode == KeyEvent.VK_UP && !rowGenerated) {
            for (Obstacle obstacle : obstacles) {
                obstacle.keyPressed(keyCode); 
            }
            rowGenerated = true; // Set flag to indicate a row is being animated
        }
        
    }

    // public void handleKeyRelease(int keyCode) {
    //     player.keyReleased(keyCode);
    //     for (Obstacle obstacle : obstacles) {
    //         obstacle.keyReleased(keyCode);
    //     }
    // }
}
