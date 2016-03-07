package tilemap;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class TilesetCollection {
	
	private int tileWidth, tileHeight;
	private List<Tileset> tileset;
	private Map<Integer, Image> gidToTileImageMap;
	
	public TilesetCollection(int tileWidth, int tileHeight, List<Tileset> tileset) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileset = tileset;
		
		gidToTileImageMap = new HashMap<Integer, Image>();
		mapGidToTileFromAllTileset();
	}
	
	private void mapGidToTileFromAllTileset() {
		for (Tileset t : tileset)
			mapGidToTileFromTileset(t);
	}
	
	private void mapGidToTileFromTileset(Tileset tileset) {
		BufferedImage bImage = null;
		try {
			bImage = ImageIO.read(new File("resources/" + tileset.getSource()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int firstGid = tileset.getFirstGid();
		int lastGid = tileset.getLastGid();
		int mapWidthInTiles = tileset.getTileAmountWidth();
		int tileWidth = tileset.getTileWidth();
		int tileHeight = tileset.getTileHeight();
		for (int gid = firstGid; gid <= lastGid; gid++) {
			int x = (gid - firstGid) % mapWidthInTiles * tileWidth;
			int y = (gid - firstGid) / mapWidthInTiles * tileHeight;
			Image tileImage = bImage.getSubimage(x, y, tileWidth, tileHeight);
			gidToTileImageMap.put(gid, tileImage);
		}
	}
	
	public Image getTile(int gid) {
		return gidToTileImageMap.get(gid);
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}
}