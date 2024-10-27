package panels;

import game.GameWindow;
import javax.swing.*;
import utils.ProportionalLayout;


public class GameOverPanel extends BasePanel {
    private int finalScore;
    private JLabel scoreLabel;

    public GameOverPanel(GameWindow gameWindow) {
        super(gameWindow);
        

        drawContent();

        

        
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
        scoreLabel.setText("Final score: " + finalScore); // Update score display
        revalidate();
        repaint();
    }

    @Override
    protected void drawContent() {

        JLabel textLabel = new JLabel("Game Over", JLabel.CENTER);
        scoreLabel = new JLabel("Final score: "+ finalScore , JLabel.CENTER);
        
        

        // Add buttons with explicit position and size based on the base resolution (800x450)
        add(textLabel, new ProportionalLayout.Constraints(640, 210, 640, 200, 80f)); 
        add(scoreLabel, new ProportionalLayout.Constraints(640, 300, 640, 200, 55f)); 

        revalidate();
        repaint();
    }
}
