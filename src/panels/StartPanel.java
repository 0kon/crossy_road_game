package panels;

import game.BasePanel;
import game.GameWindow;
import game.ProportionalLayout;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends BasePanel {

    private JButton startButton;
    private JButton exitButton;
    private GameWindow gameWindow;

    public StartPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        drawContent();

        
    }

    @Override
    protected void drawContent() {
        JButton startButton = new JButton("Start Game");
        JButton optionsButton = new JButton("Options");
        JButton exitButton = new JButton("Exit");

        // Add action listeners for buttons
        startButton.addActionListener(e -> gameWindow.showGamePanel());
        optionsButton.addActionListener(e -> gameWindow.showOptionsPanel());
        exitButton.addActionListener(e -> System.exit(0));
       
        
    
        

        // Add buttons with explicit position and size based on the base resolution (800x450)
        add(startButton, new ProportionalLayout.Constraints(640, 210, 640, 200)); 
        add(optionsButton, new ProportionalLayout.Constraints(640, 430, 640, 200)); 
        add(exitButton, new ProportionalLayout.Constraints(640, 650, 640, 200));  
    }
}
