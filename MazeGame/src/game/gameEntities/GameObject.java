package game.gameEntities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.EntityID.ID;

public abstract class GameObject {
	protected int x, y;
	protected Rectangle rectangle;
	protected ID entityID;
	protected BufferedImage image;

	public GameObject(int x, int y, Rectangle rectangle, ID entityID) {
		this.x = x;
		this.y = y;
		this.rectangle = rectangle;
		this.entityID = entityID;
	}

	public abstract void render(Graphics2D graphics);

	public abstract void update();

	// Setters and getters
	public ID getID() {
		return entityID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getRectagle() {
		return rectangle;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
