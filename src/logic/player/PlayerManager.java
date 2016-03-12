package logic.player;

import logic.entity.Entity;

public class PlayerManager {
	
	private static PlayerManager instance;
	
	private Entity player;
	
	private PlayerManager() {}
	
	public static PlayerManager getInstance() {
		if (instance == null)
			instance = new PlayerManager();
		return instance;
	}

	public Entity getPlayer() {
		return player;
	}

	public void setPlayer(Entity player) {
		this.player = player;
	}
}
