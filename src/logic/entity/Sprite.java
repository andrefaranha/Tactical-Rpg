package logic.entity;

import java.awt.Graphics2D;
import java.awt.Image;

public class Sprite {
	
	public static int FACING_DOWN = 0;
	public static int FACING_LEFT = 1;
	public static int FACING_RIGHT = 2;
	public static int FACING_UP = 3;
	
	public static int WALKING_RIGHT_FOOT_FRONT = 0;
	public static int WALKING_STANDING = 1;
	public static int WALKING_LEFT_FOOT_FRONT = 2;
	
	private Image[][] image;
	private int facingPosition;
	private int walkingPosition;
	
	public Sprite(Image[][] image) {
		this.image = image;
		setWalkingPosition(1);
	}
	
	public void update(float elapsedTime) {
	}
	
	public void render(Graphics2D g) {
	}
	
	public Image getImage() {
		return image[facingPosition][walkingPosition];
	}
	
	public void setFacingPosition(int facingPosition) {
		this.facingPosition = facingPosition;
	}
	
	public void setWalkingPosition(int walkingPosition) {
		this.walkingPosition = walkingPosition;
	}
}
