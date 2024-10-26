package game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game {
    private Player player;
    private List<Obstacle> obstacles; // List to hold obstacles
    private int fieldEndX;            // Boundary for obstacle removal (e.g., 1920px screen width)
    private Random random;             // Random generator for initial positions
    private int obstacleSpawnY = 0;    // Initial y position for new obstacles

    public Game() {
        player = new Player(1920, 640); // Adjusted for screen width of 1920
        obstacles = new ArrayList<>();
        fieldEndX = 1920; // Set to the width of your game screen (1920px)
        random = new Random(); // Initialize Random instance
    }

    // Continuously updates each obstacle's position and removes off-screen obstacles
    public void update() {
        player.update();

        // Update each obstacle's movement
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.update(); // Each obstacle handles its own movement

            // Check if obstacle has moved off-screen and remove if it has
            if (obstacle.getX() > fieldEndX) {
                iterator.remove();
            }
        }
    }

    // Method to spawn a new obstacle at a random x position in the first row
    private void spawnObstacle() {
        int randomX = random.nextInt(fieldEndX); // Random x position within screen width
        Obstacle newObstacle = new Obstacle(randomX, obstacleSpawnY);
        obstacles.add(newObstacle);
    }

    // Render player and all obstacles
    public void render(Graphics g, int xOffset, int yOffset, double scale) {
        player.draw(g, xOffset, yOffset, scale);

        // Render all obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g, xOffset, yOffset, scale);
        }
    }

    // Handles key press events and spawns a new obstacle on UP key press
    public void handleKeyPress(int keyCode) {
        player.keyPressed(keyCode);

        // Spawn a new obstacle if UP arrow key is pressed
        if (keyCode == KeyEvent.VK_UP) {
            spawnObstacle();
        }

        // Pass the key press to each obstacle
        for (Obstacle obstacle : obstacles) {
            obstacle.keyPressed(keyCode);
        }
    }

    public void handleKeyRelease(int keyCode) {
        player.keyReleased(keyCode);

        // Pass the key release to each obstacle
        for (Obstacle obstacle : obstacles) {
            obstacle.keyReleased(keyCode);
        }
    }
}
