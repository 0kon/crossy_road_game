package panels;

import game.GameWindow;
import utils.ProportionalLayout;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends BasePanel {


    // example where you can init buttons to acces them in whole class
    // private JButton startButton;
    // private JButton exitButton;


    private GameWindow gameWindow;

    public OptionsPanel(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;

        drawContent();

        
    }

    @Override
    protected void drawContent() {
        JButton option1 = new JButton("Option 1");
        JButton option2 = new JButton("Option 2");
        JButton option3 = new JButton("Game over"); // To test game over Panel when there are options should be changed

        // Add action listeners for buttons
        option1.addActionListener(e -> System.out.println("option 1"));
        option2.addActionListener(e -> System.out.println("option 2"));
        option3.addActionListener(e -> gameWindow.showGameOverPanel());
        

        // Add buttons with explicit position and size
        add(option1, new ProportionalLayout.Constraints(640, 210, 640, 200)); 
        add(option2, new ProportionalLayout.Constraints(640, 430, 640, 200)); 
        add(option3, new ProportionalLayout.Constraints(640, 650, 640, 200));  
    }
}
