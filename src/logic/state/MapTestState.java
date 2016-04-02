package logic.state;

import java.awt.Graphics2D;

import logic.player.PlayerManager;
import tilemap.Tilemap;
import tilemap.TilemapBuilder;

public class MapTestState extends State {

	private Tilemap tilemap;

	@Override
	public void update(float elapsedTime) {
		tilemap.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {
		tilemap.render(g);
	}

	@Override
	public void onEnter() {
		tilemap = TilemapBuilder.build("resources/test.map");
		PlayerManager.getInstance().getPlayer().setXWithoutMoving(TilemapBuilder.getPlayerXPosition("resources/test.map"));
		PlayerManager.getInstance().getPlayer().setYWithoutMoving(TilemapBuilder.getPlayerYPosition("resources/test.map"));
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void upButtonDown() {
		tilemap.upButtonDown();
	}

	@Override
	public void leftButtonDown() {
		tilemap.leftButtonDown();
	}

	@Override
	public void downButtonDown() {
		tilemap.downButtonDown();
	}

	@Override
	public void rightButtonDown() {
		tilemap.rightButtonDown();
	}
}
