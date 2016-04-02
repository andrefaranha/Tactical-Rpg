package tilemap;

import java.awt.Graphics2D;

import logic.entity.Entity;
import logic.entity.Sprite;
import logic.player.PlayerManager;

public class Tilemap {

	private int mapWidth, mapHeight;
	private TilemapLayer backgroundlayer, foregroundLayer, topLayer;
	private Entity player;

	public Tilemap(int mapWidth, int mapHeight, TilemapLayer Backgroundlayer, TilemapLayer foregroundLayer,
			TilemapLayer topLayer) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.backgroundlayer = Backgroundlayer;
		this.foregroundLayer = foregroundLayer;
		this.topLayer = topLayer;

		player = PlayerManager.getInstance().getPlayer();
	}

	public void update(float elapsedTime) {
		player.update(elapsedTime);
	}

	public void render(Graphics2D g) {
		backgroundlayer.render(g);
		foregroundLayer.render(g);

		player.render(g);
//		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
		// g.drawString("(" + player.getX() + "," + player.getY() + ")",
		// player.getX(), player.getY());

		topLayer.render(g);
	}

	private boolean canMoveToPlayerNewPosition(int x, int y) {
		int posXTile = x / 32;
		int posYTile = y / 32;
		if (posXTile < 0 || posXTile >= mapWidth)
			return false;
		if (posYTile < 0 || posYTile >= mapHeight)
			return false;
		if (foregroundLayer.getTileAt(posXTile, posYTile) != null)
			return false;

		return true;
	}

	public void upButtonDown() {
		int newYPosition = player.getY() - 32;
		player.setFacingDirection(Sprite.FACING_UP);
		if (canMoveToPlayerNewPosition(player.getX(), newYPosition))
			player.setYDestination(newYPosition);
	}

	public void leftButtonDown() {
		int newXPosition = player.getX() - 32;
		player.setFacingDirection(Sprite.FACING_LEFT);
		if (canMoveToPlayerNewPosition(newXPosition, player.getY()))
			player.setXDestination(newXPosition);
	}

	public void downButtonDown() {
		int newYPosition = player.getY() + 32;
		player.setFacingDirection(Sprite.FACING_DOWN);
		if (canMoveToPlayerNewPosition(player.getX(), newYPosition))
			player.setYDestination(newYPosition);
	}

	public void rightButtonDown() {
		int newXPosition = player.getX() + 32;
		player.setFacingDirection(Sprite.FACING_RIGHT);
		if (canMoveToPlayerNewPosition(newXPosition, player.getY()))
			player.setXDestination(newXPosition);
	}
}
