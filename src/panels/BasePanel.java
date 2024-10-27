package panels;

import game.GameWindow;
import java.awt.*;
import javax.swing.*;
import utils.ProportionalLayout;


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
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK); // Color for the letterbox areas

    int panelWidth = getWidth();
    int panelHeight = getHeight();
    double panelAspectRatio = (double) panelWidth / panelHeight;

    int gameWidth;
    int gameHeight;
    if (panelAspectRatio > aspectRatio) {
        gameHeight = panelHeight;
        gameWidth = (int) (gameHeight * aspectRatio);
    } else {
        gameWidth = panelWidth;
        gameHeight = (int) (gameWidth / aspectRatio);
    }

    int xOffset = (panelWidth - gameWidth) / 2;
    int yOffset = (panelHeight - gameHeight) / 2;

    // Fill only the letterbox areas, leaving the game area transparent

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

    // Render transparent game area here (optional)
    // You can render game content here without filling, so the area remains transparent
}

    

    protected abstract void drawContent();
}
