package game.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import game.EntityID.ID;
import game.gameEntities.GameObject;
import game.gameEntities.Grass;
import game.gameEntities.Lava;
import game.gameEntities.Portal;
import game.gameEntities.Rock;
import game.gameManager.EntityManager;
import game.util.ImageCropper;
import game.util.ScaleImage;
import game.weapons.Bomb;
import game.weapons.Bow;
import game.weapons.Sword;
import game.window.Window;

public class MapCreation {
	private Dimension tileDim = new Dimension(64, 64);
	private BufferedImage grass, rock, lava, sword, bow, bomb, portal;
	public static BufferedImage hero;
	private File file;
	private ImageCropper imageCropper = new ImageCropper();

	public MapCreation(Window window, File file) {
		this.file = file;
		createMap();
	}

	public void render(Graphics2D graphics) {

	}

	private void createMap() {
		createTiles();
		readMap();
	}

	private void createTiles() {

		// initialization of all tiles
		grass = imageCropper.cropper("resources\\GroundTiles\\Grass\\GrassOne.png", 0, 0, tileDim);
		rock = imageCropper.cropper("resources\\GroundTiles\\Rock\\RockTiles64.png\\", 0, 0, tileDim);
		lava = imageCropper.cropper("resources\\GroundTiles\\Lava\\Lava.png\\", 0, 0, new Dimension(16, 16));
		portal = imageCropper.cropper("resources\\GroundTiles\\Portal\\portal.png\\", 64, 96, new Dimension(32, 32));
		sword = imageCropper.cropper("resources\\Entities\\Player\\PlayerWeapons\\weapons.png", 16, 48,
				new Dimension(16, 16));
		bow = imageCropper.cropper("resources\\Entities\\Player\\PlayerWeapons\\weapons.png", 32, 48,
				new Dimension(16, 16));
		bomb = imageCropper.cropper("resources\\Entities\\Player\\PlayerWeapons\\weapons.png", 16, 16,
				new Dimension(16, 16));
		hero = imageCropper.cropper("resources\\Entities\\Player\\readhead.png", 0, 0, new Dimension(32, 32));

		lava = new ScaleImage().scaleImage(lava, 4);
		portal = new ScaleImage().scaleImage(portal, 2);
		sword = new ScaleImage().scaleImage(sword, 4);
		bow = new ScaleImage().scaleImage(bow, 4);
		bomb = new ScaleImage().scaleImage(bomb, 4);
		hero = new ScaleImage().scaleImage(hero, 2);
	}

	private void readMap() {
		int y = 0;

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				int x = 0;
				String[] elements = scanner.nextLine().trim().split("\\s+");
				for (int index = 0; index < elements.length; index++) {
					EntityManager.manager.addEntity(chooseTile(elements[index], x, y));
					x += 64;
				}
				y += 64;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private GameObject chooseTile(String str, int x, int y) {

		switch (str) {
		case "x":
			return new Rock(x, y, rock, ID.ROCK);
		case "o":
			return new Grass(x, y, grass, ID.GRASS);
		case "s":
			return new Sword(x, y, sword, ID.SWORD);
		case "b":
			return new Bow(x, y, bow, ID.BOW);
		case "m":
			return new Bomb(x, y, bomb, ID.BOMB);
		case "l":
			return new Lava(x, y, lava, ID.LAVA);
		case "0":
			return new Portal(x, y, portal, ID.PORTAL);

		default:
//			return new Grass(0, 0, rock, ID.Grass);
			return null;
		}
	}

}
