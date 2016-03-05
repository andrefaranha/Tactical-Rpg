package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import logic.state.IState;

public class Map implements IState {
	
	private int mapWidth, mapHeight, tileWidth, tileHeight;
	private List<TileSet> tileSet;
	
	public Map(int mapWidth, int mapHeight, int tileWidth, int tileHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		tileSet = new ArrayList<TileSet>();
	}

	@Override
	public void update(float elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getMapWidthInPixels(), getMapHeightInPixels());
		g.drawImage(TileSetLoader.load(tileSet.get(0)), 0, 0, null);
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
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
