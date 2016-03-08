package tilemap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TilesetCollection {
	
	private int tileWidth, tileHeight;
	private List<Tileset> tileset;
	private Map<Integer, Tile> gidToTileImageMap;
	
	public TilesetCollection(int tileWidth, int tileHeight, List<Tileset> tileset) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileset = tileset;
		
		gidToTileImageMap = new HashMap<Integer, Tile>();
		populateGidToTileImageMap();
	}
	
	private void populateGidToTileImageMap() {
		for (Tileset t : tileset)
			gidToTileImageMap.putAll(t.getGidToTileImageMap());
	}
	
	public Tile getTile(int gid) {
		return gidToTileImageMap.get(gid);
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}
}