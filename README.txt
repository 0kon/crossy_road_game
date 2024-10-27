Maciek Okoniewski, 
Carmel Keemink, 1889060
Group 56
Tutor: Plamen Nikolov

In this game, the player navigates a character through a grid while avoiding obstacles with a time limit to move. 

Game Features

Keyboard Control: Move the character using the arrow keys.
Dynamic Game States: Includes start screen, options menu, gameplay, and game-over screen.
Collision Detection: Ends the game upon obstacle collision, transitioning to a game-over screen.
Modular Panels: Modular design with separate StartPanel, GameOverPanel, and OptionsPanel for seamless transitions between states.

Usage Instructions

Starting the Game:
Run the program, and the start screen will appear. Press "Start" to begin playing.
Game Controls:
Use arrow keys to move the player character across the grid. Avoid colliding with obstacles to stay in the game.
Game Over:
If the player collides with an obstacle, the game transitions to a game-over screen with the option to restart.

Game settings
The game has various modes:
•	Easy: in this mode, the player has 7 seconds to move the character.
•	Medium: in this mode, the player has 5 seconds to move the character.
•	Hard: in this mode, the player has 3 seconds to move the character.

Window options
The game can be played either in fullscreen or in a window. 

Project Structure
Game.java: Main entry point, initializing game state and handling the game loop.
GameState.java: Enum representing game states such as START, PLAYING, and GAME_OVER.
GameWindow.java: Manages the game window, handling screen transitions and rendering.
KeyHandler.java: Detects player input and updates the player’s position.
Player.java: Defines the player character, movement, and collision methods.
Obstacle.java: Defines obstacles and their behavior on the screen.
Panels: Custom panels for different screens (start, options, game-over), located in StartPanel.java, OptionsPanel.java, and GameOverPanel.java.

Future enhancements
•	Add power-ups and additional challenge levels
•	Explore customization options for character
•	Add aesthetic design options

Topics of choice
Version control with git: We did not use it much because we worked on big parts of code simultaneously instead of working many times and updating small features.
User experience: Prioritized player experience by refining screen transitions and adding options for gameplay.
