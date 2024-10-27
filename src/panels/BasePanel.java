package panels;

import javax.swing.*;

import game.GameWindow;
import utils.ProportionalLayout;
import java.awt.*;

public abstract class BasePanel extends JPanel {

    protected final double aspectRatio = 16.0 / 9.0;
    private GameWindow gameWindow;
    protected int baseWidth  = 1920;
    protected int baseHeight = 1080;
    protected int xOffset;
    protected int yOffset;
    protected int gameWidth;
    protected int gameHeight;

    public BasePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        
        // Set the custom layout manager with the aspect ratio
        setLayout(new ProportionalLayout(aspectRatio, baseWidth, baseHeight));

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double panelAspectRatio = (double) panelWidth / panelHeight;
    
        int gameWidth;
        int gameHeight;
        if (panelAspectRatio > aspectRatio) {
            // The window is wider than the game area, so use the height as the limiting factor
            gameHeight = panelHeight;
            gameWidth = (int) (gameHeight * aspectRatio);
        } else {
            // The window is taller than the game area, so use the width as the limiting factor
            gameWidth = panelWidth;
            gameHeight = (int) (gameWidth / aspectRatio);
        }
    
        // Calculate offsets to center the game area in the middle of the panel
        int xOffset = (panelWidth - gameWidth) / 2;
        int yOffset = (panelHeight - gameHeight) / 2;
    
        // Fill the letterbox areas with black
        g2d.setColor(Color.BLACK);
    
        // Top letterbox
        if (yOffset > 0) {
            g2d.fillRect(0, 0, panelWidth, yOffset);
        }
    
        // Bottom letterbox
        if (yOffset > 0) {
            g2d.fillRect(0, yOffset + gameHeight, panelWidth, panelHeight - (yOffset + gameHeight));
        }
    
        // Left letterbox
        if (xOffset > 0) {
            g2d.fillRect(0, 0, xOffset, panelHeight);
        }
    
        // Right letterbox
        if (xOffset > 0) {
            g2d.fillRect(xOffset + gameWidth, 0, panelWidth - (xOffset + gameWidth), panelHeight);
        }
    
        // Do not fill the game area; it will remain transparent
    }
    

    protected abstract void drawContent();
}
