package game.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.EntityID.ID;
import game.engine.Engine;
import game.gameEntities.Player;
import game.gameManager.EntityManager;

public class KeyboardListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (EntityManager.manager.getIsRunning()) {
			Player player = (Player) EntityManager.manager.getElmentByID(ID.PLAYER);
			if (e.getKeyCode() == KeyEvent.VK_W) {
				player.setVelY(-64);
				Engine.engine.getNotify();
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				player.setVelY(64);
				Engine.engine.getNotify();
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				player.setVelX(64);
				Engine.engine.getNotify();
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				player.setVelX(-64);
				Engine.engine.getNotify();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
