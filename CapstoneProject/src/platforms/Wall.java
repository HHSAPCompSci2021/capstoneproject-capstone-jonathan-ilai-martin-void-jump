package platforms;

import processing.core.PImage;

/**
 * A kind of platform that is a wall and the player can't walk through it.
 * @author Ilai Tamari
 *
 */
public class Wall extends Platform {

	/**
	 * Construct a new wall
	 * @param image		Icon
	 * @param x			X-coordinate of top-left corner
	 * @param y			Y-coordinate of top-right corner
	 * @param width		Width of wall
	 * @param height	Height of wall
	 */
	public Wall(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
	}

}
