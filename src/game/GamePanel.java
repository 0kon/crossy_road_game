package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private static final double TARGET_ASPECT_RATIO = 16.0 / 9.0; // 16:9 aspect ratio
    private int gameWidth = 1920; // Original design width
    private int gameHeight = 1080; // Original design height
    private double scaleFactor = 1.0;
    private Player player;

    

    public GamePanel() {

        player =  new Player(20, 20);
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK); // Black background for letterboxing

        // Add resize listener to adjust scaling dynamically override swing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = getSize();
                scaleFactor = calculateScaleFactor(newSize);
                repaint(); // Repaint with the new scaling factor
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.handleKeyPress(e);
            }
        });
    }

    // Calculate scale factor based on window size, maintaining 16:9 aspect ratio
    private double calculateScaleFactor(Dimension screenSize) {
        double screenAspect = (double) screenSize.width / screenSize.height;

        // Maintain aspect ratio by scaling based on height or width depending on which is limiting
        if (screenAspect > TARGET_ASPECT_RATIO) {
            return (double) screenSize.height / gameHeight; // Limit by height
        } else {
            return (double) screenSize.width / gameWidth; // Limit by width
        }
    }

    // override swing
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Calculate the drawing area based on the aspect ratio
        int scaledWidth;
        int scaledHeight;
        int xOffset = 0;
        int yOffset = 0;

        // Window aspect ratio is wider than 16:9 then Add black bars on the left and right
        if (getWidth() / (double) getHeight() > TARGET_ASPECT_RATIO) {
            scaledHeight = getHeight();
            scaledWidth = (int) (scaledHeight * TARGET_ASPECT_RATIO);
            xOffset = (getWidth() - scaledWidth) / 2; // Center horizontally
        } else {
            // Window aspect ratio is taller than 16:9 then 
            // Add black bars on the top and bottom
            scaledWidth = getWidth();
            scaledHeight = (int) (scaledWidth / TARGET_ASPECT_RATIO);
            yOffset = (getHeight() - scaledHeight) / 2; // Center vertically
        }

        // Draw black letterbox
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // game field 
        g.setColor(Color.GREEN); 
        g.fillRect(xOffset, yOffset, scaledWidth, scaledHeight);

        // Scale graphics to the aspect ratio
        g2d.translate(xOffset, yOffset);
        g2d.scale((double) scaledWidth / gameWidth, (double) scaledHeight / gameHeight);

        // Render game components here (inside the letterboxed area)
        drawGameContent(g2d);

    }

    // method to handle drawing game content like player, obstacles, etc.
    private void drawGameContent(Graphics2D g) {
        
        int obstacleWidth = 640; 
        int obstacleHeight = 540; 
        g.setColor(Color.BLUE);
     
        g.fillRect(0, 0, obstacleWidth, obstacleHeight);


        g.setColor(Color.RED);
        
        g.fillRect(640, 0, obstacleWidth, obstacleHeight);

        g.setColor(Color.yellow);
        g.fillRect(1280, 0, obstacleWidth, obstacleHeight);

        g.setColor(Color.gray);
        g.fillRect(1280, 540, obstacleWidth, obstacleHeight);
        
        player.draw(g);
        
        
    }
}
