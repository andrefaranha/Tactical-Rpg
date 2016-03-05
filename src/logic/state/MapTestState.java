package logic.state;

import java.awt.Graphics2D;

import map.Map;
import map.TMXReader;

public class MapTestState implements IState {

	private Map map;
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		map.render(g);
	}

	@Override
	public void onEnter() {
		map = TMXReader.read("resources/example.tmx");
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}

}
