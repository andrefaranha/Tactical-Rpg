package tilemap;

import java.awt.Image;

public class Tile {

	public static int TYPE_UNBLOCKED = 0, TYPE_BLOCKED = 1;

	private Image image;
	private int type;

	public Tile(Image image, int type) {
		this.image = image;
		this.type = type;
	}

	public Image getImage() {
		return image;
	}

	public int getType() {
		return type;
	}
}
