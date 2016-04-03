package tilemap;

import java.awt.Graphics2D;
import java.util.List;

import logic.entity.Entity;

public class TilemapEventLayer {

	private int tileWidth, tileHeight;
	private List<Entity> events;

	public TilemapEventLayer(int tileWidth, int tileHeight, List<Entity> events) {
		this.events = events;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public void render(Graphics2D g) {
		for (Entity entity : events)
			entity.render(g);
	}

	private int eventXToTilePosX(Entity e) {
		return e.getX() / tileWidth;
	}

	private int eventYToTilePosY(Entity e) {
		return e.getY() / tileHeight;
	}

	public Entity getEventAt(int posX, int posY) {
		for (Entity entity : events)
			if (eventXToTilePosX(entity) == posX && eventYToTilePosY(entity) == posY)
				return entity;
		return null;
	}
}
