package panels;

import game.GameWindow;
import javax.swing.*;
import utils.ProportionalLayout;

public class GameOverPanel extends BasePanel {

    public GameOverPanel(GameWindow gameWindow) {
        super(gameWindow);
        // this.gameWindow = gameWindow;
        

        drawContent();

        
    }

    @Override
    protected void drawContent() {
        JLabel text1 = new JLabel("Game Over", JLabel.CENTER);
        
        

        // Add buttons with explicit position and size based on the base resolution (800x450)
        add(text1, new ProportionalLayout.Constraints(640, 210, 640, 200, 60f)); 

    }
}
