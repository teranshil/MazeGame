package game.weapons;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.EntityID.ID;
import game.gameEntities.GameObject;

public class Bomb extends GameObject implements Weapon {
	private final int weaponWeight = 1;
	private final double weaponDamage = 200.0;

	public Bomb(int x, int y, BufferedImage image, ID entityID) {
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

	@Override
	public void dealDamage() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWeaponWeight() {
		return weaponWeight;
	}

	public double getWeaponDamage() {
		return weaponDamage;
	}

	@Override
	public String toString() {
		return "bomb";
	}

}
