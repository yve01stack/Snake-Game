import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Class that starts / process the game with keyboard inputs and action listener on a Panel
public class RendererGame extends JPanel implements ActionListener,KeyListener{
		
		int board_size = 16; // Size of the board	
	    snake s = new snake(); 
		apple a = new apple(s);
		boolean appleMatch = false;
		boolean pause = false; // Pause the game if pause == true
		
		
		int[] dir = {1,0}; // Direction of the head of the snake
        float counter = 0; // timer number of sequence -> +1 = 0.25 s
        int appleEaten = 0; // Number of apple that were eaten   

		JFrame f = new JFrame();
		
        JLabel stats = new JLabel("Stats:");
		JLabel chrono = new JLabel("Chrono: " + counter/4);
		JLabel aEaten = new JLabel("Fruits eaten: " + appleEaten);
		JLabel control = new JLabel("Controls:");
		JLabel instruction1 = new JLabel("Move Up: click on Up Arrow Key | Move Down: click on Down Arrow Key");
		JLabel instruction2 = new JLabel("Move Left: click on Left Arrow Key | Move Right: click on Right Arrow Key");
		JLabel instruction3 = new JLabel("Pause : press 'P'");
		
		// Game Over button in the center of the Frame
 		JButton GO = new JButton("Game Over");
		// Pause button in the center of the Frame
 		JButton GOPause = new JButton("Pause");
 	    // Win button in the center of the Frame
 		JButton GOWin = new JButton("It's won !");
 	  
