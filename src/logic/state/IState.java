package logic.state;

import java.awt.Graphics2D;

public interface IState {
	public void update(float elapsedTime);
	public void render(Graphics2D g);
	public void onEnter();
	public void onExit();
}
