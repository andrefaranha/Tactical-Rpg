package logic.game;

import javax.swing.JFrame;

public class Game {

	public Game(String title, int width, int height) {
		JFrame frame = new JFrame(title);
		
		frame.add(new GamePanel(width, height));
		
		frame.setResizable(false);
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Game(String title) {
		this(title, 640, 640);
	}
}
