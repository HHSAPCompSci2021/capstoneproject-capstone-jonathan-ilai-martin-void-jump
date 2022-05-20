package platforms;

import processing.core.PImage;
/**
 * Represents a type of platform that a person can move through but stops portal shots
 * @author Jonathan Levi
 *
 */
public class ForceBarrier extends Platform {
	/**
	 * Constructs a new ForceBarrier given starting location, width and height 
	 * @param image platform Icon
	 * @param x  X-coordinate of top-left point
	 * @param y Y-coordinate of top-left point
	 * @param width width of the platform
	 * @param height height of the platform
	 */
	public ForceBarrier(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
	}
}
