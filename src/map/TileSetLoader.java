package map;

import java.awt.Image;

import javax.swing.ImageIcon;

public class TileSetLoader {
	
	public static Image load(TileSet tileset) {
		return new ImageIcon("resources/" + tileset.getSource()).getImage();
	}
}
