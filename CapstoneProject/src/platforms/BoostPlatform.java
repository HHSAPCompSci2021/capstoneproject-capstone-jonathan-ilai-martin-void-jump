package platforms;

import characters.Person;
import processing.core.PImage;
/**
 * Represents a type of platform that boosts a person when steps on it
 * @author Jonathan Levi
 *
 */
public class BoostPlatform extends Platform {

	private boolean isRight;
	/**
	 * Constructs a new BoostPlatform with a given starting location, width and height, and whether it boosts left or right
	 * @param image platform Icon
	 * @param x  X-coordinate of top-left point
	 * @param y Y-coordinate of top-left point
	 * @param width width of the platform
	 * @param height height of the platform
	 * @param isRight true if boosts left, false if boosts right
	 */
	public BoostPlatform(PImage image, double x, double y, double width, double height, boolean isRight) {
		super(image, x, y, width, height);
		this.isRight = isRight;
	}
	/**
	 * Increases a persons speed in the direction of the platform, based on isRight
	 * @param p Person that steps on the platform
	 */
	public void boost(Person p) {
		double xSpeed = p.getXSpeed();
		if(isRight) {
			p.setXSpeed(xSpeed + 10);
		}
		else{
			p.setXSpeed(xSpeed - 10);
		}
	}
	
}
