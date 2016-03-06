package logic.state;

import java.awt.Graphics2D;

import logic.input.KeyHandler;

public abstract class State {

	public final void processInput(KeyHandler keyHandler) {
		if (keyHandler.isPressed(KeyHandler.UP))
			upButtonPressed();
		else if (keyHandler.isPressed(KeyHandler.LEFT))
			leftButtonPressed();
		else if (keyHandler.isPressed(KeyHandler.DOWN))
			downButtonPressed();
		else if (keyHandler.isPressed(KeyHandler.RIGHT))
			rightButtonPressed();
		else if (keyHandler.isPressed(KeyHandler.ACTION))
			actionButtonPressed();
		else if (keyHandler.isPressed(KeyHandler.CANCEL))
			cancelButtonPressed();
		else if (keyHandler.isDown(KeyHandler.UP))
			upButtonDown();
		else if (keyHandler.isDown(KeyHandler.LEFT))
			leftButtonDown();
		else if (keyHandler.isDown(KeyHandler.DOWN))
			downButtonDown();
		else if (keyHandler.isDown(KeyHandler.RIGHT))
			rightButtonDown();
	}

	public void upButtonDown() {
//		System.out.println("upButtonDown");
	}

	public void leftButtonDown() {
//		System.out.println("leftButtonDown");
	}

	public void downButtonDown() {
//		System.out.println("downButtonDown");
	}

	public void rightButtonDown() {
//		System.out.println("rightButtonDown");
	}

	public void upButtonPressed() {
//		System.out.println("upButtonPressed");
	}

	public void leftButtonPressed() {
//		System.out.println("leftButtonPressed");
	}

	public void downButtonPressed() {
//		System.out.println("downButtonPressed");
	}

	public void rightButtonPressed() {
//		System.out.println("rightButtonPressed");
	}

	public void actionButtonPressed() {
//		System.out.println("actionButtonPressed");
	}

	public void cancelButtonPressed() {
//		System.out.println("cancelButtonPressed");
	}

	public abstract void update(float elapsedTime);

	public abstract void render(Graphics2D g);

	public abstract void onEnter();

	public abstract void onExit();
}
