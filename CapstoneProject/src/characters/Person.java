package characters;


import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import core.DrawingSurface;
import platforms.Platform;
import processing.core.PApplet;
import processing.core.PImage;

public class Person {

	private double x, y, width, height, xSpeed, ySpeed;
	private PImage image;
	private final double GRAVITY = 0.1;
	private Rectangle2D.Double body;
	
	public Person(double width, double height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		body = new Rectangle2D.Double(x, y, width, height);
	}
	
	public void setIcon(DrawingSurface surface, String address) {
		image = surface.loadImage(address);
		image.resize(50, 80);
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y);
	}
	
	public PImage getIcon() {
		return image;
	}
	
	// METHODS
		public void walk(int dir) {
			xSpeed += dir * 10;
		}

		public void jump() {
			ySpeed -= 7;
		}
	
	public void act(Platform[] platforms) {
		x += xSpeed;
		y += ySpeed;
		if (!onPlatform(platforms)) ySpeed += GRAVITY;
		else ySpeed = 0;
	}

	private boolean onPlatform(Platform[] platforms) {
		for (Platform platform : platforms) {
			if (platform.getPlatform().intersects(body)) return true;
		}
		return false;
	}

	
	
	
}
