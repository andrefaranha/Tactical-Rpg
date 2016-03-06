package map;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TileSetLoader {
	
	public static Image load(TileSet tileset) {
		return new ImageIcon("resources/" + tileset.getSource()).getImage();
	}
	
	public static Map<Integer, Image> getTileSetImagesMap(List<TileSet> tileset) {
		Map<Integer, Image> tileSetImageMap = new HashMap<Integer, Image>();
		
		for (int i = 0; i < tileset.size(); i++)
			tileSetImageMap.putAll(TileSetLoader.getTileSetImagesMapFromOneTileSet(tileset.get(i)));
		
		return tileSetImageMap;
	}
	
	public static Map<Integer, Image> getTileSetImagesMapFromOneTileSet(TileSet tileset) {
		Map<Integer, Image> tileSetImageMap = new HashMap<Integer, Image>();
		BufferedImage bImage = null;
		try {
			bImage = ImageIO.read(new File("resources/" + tileset.getSource()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = tileset.getFirstGid(); i <= tileset.getLastGid(); i++) {
			int x, y;
			x = (i - tileset.getFirstGid()) % tileset.getTileAmountWidth() * tileset.getTileWidth();
			y = (i - tileset.getFirstGid()) / tileset.getTileAmountWidth() * tileset.getTileHeight();
			Image tileImage = bImage.getSubimage(x,
												 y,
												 tileset.getTileWidth(), tileset.getTileHeight());
//			System.out.println(x + "," + y);
			tileSetImageMap.put(i, tileImage);
		}
		
		return tileSetImageMap;
	}
}
