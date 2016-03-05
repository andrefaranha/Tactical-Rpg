package map;

public class TileSet {
	
	private int firstGid, lastGrid, tileWidth, tileHeight, imageWidth, imageHeight, tileAmountWidth;
	private String name, source;
	
	public TileSet(int firstGid, String name, int tileWidth, int tileHeight, String source, int imageWidth, int imageHeight) {
		this.firstGid = firstGid;
		this.name = name;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.source = source;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}
	
	public String getSource() {
		return source;
	}
}
