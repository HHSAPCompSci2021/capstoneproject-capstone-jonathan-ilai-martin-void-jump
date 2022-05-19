package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;
/**
 * This class represents a monster that is of person type that can kill a player.
 * A monster walks on its own and acts exactly like a person.
 * @author Jonathan Levi
 *
 */
public class Monster extends Person {
	

	public static double WIDTH = 50, HEIGHT = 100;
	private boolean turnRight;
	/**
	 * Constructs a new monster with at a given position either walking right at first or left
	 * @param image Icon
	 * @param x X-coordinate of top-left corner
	 * @param y Y-coordinate of top-left corner	
	 * @param right true if automatic walking starts right
	 */
	public Monster(PImage image, double x, double y, boolean right) {
		super(image, x, y, WIDTH, HEIGHT);
		turnRight = right;
	}
	
	/**
	 * Has the monster walk by itself and turns when reaching the end of the platform
	 * @param platforms Platforms in the game
	 */
	public void walk(ArrayList<Platform> platforms) {
		if (getPlatform(platforms) != null) {
			if (turnRight) walk(1);
			else walk(-1);
			
			if (turnRight && Math.abs(getX() - (getPlatform(platforms).getX() + getPlatform(platforms).getWidth() - getWidth())) < 1) turnRight = !turnRight;
			if (!turnRight && Math.abs(getX() - (getPlatform(platforms).getX())) < 1) turnRight = !turnRight;
		
		}
	}
	/**
	 * 
	 * @param p Player 
	 * @post restarts the level if true
	 * @return true if the monster intersects with a player
	 */
	public boolean kill(Player p) {
		if (intersects(p)) {
			p.disappear();
			return true;
		}
		return false;
	}
	
	/**
	 * Acts like a person, gravity applies and continues to walk forvever 
	 */
	public void act(ArrayList<Platform> platforms) {
		super.act(platforms);
		walk(platforms);
		
	}
	
	

}
