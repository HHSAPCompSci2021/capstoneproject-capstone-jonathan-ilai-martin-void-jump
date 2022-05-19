package characters;

import processing.core.PImage;

/**
 * A player is a person that can take keys.
 * @author Ilai Tamari
 */
public class Player extends Person {
	
	public static double WIDTH = 70;
	public static double HEIGHT = 100;
	private boolean hasKey;

	/**
	 * Construct a new player at the starting position of the game
	 * @param image	Player icon
	 * @param x		X-coordinate of top-left corner
	 * @param y		Y-coordinate of top-left corner
	 */
	public Player(PImage image, double x, double y) {
		super(image, x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * @return true if the player has gotten the key
	 */
	public boolean hasKey() {
		return hasKey;
	}
	
	/**
	 * Take the key the player needs to open the gate
	 */
	public void takeKey() {
		hasKey = true;
	}
	
	/**
	 * Move player to his original location and drop the key
	 */
	public void reset() {
		super.reset();
		hasKey = false;
	}

	
}
