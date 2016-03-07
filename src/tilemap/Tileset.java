package tilemap;

public class Tileset {
	
	private int firstGid, lastGid, tileWidth, tileHeight, imageWidth, imageHeight, tileAmountWidth;
	private String name, source;
	
	public Tileset(int firstGid, String name, int tileWidth, int tileHeight, String source, int imageWidth, int imageHeight) {
		this.firstGid = firstGid;
		this.name = name;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.source = source;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		tileAmountWidth = imageWidth / tileWidth;
		lastGid = tileAmountWidth * (imageHeight / tileHeight) + firstGid - 1;
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

	public int getTileAmountWidth() {
		return tileAmountWidth;
	}

	public String getName() {
		return name;
	}
}