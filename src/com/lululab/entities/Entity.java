package com.lululab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.lululab.main.Game;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage HATSU_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
	public static BufferedImage NEN_EN = Game.spritesheet.getSprite(96, 16, 16, 16);
	public static BufferedImage PIG_EN = Game.spritesheet.getSprite(0, 32, 16, 16);
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	public int getX() {
		return (int)this.x;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public int getY() {
		return (int)this.y;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}

	public void tick() {	
	}
	
}
