package com.kabs.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.kabs.entities.Entity;
import com.kabs.entities.Player;
import com.kabs.graficos.Spritesheet;
 
 

public class Game extends Canvas implements Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private final int WIDTH=160;
	private final int HEIGHT=120;
	private final int SCALE=4;
	private boolean isRunning;
	private BufferedImage image;
	
	public List<Entity> entities;
	public static Spritesheet spritesheet;
	public Player player;
 
	
	public Game() {
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		addKeyListener(this);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16, spritesheet.getSprite(32, 0, 16, 16));
		
		entities.add(player);
		
 
	}

	public static void main(String[] args) {
		Game game=new Game();
		game.start();
	}

	private void initFrame() {
		frame = new JFrame("Game #1");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
		

	public synchronized void start() {
		isRunning = true;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		// implementar stop
		isRunning = false;
 
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double fps = 0;
		double timerParaFps = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			if (delta >= 1.0) {
				tick();
				render();
				delta--;
				fps++;				
			}	
			lastTime = now;
			if (System.currentTimeMillis() - timerParaFps > 1000) {
				System.out.println("FPS: " + fps);
				fps = 0;
				timerParaFps += 1000;
			}
		}
		
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		// inicializa a tela 
		Graphics g = image.getGraphics();
		g.setColor(new Color(100,100,155)); 
		g.fillRect(0, 0, WIDTH, HEIGHT);
		 
		for (Entity ent: entities) {
			ent.render(g);
		}
		
		
		// ---------------------------
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);
		bs.show();
		
	}

	private void tick() {
		for(Entity ent : entities) {
			ent.tick();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || 
				e.getKeyCode() == KeyEvent.VK_D)
				player.right = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT || 
				e.getKeyCode() == KeyEvent.VK_A)
				player.left = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S)
				player.down = true;
		if (e.getKeyCode() == KeyEvent.VK_UP || 
				e.getKeyCode() == KeyEvent.VK_W)
				player.up = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || 
				e.getKeyCode() == KeyEvent.VK_D)
				player.right = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT || 
				e.getKeyCode() == KeyEvent.VK_A)
				player.left = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S)
				player.down = false;
		if (e.getKeyCode() == KeyEvent.VK_UP || 
				e.getKeyCode() == KeyEvent.VK_W)
				player.up = false;
	}
}
