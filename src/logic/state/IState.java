package logic.state;

public interface IState {
	public void update(float elapsedTime);
	public void render();
	public void onEnter();
	public void onExit();
}
