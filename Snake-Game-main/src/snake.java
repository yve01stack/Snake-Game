import java.util.Arrays;


//Class which depicts movements and winning/losing conditions of the snake 
public class snake{ 
		int board_size = 16; // Size of the board
		// Position of the head of the snake; starts in the center
		int[] shead = {(int) board_size / 2, (int) board_size / 2};
		// Position of the tail of the snake; multiple dots
		// Each cell of the Array represents position of one dot of the cell
		int[][] stail = new int[(board_size * board_size) - 1][2];
		int alive = 1; // Alive = 1; Dead = 0; Win = 2
		int snakeLength = 1; // Length of the snake
		
		public snake() {
			// Initialization of snake: no tail
			// Position {0;0} means that the dot doesn't exist 
			// Grid position starts from 1 to Board_size (length and width)
			for(int i = 0; i < board_size * board_size - 1; i++){
			for(int j = 0; j < 2; j++)
					stail[i][j] = 0;
				}
		}
		
        // Check if next move of the snake will result in Game Over
		// Collision of the head with a border or the tail
		public void checkDeadlyMove(int[] direction) {
		// direction are the directions which the snake is heading
			// newPos : coordinates of the head of the snake after he moves
			int[] newPos = {shead[0] + direction[0], shead[1] + direction[1]};
			
			// If the length of the snake is inferior than two: head can't collide with tail
			if(snakeLength > 2) {
				// Checking collisions with borders and tail (excepted end of tail which will be moving anyway)
				if(newPos[0] == 0 || newPos[1] == 0 || newPos[0] == board_size + 1 || newPos[1] == board_size + 1 || (Arrays.stream( Arrays.copyOfRange(stail,0,snakeLength - 2)).anyMatch(x -> x[0] == newPos[0] && x[1] == newPos[1]))) {
					alive = 0; // Dead
				}	
			}
			
			else {
				//Checking collisions with borders
				if(newPos[0] == 0 || newPos[1] == 0 || newPos[0] == board_size + 1 || newPos[1] == board_size + 1) {
					alive = 0;
				}
			}
		}
		
		// Function to make the snake move if no collision
		public void move(int[] direction, boolean appleMatch) {
			int[] newPos = {shead[0] + direction[0], shead[1] + direction[1]};
			
			// appleMatch : snake is moving towards an apple
			if(appleMatch) {
				// Snake eats apple; Snake grows in size
				snakeLength += 1; 
				
				// If the snake fills the whole board
				if (snakeLength == board_size * board_size) {
					alive = 2; // Win
				}

				// For all the dots that aren't behind the snake :
				// Dot takes the position of the dot ahead
				for(int i = snakeLength - 2; i > 0; i--){
						stail[i][0] = stail[i-1][0];
						stail[i][1] = stail[i-1][1];
					}
					
					// Dot behind the head takes the position of the head
					stail[0][0] = shead[0];
					stail[0][1] = shead[1];
					
					// Head takes the new position
					shead[0] = newPos[0];
					shead[1] = newPos[1];
				
				
			}
			
			else {//Same same
				
				for(int i = snakeLength - 2; i > 0; i--){
						stail[i][0] = stail[i-1][0];
						stail[i][1] = stail[i-1][1];
				}
				
				// We have to check if the snake has a tail before giving it a new position
				// If snake has already eat an apple, he has a tail
				if (snakeLength > 1) {
					   stail[0][0] = shead[0];
					   stail[0][1] = shead[1];
				}
					
				shead[0] = newPos[0];
				shead[1] = newPos[1];
				}
			}

		}
		
	
	
	
