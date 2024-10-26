package panels;

import game.GameWindow;

import game.Game;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends BasePanel implements ActionListener {
    private Game game;
    private GameWindow gameWindow;
    private int width;
    private int height;
    private Timer gameTimer;  // Timer for game loop
    private final int FPS = 60;  // Frames per second
    private final int frameTime = 1000 / FPS;  // Time per frame in milliseconds

    public GamePanel(GameWindow gameWindow) {
        super(gameWindow);
        this.gameWindow = gameWindow;
        this.game = new Game();  // Initialize the game logic

        // Set up the Timer for the game loop (fires every frameTime ms)
        this.gameTimer = new Timer(frameTime, this);
        System.out.println(aspectRatio);
        System.out.println(baseHeight);
        System.out.println(baseWidth);
        
        // int panelWidth = getWidth();
        // int panelHeight = getHeight();
        // System.out.println(panelHeight);
        // System.out.println(panelWidth);

        // Set focus to receive key events
        // this.setFocusable(true);

    }
    
        
    
    @Override
    protected void drawContent()
        {
            // ui if wanted like buttons etc
            ;
        }

    // Start the game loop (starts the timer)
    public void startGame() {
        gameTimer.start();

    }

    // Stop the game loop (stops the timer)
    public void stopGame() {
        gameTimer.stop();
    }

    // ActionListener method: called every frame by the Timer
    @Override
    public void actionPerformed(ActionEvent e) {
        

        
        game.update();  // Update game logic
        repaint();  // Redraw the game
    }

    // Paint method: handles drawing the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
        

    }

    public Game getGame() {
        return game;
    }
}


