package panels;

import game.GameWindow;
import javax.swing.*;
import utils.ProportionalLayout;


/**
 * The panel displayed before the game.
 */
public class StartPanel extends BasePanel {

    private JButton startButton;
    private JButton optionsButton;
    private JButton exitButton;
    private GameWindow gameWindow;

    /**
     * Draws content and letterbox.
     * @param gameWindow gameWindow class
     */
    public StartPanel(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;

        drawContent();
    }

    @Override
    protected void drawContent() {
        startButton = new JButton("Start Game");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");

        // Add action listeners for buttons
        startButton.addActionListener(e -> gameWindow.showGamePanel());
        optionsButton.addActionListener(e -> gameWindow.showOptionsPanel());
        exitButton.addActionListener(e -> System.exit(0));
       
        add(startButton, new ProportionalLayout.Constraints(640, 210, 640, 200)); 
        add(optionsButton, new ProportionalLayout.Constraints(640, 430, 640, 200)); 
        add(exitButton, new ProportionalLayout.Constraints(640, 650, 640, 200));  
    }
}
