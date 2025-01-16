# Snake Game Project

## Overview
This project is a simple implementation of the classic Snake game using Java and Swing. The player controls a snake that grows in size when it eats food, with the goal of achieving the highest score possible without hitting the boundaries or the snake's own body.

## Features
- **Dynamic Gameplay**: Snake grows in size as it eats food.
- **Score Tracking**: Displays the player's current score.
- **Win Condition**: The game ends with a victory if the entire grid is filled by the snake.
- **Restart Option**: Press `R` to restart the game after it ends.
- **Smooth Animations**: Uses a `Timer` for consistent game speed.
- **Grid Alignment**: Ensures that the snake and food stay aligned with the grid.

## Controls
- **Arrow Keys**: Control the direction of the snake.
  - `Up`: Move upward
  - `Down`: Move downward
  - `Left`: Move left
  - `Right`: Move right
- **R Key**: Restart the game after a game over.

## How It Works
1. **Game Mechanics**:
   - The snake moves in the direction specified by the player.
   - Food is randomly generated on the grid, avoiding the snake's body.
   - The game ends if the snake hits the boundary or itself.

2. **Collision Detection**:
   - The game checks if the snake's head overlaps with the food or its body.
   - Boundary collisions are detected to stop the game.

3. **Apple Generation**:
   - Food is generated randomly but ensures it does not overlap with the snake's body.

4. **Game Over**:
   - Displays a "Game Over" or "You Win!" message depending on the condition.
   - Allows the player to restart by pressing `R`.

## Installation
1. Ensure you have Java Development Kit (JDK) installed.
2. Clone or download the project files.
3. Compile the Java files using `javac`:
   ```bash
   javac SnakeGame.java GamePanel.java
   ```
4. Run the game using `java`:
   ```bash
   java SnakeGame
   ```

## Code Structure
- **SnakeGame.java**: Initializes the game window and sets up the main frame.
- **GamePanel.java**: Contains the game logic, rendering, and event handling.

## Customization
- **Game Speed**: Adjust the `Timer` interval in `GamePanel.java` to modify the game speed.
  ```java
  timer = new Timer(150, this); // Lower value = faster speed
  ```
- **Grid Size**: Modify `SCREEN_WIDTH`, `SCREEN_HEIGHT`, and `UNIT_SIZE` to change the grid dimensions and cell size.

## Example Gameplay
- **Start**: The snake starts at the center of the grid.
- **Eat Food**: The snake grows in size when it eats food.
- **Score Display**: The score is displayed at the top of the screen.
- **End**: The game ends if the snake hits the boundaries or itself.

## Future Enhancements
- Add difficulty levels.
- Introduce obstacles for increased challenge.
- Add a high score tracker.

## License
This project is open-source and free to use for educational purposes.
