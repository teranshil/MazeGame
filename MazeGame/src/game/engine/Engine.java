package game.engine;

import java.awt.Graphics2D;

import game.gameManager.EntityManager;
import game.gameManager.LevelCheck;
import game.map.MapCreation;
import game.window.Window;

public class Engine implements Runnable {
	public static final Engine engine = new Engine();

	private volatile Window window;
	// Engine variable
	private Thread thread;
	private volatile boolean isRunning = true;
	// Draw
	private Graphics2D g2D;
	MapCreation mapCreation;

	private Engine() {

		window = new Window(640, 805, "Maze", 1);
		this.g2D = window.getGraphics();
		isRunning = true;
		start();
	}

	private void start() {
		if (isRunning) {
			thread = new Thread(this);
			isRunning = true;
			init();
			thread.start();
		}
	}

	public void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void run() {
		while (isRunning) {
			update();
			render();
			getInput();
		}
		stop();
	}

	private void init() {
		new LevelCheck(window);
	}

	public void update() {
		EntityManager.manager.update();

	}

	public void render() {
		EntityManager.manager.render(g2D);

	}

	public void getInput() {
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void getNotify() {
		synchronized (this) {
			notify();
		}
	}

}
