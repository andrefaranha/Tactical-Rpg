package logic.entity;

import java.awt.Graphics2D;

public class Entity {

	private Sprite sprite;
	private float x, y, xDest, yDest;

	private boolean moving;

	private int tilesPerSecond = 3;
	private float pixelsToMovePerFrame = tilesPerSecond * 32 / 40;

	private int walked;

	public Entity(String imageFilepath, int x, int y) {
		sprite = SpriteBuilder.build(imageFilepath, 32, 32);
		setXWithoutMoving(x);
		setYWithoutMoving(y);
	}

	public Entity(String imageFilepath) {
		this(imageFilepath, 0, 0);
	}

	public void update(float elapsedTime) {
		if (moving) {
			moveToDestination(elapsedTime);
		}
	}

	public void render(Graphics2D g) {
		g.drawImage(sprite.getImage(), getX(), getY(), null);
	}

	private void moveToDestination(float elapsedTime) {
		if (yDest != y) {
			walked += pixelsToMovePerFrame;
			if (y < yDest) {
				y += pixelsToMovePerFrame;
				if (y > yDest)
					y = yDest;
			} else if (y > yDest)
				y -= pixelsToMovePerFrame;
		} else if (xDest != x) {
			walked += pixelsToMovePerFrame;
			if (x < xDest) {
				x += pixelsToMovePerFrame;
				if (x > xDest)
					x = xDest;
			} else if (x > xDest)
				x -= pixelsToMovePerFrame;
		} else {
			walked = 0;
			moving = false;
			sprite.setWalkingPosition(Sprite.WALKING_STANDING);
		}
		if (walked != 0) {
			if (walked < 8)
				sprite.setWalkingPosition(Sprite.WALKING_RIGHT_FOOT_FRONT);
			else if (walked < 16)
				sprite.setWalkingPosition(Sprite.WALKING_STANDING);
			else if (walked < 24)
				sprite.setWalkingPosition(Sprite.WALKING_LEFT_FOOT_FRONT);
			else
				sprite.setWalkingPosition(Sprite.WALKING_STANDING);
		}
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

	public void setXWithoutMoving(int x) {
		setX(x);
		this.xDest = x;
	}

	public void setYWithoutMoving(int y) {
		setY(y);
		this.yDest = y;
	}

	public void setFacingDirection(int direction) {
		if (moving)
			return;
		sprite.setFacingPosition(direction);
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
