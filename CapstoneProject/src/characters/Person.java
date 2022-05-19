package characters;


import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import platforms.FallingPlatform;
import platforms.ForceBarrier;
import platforms.Platform;
import platforms.Wall;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents any person (player or monster) that appears in the game. A
 * person can change speed and direction and jump. Objects of this class can't get
 * inside of platforms, and they can use portals.
 * @author Ilai Tamari
 * 
 */
public class Person extends Rectangle2D.Double {

	private double xSpeed, ySpeed;
	private PImage image;
	private final double GRAVITY = 0.1;
	private boolean jumping, drawn, falling;
	
	/**
	 * Construct a new person at a given position with a given size.
	 * @param image		Icon
	 * @param x			X-coordinate of top-left corner
	 * @param y			Y-coordinate of top-left corner
	 * @param width		Width of person
	 * @param height	Height of person
	 */
	public Person(PImage image, double x, double y, double width, double height) {
		super(x, y, width, height);
		this.image = image;
		drawn = true;
	}
	
	/**
	 * @return true if person needs to be drawn
	 */
	public boolean isDrawn() {
		return drawn;
	}
	
	/**
	 * Reset the person's speed and boolean attributes.
	 * @post	The player stops
	 */
	public void reset() {
		xSpeed = 0;
		ySpeed = 0;
		jumping = false;
		drawn = true;
	}
	
	/**
	 * Change the person's horizontal speed
	 * @param x	new speed
	 * @post	Person's speed changes
	 */
	public void setXSpeed(double x) {
		xSpeed = x;
	}
	
	/**
	 * @return the person's horizontal speed
	 */
	public double getXSpeed() {
		return xSpeed;
	}
	
	/**
	 * @return the person's current x-location
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return the person's vertical speed
	 */
	public double getYSpeed() {
		return ySpeed;
	}
	
	/**
	 * @return the person's current y-location
	 */
	public double getY() {
		return y;
	}
	
	
	
	/**
	 * Draw the person's icon where needed
	 * @param surface
	 */
	public void draw(PApplet surface) {
		if (drawn && image != null) surface.image(image, (float) x, (float) y, (float)width,(float)height);
	}
	
	/**
	 * Make the person unseenable
	 * @post Person is not drawn
	 */
	public void disappear() {
		drawn = false;
	}
	
	/**
	 * Change horizontal speed
	 * @param dir	Amount by which to increase speed
	 * @post	Sppeed changes
	 */
	public void walk(int dir) {
		if (Math.abs(xSpeed) <= 1) xSpeed += dir;
		else xSpeed = xSpeed > 0 ? 1 : -1;
		
	}

	private boolean inside(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			if (!(platform instanceof ForceBarrier)) {
				int x = (int) (getX() + xSpeed + getWidth() * 0.1);
				int middleX = (int) (x + getWidth() * 0.5);
				int finalX = this instanceof Player ? middleX : (int) (x + getWidth() * 0.9);
				int y = (int) (getY() + ySpeed);
				int middleY = (int) (y + getHeight() * 0.5);
				int finalY = (int) (y + getHeight() * 0.9);
				Rectangle2D.Double body = platform.getPlatform();
				if (body.contains(new Point(x, y)) || body.contains(new Point(finalX, y))
						|| body.contains(new Point(x, finalY)) || body.contains(finalX, finalY)
						|| body.contains(new Point(x, middleY)) || body.contains(middleX, middleY)) return true;
			
			}
		}
		return false;
	}

	/**
	 * Decrease y-speed
	 * @param platforms	Platfomrs in the game
	 * @post	Y-speed changes
	 */
	public void jump(ArrayList<Platform> platforms) {
		if (!jumping) {
			Platform platform = standing(platforms);
			if (platform != null) {
				ySpeed -= 4;
				jumping = true;
			}
		}
		if (falling) {
			ySpeed -= 4;
			falling = false;
		}
	}
	
	/**
	 * If possible, change location according to speed. Then, obey to gavity and reduce
	 * horizontal speed if moving on platform.
	 * @param platforms
	 */
	public void act(ArrayList<Platform> platforms) {
		//inside = false;
		if (!inside(platforms)) {
			x += xSpeed;
			y += ySpeed;
		} else xSpeed = 0;
		
		//System.out.println(jumping);
		
		if (standing(platforms) == null) {
			ySpeed += GRAVITY;
			xSpeed *= 0.9;
		} else {
			jumping = false;
			ySpeed = 0;
			xSpeed *= 0.6;
		}
	}
	
	public Platform standing(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			if (!(platform instanceof ForceBarrier)) {
				int x = (int) getX();
				int y = (int) getY();
				int finalX = this instanceof Player ? (int) (x + getWidth() / 2) : (int) (x + getWidth());
				int finalY = (int) (y + getHeight());
				Rectangle2D.Double platformBody = platform.getPlatform();
				if ((platformBody.contains(new Point(x, finalY)) 
						|| platformBody.contains(new Point(finalX, finalY))) 
						&& !(platform instanceof Wall)) {
					return platform;
				}
			}
			if (platform instanceof FallingPlatform && Math.abs(platform.getY() - (y + Player.HEIGHT)) < 10 && x > platform.getX() && x + Player.WIDTH < platform.getX() + platform.getWidth())
				return platform;
		}
		return null;
	}
	
	
	
	/**
	 * Move person to a new location
	 * @param x	New x-location
	 * @param y	New y-location
	 * @post	Person is drawn at a different location
	 */
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Search for the platform on which the person stands
	 * @param platforms	All platforms in the game
	 * @return	platform on which the person stands
	 */
	public Platform getPlatform(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			ArrayList<Platform> platformBelow = new ArrayList<Platform>();
			platformBelow.add(platform);
			if (standing(platformBelow) != null) {
				return platform;
			}
			
		}
		return null;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	/**
	 * Check if the person is touching any wall
	 * @param platforms	All platforms in the game
	 * @return true if the player is touching any wall
	 */
	public boolean touchingWall(ArrayList<Platform> platforms) {
		for (Platform platform : platforms) {
			if (platform instanceof Wall && platform.getPlatform().intersects(this))
				return true;
		}
		return false;
	}
	
	
}
