package panels;

import game.GameLoop;
import game.GameWindow;
import java.awt.*;
import javax.swing.*;
import utils.ProportionalLayout;


public class GamePanel extends BasePanel {

    private GameLoop gameLoop;

    private GameWindow gameWindow;

    public GamePanel(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;
        // this.basePanel = basePanel;
        gameLoop = new GameLoop(this);

        drawContent();
    }

    @Override
    protected void drawContent()
    {
        // JButton startButton = new JButton("Start Game");
        JLabel label = new JLabel("start", JLabel.CENTER);

        // Set font color (foreground) for better contrast
        label.setForeground(Color.BLACK);

        label.setOpaque(true);  // JLabel needs to be opaque for background color to be visible
        label.setBackground(Color.YELLOW);
        

        // add(startButton, new ProportionalLayout.Constraints(640, 210, 640, 200)); 
        // add(optionsButton, new ProportionalLayout.Constraints(640, 430, 640, 200)); 
        add(label, new ProportionalLayout.Constraints(640, 200, 640, 640));  
    }

    // Start the game
    public void startGame() {
        gameLoop.start();  // Start the game loop
    }


}
