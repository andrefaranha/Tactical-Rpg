package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.state.State;

public class TileMap extends State {
	
	private int mapWidth, mapHeight, tileWidth, tileHeight;
	private List<Integer> data;
	private List<TileSet> tileSet;
	private Map<Integer, Image> tileSetImageMap;
	
	public TileMap(int mapWidth, int mapHeight, int tileWidth, int tileHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		tileSet = new ArrayList<TileSet>();
		data = new ArrayList<Integer>();
		tileSetImageMap = new HashMap<Integer, Image>();
	}

	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getMapWidthInPixels(), getMapHeightInPixels());
//		g.drawImage(TileSetLoader.load(tileSet.get(0)), 0, 0, null);
		
		TileSet t = tileSet.get(0);
		for (int i = 0; i < data.size(); i++) {
			g.drawImage(tileSetImageMap.get(data.get(i)), (i * t.getTileWidth()) % (mapWidth*tileWidth), ((i / mapWidth) * tileHeight) % (mapHeight*tileHeight), tileWidth, tileHeight, null);
		}
	}

	@Override
	public void onEnter() {
		tileSetImageMap = TileSetLoader.getTileSetImagesMap(tileSet);
//		System.out.println(tileSetImageMap.size());
//		System.out.println(mapWidth);
//		for (int i = 0; i < mapWidth; i++) {
//			System.out.println(data.get(i) + ", " + tileSetImageMap.containsKey(data.get(i)));
//		}
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	
	public void setData(List<Integer> data) {
		this.data = data;
	}
	
	public void setTileSet(List<TileSet> tileSet) {
		this.tileSet = tileSet;
	}
	
	private int getMapWidthInPixels() {
		return mapWidth * tileWidth;
	}
	
	private int getMapHeightInPixels() {
		return mapHeight * tileHeight;
	}
}
