package tilemap;

import java.awt.Graphics2D;

public class Tilemap {

	private TilemapLayer backgroundlayer, foregroundLayer, topLayer;

	// private Image player;
	// private int playerPosition;

	public Tilemap(TilemapLayer Backgroundlayer, TilemapLayer foregroundLayer, TilemapLayer topLayer) {
		this.backgroundlayer = Backgroundlayer;
		this.foregroundLayer = foregroundLayer;
		this.topLayer = topLayer;
	}

	public void render(Graphics2D g) {
		backgroundlayer.render(g);
		foregroundLayer.render(g);
		topLayer.render(g);
	}
}
