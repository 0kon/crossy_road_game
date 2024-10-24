package panels;

import javax.swing.*;

import utils.ProportionalLayout;

import java.awt.*;

public abstract class BasePanel extends JPanel {

    protected final double aspectRatio = 16.0 / 9.0;

    public BasePanel() {
        // Set the custom layout manager with the aspect ratio
        setLayout(new ProportionalLayout(aspectRatio, 1920, 1080));


        // It isnt supposed to be there its just a test \/

        // Create buttons
        // JButton startButton = new JButton("Start Game");
        // JButton optionsButton = new JButton("Options");
        // JButton label = new JButton("Exit");

        
        // // Set font color (foreground) for better contrast
        // label.setForeground(Color.BLACK);

        // label.setHorizontalAlignment(SwingConstants.CENTER);  // Horizontally centered
        // label.setVerticalAlignment(SwingConstants.CENTER);
        

        // // Add buttons with explicit position and size based on the base resolution (800x450)
        // add(startButton, new ProportionalLayout.Constraints(640, 210, 640, 200)); 
        // add(optionsButton, new ProportionalLayout.Constraints(640, 430, 640, 200)); 
        // add(label, new ProportionalLayout.Constraints(640, 650, 640, 200));  


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

    // @Override
    // protected void paintComponent(Graphics g) {
    //     super.paintComponent(g);

    //     // Get the current panel dimensions
    //     int panelWidth = getWidth();
    //     int panelHeight = getHeight();

    //     // Calculate the largest area that maintains the aspect ratio
    //     int targetWidth = panelWidth;
    //     int targetHeight = (int) (panelWidth / aspectRatio);

    //     if (targetHeight > panelHeight) {
    //         targetHeight = panelHeight;
    //         targetWidth = (int) (panelHeight * aspectRatio);
    //     }

    //     // Calculate the black bars' positions
    //     int xOffset = (panelWidth - targetWidth) / 2;
    //     int yOffset = (panelHeight - targetHeight) / 2;

    //     // Fill the background with black (letterbox effect)
    //     g.setColor(Color.BLACK);
    //     g.fillRect(0, 0, panelWidth, panelHeight);

    //     // Draw the actual content
    //     Graphics2D g2d = (Graphics2D) g.create();
    //     g2d.translate(xOffset, yOffset);

    //     // Delegate to child panels to draw their specific content
    //     drawContent(g2d, targetWidth, targetHeight);

    //     g2d.dispose();
    // }

    // // Abstract method that subclasses must implement to draw their content
    protected abstract void drawContent();
}
