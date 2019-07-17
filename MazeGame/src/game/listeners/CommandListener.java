package game.listeners;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.EntityID.ID;
import game.engine.Engine;
import game.gameEntities.Player;
import game.gameManager.EntityManager;

public class CommandListener implements ActionListener {
	private JTextArea textArea;
	private JPanel panel;
	private JTextField field;
	private StringBuilder textAreaStory = new StringBuilder();
	private Player player;

	public CommandListener(JTextArea textArea, JTextField field, JPanel panel) {
		this.textArea = textArea;
		this.panel = panel;
		this.field = field;
		init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player = (Player) EntityManager.manager.getElmentByID(ID.PLAYER);

		String inputData[] = field.getText().split("\\s+");
		if (inputData[0].toLowerCase().equals("help")) {
			textArea.append(" All of the available commands are: \n");
			textArea.append(" - location \n");
			textArea.append(" - move right\\left\\down\\up \n");
			textArea.append(" - drop ... <- weapon name \n");
			textArea.append(" - all weapons in backpack \n");
			textArea.append(" - quite \n");

		} else if (inputData[0].toLowerCase().equals("location"))
			textArea.append(" Player location x:" + player.getX() + " y:" + player.getY() + "\n");

		else if (inputData[0].toLowerCase().equals("quite"))
			System.exit(0);

		else if (inputData[0].toLowerCase().equals("drop")) {
			if (player.dropWeapon(inputData[1].toUpperCase()))
				textArea.append(" " + inputData[1] + " dropped \n");
			else
				textArea.append(" " + inputData[1] + " not found \n");

		} else if (inputData[0].toLowerCase().equals("move"))
			movePlayer(inputData[1]);

		field.setText("");
		panel.requestFocusInWindow();
	}

	private void init() {
		textAreaStory.append(" To start the game enter any text in the field.\n");
		textAreaStory.append(" Therefore you'll able to move the player\n with keyboard keys: W S A D.\n");
		textAreaStory.append(" Enter 'help' to see all of the \n available commands. \n");
		textArea.setFont(new Font("Bold", Font.BOLD, 14));
		textArea.setText(textAreaStory.toString());
	}

	private void movePlayer(String input) {

		if (input.toLowerCase().equals("left"))
			player.setVelX(-64);
		else if (input.toLowerCase().equals("right"))
			player.setVelX(64);
		else if (input.toLowerCase().equals("down"))
			player.setVelY(64);
		else if (input.toLowerCase().equals("up"))
			player.setVelY(-64);
		Engine.engine.getNotify();

	}

	public void addText(String str) {
		textAreaStory.append(str);
	}

}
