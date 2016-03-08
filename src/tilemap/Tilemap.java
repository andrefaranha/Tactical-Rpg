package tilemap;

import java.awt.Graphics2D;

import logic.player.Player;
import logic.player.PlayerManager;

public class Tilemap {

	private int mapWidth, mapHeight;
	private TilemapLayer backgroundlayer, foregroundLayer, topLayer;
	private Player player;

	public Tilemap(int mapWidth, int mapHeight, TilemapLayer Backgroundlayer, TilemapLayer foregroundLayer,
			TilemapLayer topLayer, int playerPosition) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.backgroundlayer = Backgroundlayer;
		this.foregroundLayer = foregroundLayer;
		this.topLayer = topLayer;

		player = PlayerManager.getInstance().getPlayer();
	}

	public void render(Graphics2D g) {
		backgroundlayer.render(g);
		foregroundLayer.render(g);

		int position = player.getPosition();
		g.drawImage(player.getImage(), (position % mapWidth) * 32, (position / mapWidth) * 32, null);

		topLayer.render(g);
	}

	private boolean canMoveToPlayerNewPosition(int newPosition) {
		if (newPosition < 0 || newPosition >= mapWidth * mapHeight || foregroundLayer.getTileAt(newPosition) != null)
			return false;

		return true;
	}

	public void upButtonDown() {
		if (canMoveToPlayerNewPosition(player.getPosition() - mapWidth))
			player.setPosition(player.getPosition() - mapWidth);
		
	}

	public void leftButtonDown() {
		if (canMoveToPlayerNewPosition(player.getPosition() - 1))
			if (player.getPosition() / mapWidth == (player.getPosition()-1) / mapWidth)
				player.setPosition(player.getPosition() - 1);
	}

	public void downButtonDown() {
		if (canMoveToPlayerNewPosition(player.getPosition() + mapWidth))
			player.setPosition(player.getPosition() + mapWidth);
	}

	public void rightButtonDown() {
		if (canMoveToPlayerNewPosition(player.getPosition() + 1))
			if (player.getPosition() / mapWidth == (player.getPosition()+1) / mapWidth)
				player.setPosition(player.getPosition() + 1);
	}
}
