package panels;


import java.awt.*;
import javax.swing.*;

import game.GameWindow;
import utils.ProportionalLayout;
import game.GameLoop;
import game.GameState;



public class GamePanel extends BasePanel {

    private GameLoop gameLoop;
    private GameState gameState;
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
        

        // Add buttons with explicit position and size based on the base resolution (800x450)
        // add(startButton, new ProportionalLayout.Constraints(640, 210, 640, 200)); 
        // add(optionsButton, new ProportionalLayout.Constraints(640, 430, 640, 200)); 
        add(label, new ProportionalLayout.Constraints(640, 200, 640, 640));  
    }

    // Start the game
    public void startGame() {
        gameLoop.start();  // Start the game loop
        gameState = GameState.IN_PLAY;
    }


}
