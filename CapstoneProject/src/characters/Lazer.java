package characters;

import ilaitm12.shapes.Line;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents a lazer that can kill characters if being touched. A lazer
 * is always parallel to the x-axis.
 * @author Ilai Tamari
 */
public class Lazer extends Line {
	
	private PImage icon;

	/**
	 * Construct a new lazer using the lazer icon at a specific location with a given length.
	 * @param image		Lazer icon
	 * @param x			X-coordinate of top-left point
	 * @param y			Y-coordinate of top-left point
	 * @param length	Horizontal length
	 */
	public Lazer(PImage image, double x, double y, double length) {
		super(x, y, x + length, y);
		icon = image;
		icon.resize((int) length, image.height);
	}
	
	/**
	 * Draw the lazer icon using the line's position and length.
	 */
	public void draw(PApplet surface) {
		surface.image(icon, (float) getX(), (float) getY());
	}
}
