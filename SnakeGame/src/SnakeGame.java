

import javax.swing.JFrame;

public class SnakeGame  {
	JFrame frame;
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	GamePanel panel = new GamePanel();
	
	SnakeGame() {
		frame = new JFrame("Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes after clicking on X
		frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT); // Set the size to 600 by 600
		frame.setResizable(false); // Window is not resizeable
		frame.setLocationRelativeTo(null); // set the location to be in the middle of the screen
		
	
		frame.add(panel);
		frame.pack(); // Adjust the JFrame to fit the content pane
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new SnakeGame();
		
	}
	
}
