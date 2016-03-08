package logic.player;

import java.awt.Image;

public class Player {
	
	private Image image;
	private int position;
	
	public Player(Image image, int position) {
		setImage(image);
		setPosition(position);
	}
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
