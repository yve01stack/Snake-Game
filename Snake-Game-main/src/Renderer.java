import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Menu screen
public class Renderer extends Frame {
	
	// Get dimension of the screen of the computer to create a Frame with right width and length
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
    private int width = (int)size.getWidth(); 
    private int height = (int)size.getHeight(); 
    
    private int window_w = (width*75)/100;
    private int window_h = (height*75)/100;
    
    // Proportioned buttons and labels sizes / positions
    private int buttonSize_w = window_w / 8;
    private int buttonSize_h = window_h / 8;
    private int buttonPosition_w = (window_w/2)-(buttonSize_w/2);
    private int buttonPosition_h = window_h/2;

    private int gamenameSize_w = window_w * 10 / 24;
    private int gamenameSize_h = window_h / 8;
    private int gamenamePosition_w = (window_w/2)-(gamenameSize_w/2);
    private int gamenamePosition_h = (window_h/3)-gamenameSize_h;
  
    // Create objects
	Frame window = new Frame();
	Button button_1 = new Button("Start Game");
	Button button_2 = new Button("Exit");
	JLabel gameName = new JLabel("Snake Game");
	
	
	public Renderer(){
		// Add objects to the frame and make them visible
		button_1.setBounds(buttonPosition_w, buttonPosition_h, buttonSize_w, buttonSize_h);
		button_2.setBounds(buttonPosition_w, buttonPosition_h+buttonSize_h+25, buttonSize_w, buttonSize_h);
		gameName.setBounds(gamenamePosition_w, gamenamePosition_h, gamenameSize_w, gamenameSize_h);
		gameName.setFont(new Font(gameName.getName(), 65, gamenameSize_h));
		
		window.add(button_1);
		window.add(button_2);
		window.add(gameName);
		
		// Add ActionListener to buttons
		button_1.addActionListener(new jouer());
		button_2.addActionListener(new Sortir());
		
		window.setSize(window_w, window_h);
		window.setTitle("Snake Game");

		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setLayout(null);
		window.setVisible(true);
	}
	
	public class jouer implements ActionListener{
		// If button1 pressed ("Start Game"), start the class which starts the game (new Frame / Panel)
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			RendererGame Game = new RendererGame();
		}
	}

	class Sortir implements ActionListener{
			// If button2 is pressed, exit process
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
}
