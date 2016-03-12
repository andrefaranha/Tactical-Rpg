package logic.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import logic.entity.Entity;
import logic.input.KeyHandler;
import logic.player.Player;
import logic.player.PlayerManager;
import logic.state.MapTestState;
import logic.state.StateMachineStack;

public class GameManager {

	private BufferedImage image;
	private Graphics2D g2d;

	private boolean running;

	private int frames, fps;
	private long lastTime, totalTime, beginTime;
	
	private StateMachineStack stateMachineStack;

	public GameManager() {
		beginTime = System.nanoTime();
	}

	public void init() {
		image = new BufferedImage(GamePanel.WIDTH, GamePanel.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) image.getGraphics();

		setRunning(true);

		totalTime = 0;
		frames = 0;
		fps = 0;
		lastTime = beginTime;

		PlayerManager.getInstance().setPlayer(new Entity(new ImageIcon("resources/player.png").getImage(), 0));
		
		stateMachineStack = new StateMachineStack();
		stateMachineStack.add("test", new MapTestState());
		stateMachineStack.push("test");
	}

	private void setRunning(boolean b) {
		running = b;
	}

	public boolean isRunning() {
		return running;
	}
	
	public void processInput(KeyHandler keyHandler) {
		stateMachineStack.processInput(keyHandler);
		keyHandler.update();
	}

	public void update(long delta) {
		calcCurrentFPS();

		stateMachineStack.update(delta);
	}

	public void draw() {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		stateMachineStack.render(g2d);

		drawFPS();
	}

	public void render(Graphics g, int width, int height) {
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
	}

	private void drawFPS() {
		g2d.setColor(Color.RED);
		g2d.drawString("FPS " + fps, 0, 10);
	}

	private void calcCurrentFPS() {
		totalTime += System.nanoTime() - lastTime;
		lastTime = System.nanoTime();

		frames++;
		if (totalTime >= GamePanel.ONE_SEC_IN_NANO) {
			totalTime -= GamePanel.ONE_SEC_IN_NANO;
			fps = frames;
			frames = 0;
		}
	}

}
