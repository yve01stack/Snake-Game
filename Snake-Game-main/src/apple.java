import java.util.Arrays;
import java.util.Random;

public class apple{
		snake s;
		int board_size = 16; // Size of the board
		Random ran = new Random(); // Randomness for the spawn of the apple
		
		// apple takes random position in the grid (between 1 and board_size for length and width
		int[] pos = {ran.nextInt(board_size) + 1, ran.nextInt(board_size) + 1};
		
		
		public apple(snake s) {
			// If the position of apple is already filled by the snake :
			// Reroll location
			while((pos[0] == s.shead[0] && pos[1] == s.shead[1]) || (Arrays.stream(s.stail).anyMatch(x -> x[0] == pos[0] && x[1] == pos[1]))) {
				pos[0] = ran.nextInt(board_size) + 1;
				pos[1] = ran.nextInt(board_size) + 1;		
			}
		}
		
 	}
