package logic.entity;

import java.awt.Image;

public class Entity {

	private Image image;
	private int position;
	private float x, y, xDest, yDest;

	private boolean moving;

	private int tilesPerSecond = 5;
	private float pixelsToMovePerFrame = tilesPerSecond * 32 / 40;

	public Entity(Image image, int position) {
		setImage(image);
		setPosition(position);
	}

	public void update(float elapsedTime) {
		if (moving) {
			moveToDestination(elapsedTime);
		}
	}

	private void moveToDestination(float elapsedTime) {
		if (yDest != y) {
			if (y < yDest) {
				y += pixelsToMovePerFrame;
				if (y > yDest) y = yDest;
			} else if (y > yDest)
				y -= pixelsToMovePerFrame;
		} else if (xDest != x) {
			if (x < xDest) {
				x += pixelsToMovePerFrame;
				if (x > xDest) x = xDest;
			}
			else if (x > xDest)
				x -= pixelsToMovePerFrame;
		} else
			moving = false;
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

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setXDestination(int xDest) {
		if (moving)
			return;
		moving = true;
		this.xDest = xDest;
	}

	public void setYDestination(int yDest) {
		if (moving)
			return;
		moving = true;
		this.yDest = yDest;
	}
}
