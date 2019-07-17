package game.gameManager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import game.EntityID.ID;
import game.gameEntities.GameObject;
import game.gameEntities.Grass;

public class EntityManager {
	private volatile List<GameObject> entities = new ArrayList<>();
	public static final EntityManager manager = new EntityManager();
	private volatile boolean isRunning = true;
	// filter which game objects will be rendering constantly
//	Predicate<GameObject> filter = e -> e.getID() != ID.Grass && e.getID() != ID.Rock && e.getID() != ID.Portal;

	private EntityManager() {

	}

	public <E extends GameObject> void addEntity(E element) {
		entities.add(element);
	}

	public void update() {
//			entities.stream().filter(e -> isRunning).forEach(e -> e.update());
		for (int index = 0; index < entities.size(); index++) {
			if (isRunning)
				entities.get(index).update();
		}
	}

	public void render(Graphics2D g2D) {
//			entities.stream().filter(e -> isRunning).forEach(e -> e.render(g));
		for (int index = 0; index < entities.size(); index++) {
			if (isRunning)
				entities.get(index).render(g2D);
		}
	}

	public boolean isIntersecting(Rectangle rectangle) {
		return false;
	}

	public List<GameObject> getAllElements() {
		return entities;
	}

	public void replaceTile(GameObject tile, BufferedImage image) {
		ListIterator<GameObject> entityIterator = entities.listIterator();

		while (entityIterator.hasNext()) {

			GameObject tempTile = entityIterator.next();
			if (tempTile.getX() == tile.getX() && tempTile.getY() == tile.getY()) {
				isRunning = false;
				Grass grass = new Grass(tile.getX(), tile.getY(), image, ID.GRASS);
				entityIterator.set(grass);
				isRunning = true;
			}

		}
	}

	public GameObject getElmentByID(ID ID) {
		return entities.stream().filter(e -> e.getID() == ID).findFirst().get();
	}

	public Map<GameObject, Boolean> doesCollide(Rectangle rentangle) {
		for (int index = 0; index < entities.size(); index++) {
			GameObject temp = entities.get(index);

			if (temp.getRectagle().intersects(rentangle) && temp.getID() != ID.GRASS) {
				Map<GameObject, Boolean> tempMap = new HashMap<>();
				tempMap.put(temp, true);
				return tempMap;
			}
		}
		return null;
	}

	public void setList(List<GameObject> list) {
		this.entities = list;
	}

	public boolean getIsRunning() {
		return isRunning;
	}

}
