package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;
/**
 * This class represents a special type of monster that does not act like a person and teleports around
 * trying to kill the player.
 * @author Jonathan Levi
 *
 */
public class Teleporter extends Monster {

	private double width, height, startX, startY;
	/**
	 * Constructs a new Teleporter at a given position, and a given teleporting bounds
	 * @param image Icon
	 * @param x X-coordinate of top-left corner
	 * @param y Y-coordinate of top-left corner
	 * @param right boolean true if automatic walking starts right
	 * @param width the width of the teleporting bounds 
	 * @param height the height of the teleporting bounds
	 */
	public Teleporter(PImage image, double x, double y, boolean right, double width, double height) {
		super(image, x, y, right);
		this.width = width;
		this.height = height;
		this.startX = x;
		this.startY = y;
	}
	/**
	 * Changes x to a set double
	 * @param x new X-Coordinate 
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Changes y to a set double
	 * @param y new Y-Coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Moves the teleporter to a random location inside of the teleporting bounds
	 */
	public void teleport() {
		double rHeight = Math.random() * height + 1;
		rHeight += startY;
		double rWidth = Math.random() * width + 1;
		rWidth += startX;
		if(rHeight >= startY && rHeight <= height + startY && rWidth >= startX && rWidth <= width + startX) {
			setX(rWidth);
			setY(rHeight);
		}
		
	}
	
	/**
	 * Overrides Person's act method so that gravity has no effect on the 
	 * teleporter 
	 */
	public void act(ArrayList<Platform> platforms) {
		
		
		//System.out.println(jumping);
		
//		if (standing(platforms) == null) {
//			ySpeed += GRAVITY;
//			xSpeed *= 0.9;
//		} else {
//			jumping = false;
//			ySpeed = 0;
//			xSpeed *= 0.6;
//		}
	}
	
	

}
