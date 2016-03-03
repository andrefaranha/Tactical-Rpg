package logic.game;

import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	public static int WIDTH, HEIGHT;

	private final int FPS = 40;

	public static final int ONE_SEC_IN_NANO = 1000000000;
	public static final int ONE_MILLI_IN_NANO = 1000000;

	private final long FIXED_RATE = ONE_SEC_IN_NANO / FPS;

	private GameManager gameManager;

	private Thread thread;

	public GamePanel(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify();

		gameManager = new GameManager();
		// addKeyListener(KeyHandler.getInstance());
		// addMouseListener(MouseHandler.getInstance());
		// addMouseMotionListener(MouseHandler.getInstance());

		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		gameManager.init();

		long sleepTime;
		long lastTime;

		long nextTime = System.nanoTime();
		long currentTime = nextTime;

		while (gameManager.isRunning()) {
			lastTime = currentTime;
			currentTime = System.nanoTime();

			if (currentTime >= nextTime) {
				nextTime += FIXED_RATE;

				gameManager.update(currentTime - lastTime);
				gameManager.draw();
				gameManager.render(getGraphics(), WIDTH, HEIGHT);

			} else {
				sleepTime = (long) (nextTime - currentTime) / ONE_MILLI_IN_NANO;
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		exit();
	}

	private void exit() {
		System.exit(0);
	}

}
