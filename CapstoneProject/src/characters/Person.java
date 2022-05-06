package characters;


import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import core.DrawingSurface;
import platforms.Platform;
import processing.core.PApplet;
import processing.core.PImage;

public class Person extends Rectangle2D.Double {

	private double xSpeed, ySpeed;
	private PImage image;
	private final double GRAVITY = 0.1;
	private boolean jumping;
	
	public Person(PImage image, double x, double y, double width, double height) {
		super(x, y, width, height);
		this.image = image;
	}
	
	public void setIcon(DrawingSurface surface, String address) {
		image = surface.loadImage(address);
		image.resize(50, 80);
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y, (float)width,(float)height);
	}
	
	// METHODS
	public void walk(int dir) {
		if (Math.abs(xSpeed) <= 1) xSpeed += dir;
		else xSpeed = xSpeed > 0 ? 1 : -1;
	}

	public void jump() {
		if (!jumping) ySpeed -= 3;
		jumping = true;
	}
	
	public void act(ArrayList<Platform> platforms) {
		x += xSpeed;
		y += ySpeed;
		if (!standing(platforms)) ySpeed += GRAVITY;
		else {
			ySpeed = 0;
			xSpeed *= 0.6;
		}
	}

	private boolean standing(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			int x = (int) getX();
			int y = (int) getY();
			int finalX = (int) (x + getWidth());
			int finalY = (int) (y + getHeight());
			Rectangle2D.Double platformBody = platform.getPlatform();
			if (platformBody.contains(new Point(x, finalY))
					|| platformBody.contains(new Point(finalX, finalY))) {
				jumping = false;
				return true;
			}
		}
		return false;
	}

	
	
	
}
