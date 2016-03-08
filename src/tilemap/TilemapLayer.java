package tilemap;

import java.awt.Graphics2D;
import java.util.List;

public class TilemapLayer {
	
	private int mapWidth, mapHeight, tileWidth, tileHeight;
	private List<Integer> layerData;
	private TilesetCollection tilesetCollection;
	
	public TilemapLayer(int mapWidth, int mapHeight, List<Integer> layerData, TilesetCollection tilesetCollection) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.layerData = layerData;
		this.tilesetCollection = tilesetCollection;
		tileWidth = tilesetCollection.getTileWidth();
		tileHeight = tilesetCollection.getTileHeight();
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < layerData.size(); i++) {
			int gid = layerData.get(i);
			int x = (i * tileWidth) % (mapWidth*tileWidth);
			int y = ((i / mapWidth) * tileHeight) % (mapHeight*tileHeight);
			Tile t = tilesetCollection.getTile(gid);
			if (t != null)
				g.drawImage(t.getImage(), x, y, null);
		}
	}
	
	public Tile getTileAt(int mapLocation) {
		return tilesetCollection.getTile(layerData.get(mapLocation));
	}
}