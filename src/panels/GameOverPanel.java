package panels;

import game.GameWindow;
import utils.ProportionalLayout;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends BasePanel {


    // example where you can init buttons to acces them in whole class
    // private JButton startButton;
    // private JButton exitButton;


    private GameWindow gameWindow;

    public GameOverPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        drawContent();

        
    }

    @Override
    protected void drawContent() {
        JLabel text1 = new JLabel("Game Over", JLabel.CENTER);
        
        

        // Add buttons with explicit position and size based on the base resolution (800x450)
        add(text1, new ProportionalLayout.Constraints(640, 210, 640, 200, 60f)); 

    }
}
