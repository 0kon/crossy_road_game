package game;
import java.awt.Graphics;


public class Game {
    private Player player;

    public Game() {

        
        player = new Player(0, 0); 

    }

    public void update() {
        player.update();  // Update player position and state
    }

    public void render(Graphics g, int xOffset, int yOffset, double scale) {
        player.draw(g,xOffset, yOffset, scale);  // Render player
    }

    

    public void handleKeyPress(int keyCode) {
        player.keyPressed(keyCode);  // Pass key press to player
        System.out.println("ddd");
    }

    public void handleKeyRelease(int keyCode) {
        player.keyReleased(keyCode);  // Pass key release to player
    }
}
