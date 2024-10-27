package panels;

import game.GameWindow;
import utils.ProportionalLayout;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends BasePanel {


    // example where you can init buttons to acces them in whole class
    private JButton fullScreeButton;
    private JButton difficultyButton;
    private int difficulty;
    // private JButton exitButton;
  

    private GameWindow gameWindow;

    public OptionsPanel(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;

        drawContent();

        
    }

    @Override
    protected void drawContent() {

        System.out.println(gameWindow.isFullscreen);
        if (gameWindow.isFullscreen) {
            fullScreeButton = new JButton("Fullscreen");
        } else {
            fullScreeButton = new JButton("Windowed");
        }

        // Add action listener
        fullScreeButton.addActionListener(e -> {
            gameWindow.toggleFullScreen();
            if (gameWindow.isFullscreen) {
                fullScreeButton.setText("Fullscreen");
            } else {
                fullScreeButton.setText("Windowed");
            }

        });

        difficulty = 2;
        difficultyButton = new JButton("Medium (time = 5s)");

        difficultyButton.addActionListener(e -> {
            difficulty += 1;
            if (difficulty > 3) {
                difficulty = 1;
            }
            gameWindow.getGamePanel().changeDifficulty(difficulty);

            switch (difficulty) {
                case 1:
                    difficultyButton.setText("Easy (time = 7s)");
                    break;
                case 2:
                    difficultyButton.setText("Medium (time = 5s)");
                    break;
                case 3:
                    difficultyButton.setText("Hard (time = 3s)");
                    break;
                default:
                    break;
            }

        });
        JButton option3 = new JButton("Return to start menu"); // To test game over Panel when there are options should be changed

        
        // difficultyButton.addActionListener(e -> System.out.println("option 2"));
        option3.addActionListener(e -> gameWindow.showStartPanel());
        
        

        // Add buttons with explicit position and size
        add(fullScreeButton, new ProportionalLayout.Constraints(640, 210, 640, 200)); 
        add(difficultyButton, new ProportionalLayout.Constraints(640, 430, 640, 200)); 
        add(option3, new ProportionalLayout.Constraints(640, 650, 640, 200)); 
        revalidate();
        repaint(); 
    }
}
