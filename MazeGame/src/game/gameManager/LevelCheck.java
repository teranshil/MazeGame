package game.gameManager;

import java.io.File;

import game.EntityID.ID;
import game.gameEntities.Player;
import game.map.MapCreation;
import game.window.Window;

public class LevelCheck {

	private static final String DEFAULT_LEVEL = "Map";
	private static int countLevels = 0;

	public LevelCheck(Window window) {

		File file = new File("resources\\" + DEFAULT_LEVEL + countLevels + ".txt");
		window.getJPanel().removeAll();
		window.getJPanel().updateUI();
		new MapCreation(window, file);

		EntityManager.manager.addEntity(new Player(Player.DEFAULT_X, Player.DEFAULT_Y, "Theo", ID.PLAYER, window));
		countLevels++;
	}

}
