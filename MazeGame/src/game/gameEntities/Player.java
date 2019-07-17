package game.gameEntities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game.EntityID.ID;
import game.gameManager.EntityManager;
import game.gameManager.LevelCheck;
import game.util.ImageCropper;
import game.weapons.Weapon;
import game.window.Window;

public class Player extends GameObject {

	private String playerName;
	public static final int DEFAULT_X = 64, DEFAULT_Y = 64;
	private static int backpackCapacity = 10;
	private static List<GameObject> playerWeapons = new ArrayList<>();
	private int velX = 0, velY = 0;
	private Set<ID> specialTiles = new HashSet<>();
	private Window window;
	private BufferedImage grass;
	private ImageCropper imageCropper;

	/**
	 * @param x      position on the JPannel
	 * @param y      postion on the JPannel
	 * @param size   represented as Rectangle object
	 * @param String playerName
	 */

	public Player(int x, int y, String playerName, ID id, Window window) {
		super(x, y, new Rectangle(64, 64), id);
		this.playerName = playerName;
		this.window = window;
		init();
	}

	private void init() {
		specialTiles.add(ID.SWORD);
		specialTiles.add(ID.BOW);
		specialTiles.add(ID.BOMB);
		imageCropper = new ImageCropper();
		grass = imageCropper.cropper("resources\\GroundTiles\\Grass\\GrassOne.png", 0, 0, new Dimension(64, 64));
	}

	@Override
	public void update() {
		Rectangle tempRect = new Rectangle(x + velX, y + velY, 64, 64);
		if (!checkTileCollision(tempRect)) {

			x += velX;
			y += velY;
		}
		velX = 0;
		velY = 0;
	}

	@Override
	public void render(Graphics2D graphics) {

		graphics.setColor(Color.BLUE);
//		graphics.drawImage(image, x, y, null);
		graphics.fillRect(x, y, (int) rectangle.getWidth(), (int) rectangle.getHeight());

	}

	private boolean checkTileCollision(Rectangle rect) {
		Map<GameObject, Boolean> tempMap = EntityManager.manager.doesCollide(rect);
		boolean doesColide = false;

		if (tempMap != null) {
			doesColide = tempMap.values().stream().findFirst().get();

			GameObject temp = tempMap.keySet().stream().findFirst().get();
			if (temp.getID() == ID.PORTAL) {

				List<GameObject> list = new ArrayList<>();
				EntityManager.manager.setList(list);
				new LevelCheck(window);
			} else if (specialTiles.contains(temp.getID())) {
				playerWeapons.add(temp);
				if (backpackCapacity - ((Weapon) temp).getWeaponWeight() >= 0) {

					EntityManager.manager.replaceTile(temp, grass);
					backpackCapacity -= ((Weapon) temp).getWeaponWeight();
					window.getTextArea().setText(window.getTextArea().getText() + " You have picked up: "
							+ ((Weapon) temp).toString() + "\n");
					window.getTextArea()
							.setText(window.getTextArea().getText() + " Backpack capacity: " + backpackCapacity + "\n");

				} else
					window.getTextArea()
							.setText(window.getTextArea().getText() + " You cannot pick it up. Not enough space.\n");
			}
		}
		return doesColide;
	}

	public boolean dropWeapon(String weapon) {
		Iterator<GameObject> iterator = playerWeapons.iterator();
		while (iterator.hasNext()) {
			GameObject entity = iterator.next();
			if (weapon.equals(entity.getID().toString())) {
				backpackCapacity += ((Weapon) entity).getWeaponWeight();
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public List<GameObject> getPlayerWeapons() {
		return playerWeapons;
	}

	// Setter and getters
	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public String getPlayerName() {
		return playerName;
	}
}
