import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;


import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;  // size of each cell
	static final int rowsAndColumns = SCREEN_WIDTH/UNIT_SIZE; 

	ArrayList<Point> snake = new ArrayList<>();

	
	int appleX;
	int appleY;
	Random randomAppleLocation;
	boolean ateFood = false;
	int score;
	char direction = 'R';
	Timer timer;
	
	
	boolean isGameOver = false;
	
	
	GamePanel() {
		snake.add(0,new Point(300,300));
		timer = new Timer(150,this);
		timer.start();
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		
		//add a key listener for keyboard inputs for moving the snake
		
			this.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		    	// Prevents key presses during game over, except for pressing 'R' to restart
		    	if(isGameOver && e.getKeyCode()!=KeyEvent.VK_R) {
		    		return;
		    	}
		    	 switch (e.getKeyCode()) {
                 case KeyEvent.VK_UP:
                	 if(direction!='D') direction = 'U';
                     break;
                 case KeyEvent.VK_DOWN:
                	 if(direction!='U') direction = 'D';
                     break;
                 case KeyEvent.VK_LEFT:
                	 if(direction!='R') direction = 'L';
                     break;
                 case KeyEvent.VK_RIGHT:
                	 if(direction!='L') direction = 'R';
                     break;
                 case KeyEvent.VK_R:
                	 resetGame();
             }
		  
		    }
		});
		this.setFocusable(true); // Make sure the panel can receive key events
		this.requestFocusInWindow(); // Ensure it has focus when the game starts
		
		gameStart();
	}
	
	public void gameStart() {
		generateApple();
	}
	
	public void checkCollisions() {
		Point head = snake.get(0);
		if(head.x == appleX && head.y == appleY) {
			ateFood = true;
			updateScore();
			generateApple();
			repaint();
		}
		
		//Check to see if the snake is out of bounds	
		if (head.x < 0 || head.y < 0 || head.x >= SCREEN_WIDTH || head.y >= SCREEN_HEIGHT) {
		    gameOver();
		}

		
		//Check to see if the snake hit itself starting from index 1
		for(int i = 1; i<snake.size();i++) {
			if(head.x == snake.get(i).x && head.y == snake.get(i).y) {
				gameOver();
			}
		}
		
		
	}
	
	public void updateScore() {
		score++;
	}
	
	public void resetGame() {
		// Resets the game state: timer, score, snake position, and direction
		isGameOver = false;
		timer.restart();
   	 	score = 0;
   	 	snake.clear();
   	 	snake.add(0,new Point(300,300));
   	 	direction = 'R';
   	 	ateFood = false;
   	 	gameStart();
   	 	repaint();
   	 
   	   
	}
	
	
	public void gameOver() {
		isGameOver = true;
		timer.stop();
		repaint();
		
	}
	
	
	public void generateApple() { // place the apple randomly on the grid
		// If the snake fills the entire board, display the game over screen and exit the method to prevent further apples from generating
		if(snake.size() == rowsAndColumns * rowsAndColumns) {
			gameOver();
			return;
		}
		randomAppleLocation = new Random();
		appleX = randomAppleLocation.nextInt((int)rowsAndColumns) * UNIT_SIZE;
		appleY = randomAppleLocation.nextInt((int)rowsAndColumns) * UNIT_SIZE;
		
		//Ensures that the apple does not spawn on top of the snake or its body parts
		for(int i = 0; i<snake.size();i++) {
			if(snake.get(i).x == appleX && snake.get(i).y == appleY) {
				generateApple();
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		
		g.setColor(Color.RED);
		// Aligns the apple's position to the grid
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); 
		
		
		g.setColor(Color.GREEN);
		
		for(int i =0; i<snake.size();i++) {
			g.fillRect(snake.get(i).x, snake.get(i).y, UNIT_SIZE, UNIT_SIZE);
		}
		
		g.setColor(Color.yellow);
		g.setFont(new Font("Monospaced",Font.BOLD,30));
		g.drawString("Score:" + score, 250, 20);
		
		if (isGameOver) {
			//if the person has a full board filled with the snake's body, then they win
			if(snake.size() == rowsAndColumns * rowsAndColumns) {
				g.setColor(Color.PINK);
				g.setFont(new Font("Monospaced",Font.BOLD,30));
				g.drawString("You Win!", 225, 295);
				
				g.setColor(Color.CYAN);
				g.setFont(new Font("Monospaced",Font.BOLD,30));
				g.drawString("Press R To Restart", 150, 325);
				
			}
			else {
				g.setColor(Color.RED);
				g.setFont(new Font("Monospaced",Font.BOLD,30));
				g.drawString("Game Over", 225, 295);
				
				
				g.setColor(Color.CYAN);
				g.setFont(new Font("Monospaced",Font.BOLD,30));
				g.drawString("Press R To Restart", 150, 325);
				
			}
		}
					

	}
	

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Handles game updates for movement and collisions at each timer tick
		Point head = snake.get(0);
		
		 switch (direction) {
         case 'U':
        	 snake.add(0,new Point(head.x, head.y-UNIT_SIZE));
             break;
         case 'D':
        	 snake.add(0,new Point(head.x, head.y+UNIT_SIZE));
             break;
         case 'L':
        	 snake.add(0,new Point(head.x-UNIT_SIZE, head.y));
             break;
         case 'R':
        	 snake.add(0,new Point(head.x+UNIT_SIZE, head.y));  
             break;
     }
	checkCollisions(); 
		
    //If the snake ate the food, don't decrement the tail and set ateFood back to false to ensure that it grows
     if(ateFood == true) {
    	 ateFood = false;
     }
     else {
    	 // remove the last segment of the snake to simulate movement if its not eating the apple
    	 snake.remove(snake.size()-1);
     }
     repaint(); 
		
	}
	

}
