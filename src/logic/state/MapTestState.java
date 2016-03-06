package logic.state;

import java.awt.Graphics2D;

import map.TileMap;
import map.TMXReader;

public class MapTestState extends State {

	private TileMap map;
	
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
		map.onEnter();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}

}
