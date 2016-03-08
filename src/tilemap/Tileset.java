package tilemap;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Tileset {
	
	private int firstGid, lastGid, tileWidth, tileHeight, imageWidth, imageHeight, mapWidthInTiles;
	private String name, source;
	private Map<Integer, Tile> gidToTileImageMap;
	
	public Tileset(int firstGid, String name, int tileWidth, int tileHeight, String source, int imageWidth, int imageHeight) {
		this.firstGid = firstGid;
		this.name = name;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.source = source;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		mapWidthInTiles = imageWidth / tileWidth;
		lastGid = mapWidthInTiles * (imageHeight / tileHeight) + firstGid - 1;
		
		gidToTileImageMap = new HashMap<Integer, Tile>();
		mapGidToTileFromTileset();
	}
	
	private void mapGidToTileFromTileset() {
		BufferedImage bImage = null;
		try {
			bImage = ImageIO.read(new File("resources/" + getSource()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int gid = firstGid; gid <= lastGid; gid++) {
			int x = (gid - firstGid) % mapWidthInTiles * tileWidth;
			int y = (gid - firstGid) / mapWidthInTiles * tileHeight;
			Image tileImage = bImage.getSubimage(x, y, tileWidth, tileHeight);
			gidToTileImageMap.put(gid, new Tile(tileImage, Tile.TYPE_UNBLOCKED));
		}
	}
	
	public String getSource() {
		return source;
	}

	public int getFirstGid() {
		return firstGid;
	}

	public int getLastGid() {
		return lastGid;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getMapWidthInTiles() {
		return mapWidthInTiles;
	}

	public String getName() {
		return name;
	}
	
	public Map<Integer, Tile> getGidToTileImageMap() {
		return gidToTileImageMap;
	}
}
