package platforms;

import characters.Player;
import processing.core.PImage;
/**
 * Represents a type of platform that falls down when a player steps on it
 * @author Jonathan Levi
 *
 */
public class FallingPlatform extends Platform{
	/**
	 * Constructs a new FallingPlatform given starting location, width and height 
	 * @param image platform Icon
	 * @param x  X-coordinate of top-left point
	 * @param y Y-coordinate of top-left point
	 * @param width width of the platform
	 * @param height height of the platform
	 */
	public FallingPlatform(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
	}
	/**
	 * Moves the platform down when a player steps on it 
	 * @param player Player that steps on the platform
	 */
	public void fall(Player player) {
		setY(getY() + 5);
		player.setFalling(true);
	}
}
