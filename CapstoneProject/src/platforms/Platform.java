package platforms;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * Represents a platform that a person can step on and jump on
 * @author Jonathan Levi
 *
 */
public class Platform {
	
	private PImage image;
	private Rectangle2D.Double body;
	/**
	 * Constructs a new Platform given starting location, width and height 
	 * @param image platform Icon
	 * @param x  X-coordinate of top-left point
	 * @param y Y-coordinate of top-left point
	 * @param width width of the platform
	 * @param height height of the platform
	 */
	public Platform(PImage image, double x, double y, double width, double height) {
		this.image = image;
		body = new Rectangle2D.Double(x, y, width, height);
	}
	/**
	 * Draws the platform on the screen
	 * @param surface PApplet that draws the screen
	 * @pre surface cannot be null
	 * @post surface will be changed so that the platform is drawn on it
	 */
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) body.x, (float) body.y, (float) body.width, (float) body.height);
	}
	/**
	 * Returns the rectangle that represents the platform 
	 * @return rectangle that represnts the platform
	 */
	public Rectangle2D.Double getPlatform() {
		return body;
	}
	/**
	 * Returns the X-coordinate of top-left point of the platform
	 * @return X-coordinate of top-left point of the platform
	 */
	public double getX() {
		return body.x;
	}
	/**
	 * Returns the Y-coordinate of top-left point of the platform
	 * @return Y-coordinate of top-left point of the platform
	 */
	public double getY() {
		return body.y;
	}
	/**
	 * Returns the width of the platform
	 * @return width of the platform
	 */
	public double getWidth() {
		return body.width;
	}
	/**
	 * Returns the height of the platform
	 * @return height of the platform
	 */
	public double getHeight() {
		return body.height;
	}
	/**
	 * Changes the Y-coordinate of top-left point of the platform to a set value of double type
	 * @param y the new Y-coordinate of top-left point of the platform
	 */
	public void setY(double y) {
		body.y = y;
	}
	
}
