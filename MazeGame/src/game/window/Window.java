package game.window;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.listeners.CommandListener;
import game.listeners.KeyboardListener;

public class Window {

	public static final int DEFAULTHEIGHT = 805; // 10
	public static final int DEFAULTWIDTH = 655; // 655
	private double scale = 1;

	private JFrame frame;
	private JPanel panel;
	private JTextArea textArea;
	private JPanel wrapperPanel;
	private JButton button;
	private KeyboardListener keyListener;
	private int currentHeight;
	private int currentWidth;

	private String gameTitle;
	private Graphics2D g2D;

	public Window() {
		currentHeight = DEFAULTHEIGHT;
		currentWidth = DEFAULTWIDTH;
	}

	public Window(int width, int height, String gameTitle, double scale) {
		this.gameTitle = gameTitle;
		this.scale = scale;
		this.currentWidth = (int) (width * this.scale);
		this.currentHeight = (int) (height * this.scale);
		createWindow();
	}

	private void createWindow() {

		Dimension panelDim = new Dimension(currentWidth, currentHeight);
		Dimension wrapPanelDim = new Dimension(currentWidth + 350, currentHeight);

		// Setting frame properties
		frame = new JFrame(gameTitle);

		frame.setPreferredSize(wrapPanelDim);
		frame.setMinimumSize(wrapPanelDim);
		frame.setMaximumSize(wrapPanelDim);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creating wrapper Jpanel
		createWrapperPanel(wrapPanelDim);

		// Adding to the wrapper Jpanel
		wrapperPanel.add(createGamePanel(panelDim));

		addComponents();

		frame.add(wrapperPanel);
		panel.requestFocusInWindow();
		frame.pack();
		frame.setVisible(true);

		// Taking the graphics
		g2D = (Graphics2D) panel.getGraphics();
//		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	private void addComponents() {
		// all elements
		JTextField textField = new JTextField();
		textArea = new JTextArea();
		JLabel commandLabel = new JLabel("Field for commands: ");
		JLabel commandOutput = new JLabel("Output: ");

		button = new JButton("Cick me");

		textArea.setFocusable(false);

		textArea.setBounds(655, 125, 320, 545); // 545
		textField.setBounds(655, 741, 200, 25);

		commandLabel.setBounds(655, 715, 170, 25);
		commandOutput.setBounds(655, 103, 160, 25);
		button.setBounds(855, 741, 80, 25);
		final CommandListener listener = new CommandListener(textArea, textField, panel);
		button.addActionListener(listener);

		wrapperPanel.add(textArea);
		wrapperPanel.add(button);
		wrapperPanel.add(textField);
		wrapperPanel.add(commandOutput);
		wrapperPanel.add(commandLabel);
	}

	private JPanel createGamePanel(Dimension dimension) {
		panel = new JPanel();
		keyListener = new KeyboardListener();
		panel.setVisible(true);
		panel.setBounds(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());
		panel.setPreferredSize(dimension);
		panel.addKeyListener(keyListener);
		panel.requestFocus();
		return panel;
	}

	private JPanel createWrapperPanel(Dimension dimension) {
		wrapperPanel = new JPanel();
		wrapperPanel.setVisible(true);
		wrapperPanel.setLayout(null);
		wrapperPanel.setSize(dimension);

		return wrapperPanel;
	}

	// Getters and Setters
	public Graphics2D getGraphics() {
		return g2D;
	}

	public int getCurrentHeight() {
		return currentHeight;
	}

	public int getCurrentWidth() {
		return currentWidth;
	}

	public double getScale() {
		return scale;
	}

	public JPanel getJPanel() {
		return panel;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

}
