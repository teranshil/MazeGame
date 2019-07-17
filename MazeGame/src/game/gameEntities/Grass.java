package game.gameEntities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.EntityID.ID;

public class Grass extends GameObject {

	public Grass(int x, int y, BufferedImage image, ID entityID) {
		super(x, y, new Rectangle(x, y, image.getWidth(), image.getHeight()), entityID);
		this.image = image;
	}

	@Override
	public void render(Graphics2D graphics) {
		graphics.drawImage(image, x, y, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
