package com.kabs.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	private double x;
	private double y;
	private double h;
	private double w;
	protected BufferedImage sprite;

	public Entity(int x, int y, int h, int w, BufferedImage sprite) {
		this.x=  x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.sprite = sprite;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getH() {
		return  h;
	}
	public double getW() {
		return w;
	}
	public void setX(double newValue) {
		x = newValue;
	}
	public void setY(double newValue) {
		y = newValue;
	}
	public void setW(double newValue) {
		w = newValue;
	}
	public void setH(double newValue) {
		h = newValue;
	}
	public void render(Graphics g)
	{
		g.drawImage(sprite, (int)getX(), (int)getY(), null);
	}
	
	public void tick() {
		
	}
}
