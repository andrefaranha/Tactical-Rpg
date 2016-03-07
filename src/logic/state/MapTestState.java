package logic.state;

import java.awt.Graphics2D;

import tilemap.Tilemap;
import tilemap.TilemapBuilder;

public class MapTestState extends State {

//	private OLDTileMap map;
	private Tilemap tilemap;
	
	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		tilemap.render(g);
	}

	@Override
	public void onEnter() {
		tilemap = TilemapBuilder.build("resources/test.map");
//		map.onEnter();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void rightButtonPressed() {
//		map.rightButtonPressed();
	}
}
