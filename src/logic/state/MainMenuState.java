package logic.state;

import java.awt.Color;
import java.awt.Graphics2D;

import logic.game.GamePanel;

public class MainMenuState implements IState {

	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}

}
