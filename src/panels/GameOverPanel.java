package panels;

import game.GameWindow;
import javax.swing.*;
import utils.ProportionalLayout;

/**
 * Panel shown when game is over.
 */
public class GameOverPanel extends BasePanel {
    private int finalScore;
    private JLabel scoreLabel;
    private JLabel textLabel;

    /**
     * UI and letterbox are drawn.
     */
    public GameOverPanel(GameWindow gameWindow) {
        super(gameWindow);
        drawContent();
    }

    /**
     * Sets scoreLabel text.
     * @param finalScore score after game is over
     */
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
        scoreLabel.setText("Final score: " + finalScore); // Update score display
        revalidate();
        repaint();
    }

    @Override
    protected void drawContent() {

        textLabel = new JLabel("Game Over", JLabel.CENTER);
        scoreLabel = new JLabel("Final score: " + finalScore, JLabel.CENTER);

        add(textLabel, new ProportionalLayout.Constraints(640, 210, 640, 200, 80f)); 
        add(scoreLabel, new ProportionalLayout.Constraints(640, 300, 640, 200, 55f)); 

        revalidate();
        repaint();
    }
}
