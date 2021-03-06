package com.lululab.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.lululab.entities.Entity;
import com.lululab.entities.Player;
import com.lululab.graphics.Spritesheet;
import com.lululab.world.World;

<<<<<<< HEAD
public class Game extends Canvas implements Runnable, KeyListener {

=======
public class Game extends Canvas implements Runnable,KeyListener{
	
>>>>>>> 9f84f4fd624d7289facb19b8712e1cd912314669
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
<<<<<<< HEAD
	public static final int WIDTH = 250;
	public static final int HEIGHT = 210;
=======
	private final int WIDTH = 250;
	private final int HEIGHT = 210;
>>>>>>> 9f84f4fd624d7289facb19b8712e1cd912314669
	private final int SCALE = 4;

	private BufferedImage image;
<<<<<<< HEAD

=======
	
>>>>>>> 9f84f4fd624d7289facb19b8712e1cd912314669
	public static List<Entity> entities;
	public static Spritesheet spritesheet;

	public static World world;
<<<<<<< HEAD

	public static Player player;

	public Game() {
=======
	
	public static Player player;
	
	public Game()
	{
>>>>>>> 9f84f4fd624d7289facb19b8712e1cd912314669
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		// Inicializando objetos.

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png ");
<<<<<<< HEAD
		player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
		entities.add(player);
		world = new World("/map.png");
	}
	// Cria??o do player

	public void initFrame() {
=======
		player = new Player(0,0,16,	16,spritesheet.getSprite(32,0,16,16));
		entities.add(player);
		world = new World("/map.png");
	}		
		//Cria??o do player
	
	public void initFrame()
	{
>>>>>>> 9f84f4fd624d7289facb19b8712e1cd912314669
		frame = new JFrame("HxH");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		Game game = new Game();
		game.start();

	}

	// L?gica do Jogo
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e instanceof Player) {
				// tick no player
			}
			e.tick();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Renderiza??o do Jogo
		world.render(g);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		// Graphics2D g2 = (Graphics2D) g; //Casting

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();
	}

	// Configura??o dos FPS
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			// Andar para a direita.
			player.right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			// Andar para a esquerda.
			player.left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			// Andar para cima.
			player.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			// Andar para baixo.
			player.down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
	}
}
