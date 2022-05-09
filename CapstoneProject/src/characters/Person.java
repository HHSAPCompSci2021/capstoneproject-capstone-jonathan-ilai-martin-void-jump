package characters;


import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import platforms.Platform;
import platforms.Wall;
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
	
	public double getXSpeed() {
		return xSpeed;
	}
	
	public double getYSpeed() {
		return ySpeed;
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y, (float)width,(float)height);
	}
	
	// METHODS
	public void walk(int dir) {
		if (Math.abs(xSpeed) <= 1) xSpeed += dir;
		else xSpeed = xSpeed > 0 ? 1 : -1;
		
	}


	private boolean inside(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			int x = (int) (getX() + xSpeed + getWidth() * 0.1);
			int middleX = xSpeed > 0 ? (int) (x + getWidth() * 0.5) : (int) (x - getWidth() * 0.5);
			int finalX = this instanceof Player ? middleX : xSpeed > 0 ? (int) (x + getWidth() * 0.9) : (int) (x - getWidth() * 0.9);
			int y = (int) (getY() + ySpeed);
			int middleY = (int) (y + getHeight() * 0.5);
			int finalY = (int) (y + getHeight() * 0.9);
			Rectangle2D.Double body = platform.getPlatform();
			if (body.contains(new Point(x, y)) || body.contains(new Point(finalX, y))
					|| body.contains(new Point(x, finalY)) || body.contains(finalX, finalY)
					|| body.contains(new Point(x, middleY)) || body.contains(middleX, middleY)) return true;
		}
		return false;
	}

	public void jump(ArrayList<Platform> platforms) {
		if (standing(platforms)) {
			if (!jumping) ySpeed -= 4;
			jumping = true;
		}
	}
	
	public void act(ArrayList<Platform> platforms) {
		//inside = false;
		if (!inside(platforms)) {
			x += xSpeed;
			y += ySpeed;
		} else xSpeed = 0;
		
		
		if (!standing(platforms)) {
			ySpeed += GRAVITY;
			xSpeed *= 0.9;
		} else {
			ySpeed = 0;
			xSpeed *= 0.6;
		}
	}
	
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	protected boolean standing(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			int x = (int) getX();
			int y = (int) getY();
			int finalX = this instanceof Player ? (int) (x + getWidth() / 2) : (int) (x + getWidth());
			int finalY = (int) (y + getHeight());
			Rectangle2D.Double platformBody = platform.getPlatform();
			if ((platformBody.contains(new Point(x, finalY)) 
					|| platformBody.contains(new Point(finalX, finalY))) 
					&& !(platform instanceof Wall)) {
				jumping = false;
				return true;
			}
//			else if ((platformBody.contains(new Point(x, finalY)) 
//					|| platformBody.contains(new Point(finalX, finalY))) 
//					&& (platform instanceof Wall)) {
//				xSpeed = 0;
//				ySpeed = 1;
//				return false;
//			}
			
		}
		return false;
	}
	
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean touchingWall(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			if (platform instanceof Wall && platform.getPlatform().intersects(this)) return true;
		}
		return false;
	}
	
	
	
}
