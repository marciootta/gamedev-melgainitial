package com.kabs.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.kabs.main.Game;

public class Player extends Entity {

	public boolean left, right, up, down;
	public double speed = 1.4; 
	private ArrayList<BufferedImage> playerLeft;
	private ArrayList<BufferedImage> playerRight;
	private int maxAnimations = 3;
	private int indexAnimation = 0;
	private int framesToAnime = 4;
	private int actualFrame = 0;
	
	
	public Player(int x, int y, int h, int w, BufferedImage sprite) {
		super(x, y, h, w, sprite);
		
		playerRight = new ArrayList<BufferedImage>();
		playerLeft = new ArrayList<BufferedImage>();
		for(int i=0; i <= maxAnimations; i++) {
			 playerRight.add(Game.spritesheet.getSprite(32+i*16, 0, 16, 16));
			 playerLeft.add(Game.spritesheet.getSprite(32+i*16, 16, 16, 16));			 
		}
	}
	
	public void tick() 
	{
		boolean moved = false;
		if (right) {
			moved = true;
			setX(getX()+speed);
			sprite = playerRight.get(indexAnimation);
		}
		if (left) {
			moved = true;
			setX(getX()-speed);
			sprite = playerLeft.get(indexAnimation);
		}
		if (up) {
			moved = true;
			setY(getY()-speed);
		}
		if (down) {
			moved = true;
			setY(getY()+speed);
		}
		if (moved) {
			actualFrame++;
			if (actualFrame == framesToAnime) {
				actualFrame = 0;
				indexAnimation = indexAnimation == maxAnimations ? 0 : (indexAnimation+1);
			}
		}

	}

}
