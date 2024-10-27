package panels;

import game.GameWindow;
import utils.ProportionalLayout;
import game.Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import utils.ProportionalLayout;

public class GamePanel extends BasePanel implements ActionListener {
    private Game game;
    private GameWindow gameWindow;
    private int width;
    private int height;
    private Timer gameTimer;  // Timer for game loop
    private Timer countdownTimer;
    private JButton finishRunButton;

    private final int FPS = 120;  // Frames per second
    private int baseCountdown; // Start countdown from 5
    private int countdown;
    private JLabel countdownLabel;
    private final int frameTime = 1000 / FPS;  // Time per frame in milliseconds
    private JLabel scoreLabel; // Score display label
    private int lastScore = 0;

    public GamePanel(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;
        this.game = new Game();  // Initialize the game logic

        this.gameTimer = new Timer(frameTime, this);
        baseCountdown = 50;
        countdown = baseCountdown;


    }

    private void endGame() {
        gameTimer.stop();        // Stop the main game loop
        countdownTimer.stop();    // Stop the countdown timer
        gameWindow.showGameOverPanel(game.getScore()); // Show Game Over screen with final score
    }

    public void startGame() {
        initializeComponents();
        gameTimer.start();
        drawContent();
        countdownTimer.start();
        
        
    }
    
        
    

    private void initializeComponents() {
        // Initialize and add countdownLabel
        countdown = baseCountdown;
        countdownLabel = new JLabel("Time remaining: " + (countdown / 10.0), JLabel.CENTER);
        add(countdownLabel, new ProportionalLayout.Constraints(640, 0, 640, 100, 60f));
    
        // Initialize countdownTimer once
        countdownTimer = new Timer(100, e -> {
            if (game.playerMovedUp()) {
                countdown = baseCountdown;
            }
            if (countdown > 0) {
                countdown--;
                countdownLabel.setText("Time remaining: " + (countdown / 10.0));
            } else {
                endGame();
            }
        });
        countdownTimer.start();
    
        // Initialize and add finishRunButton
        JButton finishRunButton = new JButton("Finish run");
        finishRunButton.addActionListener(e -> gameWindow.showGameOverPanel(game.getScore()));
        add(finishRunButton, new ProportionalLayout.Constraints(1600, 20, 300, 100));
    
        // Initialize and add scoreLabel
        scoreLabel = new JLabel("Score: " + game.getScore(), JLabel.LEFT);
        add(scoreLabel, new ProportionalLayout.Constraints(20, 0, 500, 100, 60f));
    }
    
    @Override
    protected void drawContent() {
        // Update countdown and score labels without re-adding components
        countdownLabel.setText("Time remaining: " + (countdown / 10.0));
    
        int currentScore = game.getScore();
        if (lastScore != currentScore) {
            scoreLabel.setText("Score: " + currentScore);
            lastScore = currentScore;
        }
    
        revalidate(); 
        repaint();
    }

    


    public void changeDifficulty(int difficulty) {   
        
        switch (difficulty) {
            case 1:
                baseCountdown = 70;
                break;
            case 2:
                baseCountdown = 50;;
                break;
            case 3:
                baseCountdown = 30;
                break;
            default:
                baseCountdown = 50;
                break;

        }
            
    }


    // ActionListener method: called every frame by the Timer
    public void actionPerformed(ActionEvent e) {
        game.update(); // Update game logic
        


        if (game.isGameOver()) {
            gameWindow.showGameOverPanel(game.getScore());
            stopGame(); // Stop the game loop
            return;
        }
        // Check if the score has changed
        int currentScore = game.getScore();



        if (currentScore != lastScore) {
            lastScore = currentScore; // Update lastScore
            scoreLabel.setText("Score: " + currentScore); // Update the score display
        } // Refresh layout if needed
        
        
    
        repaint(); // Redraw the game
    }

    // Paint method: handles drawing the game
    @Override
    protected void paintComponent(Graphics g) {
        
        // Always call the superclass method first
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calculate scaling and offsets
        double scaleWidth = (double) panelWidth / baseWidth;
        double scaleHeight = (double) panelHeight / baseHeight;
        double scale = Math.min(scaleWidth, scaleHeight);  // Uniform scale for aspect ratio

        int xOffset = (panelWidth - (int)(baseWidth * scale)) / 2;
        int yOffset = (panelHeight - (int)(baseHeight * scale)) / 2;

        
        game.render(g, xOffset, yOffset, scale);  // Delegate rendering to the game logic
        super.paintComponent(g);

    }
    
    // Stop the game loop (stops the timer)
    public void stopGame() {
        gameTimer.stop();
    }

    public Game getGame() {
        return game;
    }
}


