package platforms;

import processing.core.PImage;
/**
 * 
 * A platform type that represents a set of spikes that characters can't step on, otherwise they 'die'.
 * @author Martin Simeonov
 *
 */
public class Spikes extends Platform{

	/**
	 * Constructs a new set of spikes
	 * @param image The image that will be used to show the spikes
	 * @param x The starting x-coordinate of the spike set
	 * @param y The starting y-coordinate of the spike set
	 * @param width The width of the spike set
	 * @param height The height of the spike set
	 */
	public Spikes(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
	}

}