	  	// pressed is the key code of the last pressed arrow key which is a legal move
	  	private int pressed;
		
	  	
		// "Main" method : Initiate Frame and Panel
		// Initialization of Panel, apple, snake...
	    public RendererGame() { 
			// Get dimension of the screen of the computer to create a Frame with right width and length
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
			
			// this -> JPanel
	        this.addKeyListener(this); // KeyListener included in class
	        this.setFocusable(true);
	        
			int width = (int)size.getWidth(); 
			int height = (int)size.getHeight(); 
			int window_w = (width*95)/100;
			int window_h = (height*95)/100;


			// Make the exit button work
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.add(this);
			f.pack();
			f.setSize(window_w, window_h);
			f.setTitle("Snake Game");
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			f.setVisible(true);
			
			//Initialization of snake and apple
            this.repaint();
			appleMatch = false;
			
			// Initialization of a Timer : each 500 ms, the function actionPerformed will play
		    // Action performed will move the snake accordingly to KeyListener and update the graphics of the panel
		    Timer timer = new Timer(250, this);
		 	timer.start();

			}
	    
		
		@Override // Create the graphics of the board, snake, apple...
		public void paintComponent(Graphics g) {	
			chrono.setText("Chrono: " + counter/4);
			aEaten.setText("Fruits eaten: " + appleEaten);
			
			//Set size and bounds of Buttons/Labels 
			GOPause.setSize(400, 100);
			GOPause.setFont(new Font("Arial", Font.PLAIN, 50));
	 		GOPause.setLocation(220,300);
     		GO.setSize(400, 100);
     		GO.setFont(new Font("Arial", Font.PLAIN, 50));
     		GO.setLocation(220,300);
     		GOWin.setSize(400, 100);
     		GOWin.setFont(new Font("Arial", Font.PLAIN, 50));
     		GOWin.setLocation(220,300);
     		
	    	stats.setBounds(1000, 10, 300, 100);
	    	chrono.setBounds(800, 50, 300, 100);
	    	aEaten.setBounds(800, 70, 300, 100);
	    	control.setBounds(990, 110, 300, 100);
	    	instruction1.setBounds(800, 150, 600, 100);
	    	instruction2.setBounds(800, 170, 600, 100);
	    	instruction3.setBounds(800,190,600,100);
	    	

            //add Labels
			this.add(stats);
		    this.add(chrono);
		    this.add(aEaten);
		    this.add(control);
		    this.add(instruction1);
		    this.add(instruction2);
		    this.add(instruction3);
			
			// White board (rectangle)
			super.paintComponent(g);
			g.setColor(Color.WHITE);
			g.fillRect(100, 50, 40*board_size, 40*board_size);
			
			// Black grid (Lines) 
			g.setColor(Color.BLACK);
			for (int i=0; i< board_size+1; i++) {
				g.drawLine(100, i*40+50, 40*board_size+100, i*40+50); 
				g.drawLine(i*40+100, 50, i*40+100, 40*board_size+50);
			}
	 
			// Green snake
			g.setColor(Color.GREEN);
			// Head of the snake (rectangle + triangle) with its position in a cell depending on its leaning (direction)
			if(dir[0] == -1) {
			g.fillRect(120 + 40 * (s.shead[0]-1), 60 + 40 * (s.shead[1]-1), 20, 20);		 
			g.fillPolygon(new int[] {120 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1), 100 + 40 * (s.shead[0]-1)}, new int[] {50 + 40 * (s.shead[1]-1),90 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1)}, 3);
			}
			
			else if(dir[0] == 1) {
				g.fillRect(100 + 40 * (s.shead[0]-1), 60 + 40 * (s.shead[1]-1), 20, 20);		 
				g.fillPolygon(new int[] {120 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1), 140 + 40 * (s.shead[0]-1)}, new int[] {50 + 40 * (s.shead[1]-1),90 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1)}, 3);
			}
			
			else if(dir[1] == 1) {
				g.fillRect(110 + 40 * (s.shead[0]-1), 50 + 40 * (s.shead[1]-1), 20, 20);		 
				g.fillPolygon(new int[] {100 + 40 * (s.shead[0]-1), 140 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1)}, new int[] {70 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1),90 + 40 * (s.shead[1]-1)}, 3);
			}
			
			else {
				g.fillRect(110 + 40 * (s.shead[0]-1), 70 + 40 * (s.shead[1]-1), 20, 20);		 
				g.fillPolygon(new int[] {100 + 40 * (s.shead[0]-1), 140 + 40 * (s.shead[0]-1), 120 + 40 * (s.shead[0]-1)}, new int[] {70 + 40 * (s.shead[1]-1),70 + 40 * (s.shead[1]-1),50 + 40 * (s.shead[1]-1)}, 3);
			}
			
			// Tail of the snake (multiple circles)
			for(int i = 0; i < s.snakeLength - 1; i++) {
				g.fillOval(100 + 40*(s.stail[i][0]-1), 50 + (s.stail[i][1]-1)*40, 40, 40);	
			}
			
			// Red Apple (circle)
			g.setColor(Color.RED);
			g.fillOval(100 + 40*(a.pos[0]-1), 50 + (a.pos[1]-1)*40, 40, 40);
			
			if (pause && s.alive == 1) {
				// Pause button in the center of the Frame
				this.add(GOPause);
			}
			
			else {
				this.remove(GOPause);
			}
			
			if (s.alive == 0) {
				// Game Over button in the center of the Frame
				this.add(GO);
			}
			
			else {
				this.remove(GO);
			}
			
			if (s.alive == 2) {
				// Win button in the center of the Frame
				this.add(GOWin);
			}
			
			else {
				this.remove(GOWin);
			}
		 }    

	  		
	    @Override // Move the snake accordingly to actionListener
        public void actionPerformed(ActionEvent e) {
	        	
	        	if (pressed == KeyEvent.VK_P) {
	        		pause = !pause; //pause or unpause
	        		this.repaint();
	        		pressed = 0; // (int) pressed is reset to 0 for no unpausing (Last pressed key is VK_P...)
	        	}
	        	
    	    
	    	    // If the snake is not alive, he doesn't move; same for pause
             	if(s.alive == 1 && !pause) {
             		this.repaint();	
             		counter = counter+1; //Time goes by... so slowly;
             		
             		// Change the direction according to last arrow key pressed (doesn't change anything is last pressed direction is the opposite of current dirction)
             		if(pressed == KeyEvent.VK_UP && dir[1] == 0) {
             			dir[0] = 0;
             			dir[1] = -1;
             		}
             
             		else if(pressed == KeyEvent.VK_DOWN && dir[1] == 0) {
             			dir[0] = 0;
             			dir[1] = 1;
             		}
             
             		else if(pressed == KeyEvent.VK_LEFT && dir[0] == 0) {
             			dir[0] = -1;
             			dir[1] = 0;
             		}
             
             		else if(pressed == KeyEvent.VK_RIGHT && dir[0] == 0) {
             			dir[0] = 1;
             			dir[1] = 0;
             		}
             		
             		// Check if according to the new direction, the snake will be dead
             		s.checkDeadlyMove(dir);	
                     	
                 	if(s.alive != 0) {
                 		// If the head of the snake is going to an apple...
             			if (s.shead[0] + dir[0] == a.pos[0] && s.shead[1] + dir[1] == a.pos[1]) {
             				// Snake will eat apple (grows in size)
             				appleMatch = true;
             			}
    	    	        
             			// Make the snake move towards dir
             			s.move(dir, appleMatch);
             			
             			if (appleMatch && s.alive != 2) {
             				// If snake ate apple and do not fill all cells, create new apple
             				a = new apple(s);
             				appleEaten = s.snakeLength - 1;

             			}
             			
             			//Reset apple position
             			appleMatch = false;
             			
             			// Create new graphics of the new situation (new Frame of the game, snake moved)
             			this.repaint();
             		} 
                 	
             	}           	
	        
	        }
	    

	    
	    // Key listener functions : only use the case when a key is pressed
		public void keyTyped(KeyEvent e) {
	        return;
	    }

		// When a Key is pressed, give pressed the key code of it
	    public void keyPressed(KeyEvent e) {
	        pressed = e.getKeyCode();
	    }

	    public void keyReleased(KeyEvent e) {
	        return;
	    }
		 

		
	
}
