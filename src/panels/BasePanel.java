package panels;

import javax.swing.*;

import game.GameWindow;
import utils.ProportionalLayout;
import java.awt.*;

public abstract class BasePanel extends JPanel {

    protected final double aspectRatio = 16.0 / 9.0;
    private GameWindow gameWindow;

    public BasePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        
        // Set the custom layout manager with the aspect ratio
        setLayout(new ProportionalLayout(aspectRatio, 1920, 1080));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fill the entire background with black to create letterbox effect
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Calculate the scaled game area (white area) dimensions while maintaining the aspect ratio
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double panelAspectRatio = (double) panelWidth / panelHeight;

        int gameWidth;
        int gameHeight;
        if (panelAspectRatio > aspectRatio) {
            // The window is wider than the game area, so we use the height as the limiting factor
            gameHeight = panelHeight;
            gameWidth = (int) (gameHeight * aspectRatio);
        } else {
            // The window is taller than the game area, so we use the width as the limiting factor
            gameWidth = panelWidth;
            gameHeight = (int) (gameWidth / aspectRatio);
        }

        // Calculate offsets to center the white game area in the middle of the panel
        int xOffset = (panelWidth - gameWidth) / 2;
        int yOffset = (panelHeight - gameHeight) / 2;

        // Fill the game area with white (this is the game area itself)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(xOffset, yOffset, gameWidth, gameHeight);
    }

    // // Abstract method that subclasses must implement to draw their content
    protected abstract void drawContent();
}
