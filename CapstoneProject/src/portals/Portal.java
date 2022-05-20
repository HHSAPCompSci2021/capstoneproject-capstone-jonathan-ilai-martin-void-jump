package portals;

import java.awt.geom.Rectangle2D;

import characters.Person;
import characters.Player;
import core.DrawingSurface;
import ilaitm12.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Point;


/**
 * Represents a portal which a player can pass through
 * @author Jonathan Levi
 *
 */
public class Portal extends Rectangle2D.Double {

	public static final double WIDTH = 70, HEIGHT = 100;
	private double x, y;
	private PImage image;
	private boolean drawn;

	/**
	 * Constructs a new Portal given a starting location and whether or not it should be drawn
	 * @param image Icon
	 * @param x X-coordinate of top-left point
	 * @param y Y-coordinate of top-left point
	 * @param drawn true if it should be drawn on screen and false otherwise
	 */
	public Portal(PImage image, double x, double y, boolean drawn) {
		super(x, y, WIDTH, HEIGHT);
		this.image = image;
		this.drawn = drawn;

	}
	/**
	 * Changes the drawn parameter of the Portal to a set boolean
	 * @param drawn true if it should be drawn and false otherwise
	 */
	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}
	/**
	 * Changes the X-Coordinate of the top-left corner of the Portal to a set value
	 * @param x new X-Coordinate of the top-left corner of the Portal
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Changes the Y-Coordinate of the top-left corner of the Portal to a set value
	 * @param y new Y-Coordinate of the top-left corner of the Portal
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Returns the X-coordinate of top-left corner of the portal
	 * @return X-coordinate of top-left point of the portal
	 */
	public double getX() {
		return x;
	}
	/**
	 * Returns the Y-coordinate of top-left point of the platform
	 * @return Y-coordinate of top-left point of the platform
	 */
	public double getY(){
		return y;
	}
	/**
	 * Returns whether or not the portal should be drawn
	 * @return true if the portal should be drawn and false if not
	 */
	public boolean getDrawn() {
		return drawn;
	}
	
	/**
	 * Draws the portal on the screen
	 * @param surface PApplet that draws the screen
	 * @pre surface cannot be null
	 * @post surface will be changed so that the portal is drawn on it
	 */
	public void draw(PApplet surface) {
		if (image != null)
			surface.image(image, (float) x, (float) y, (float)WIDTH,(float)HEIGHT);
	}
	
	/**
	 * Checks to see if a person is inside of a portal
	 * @param p Person that is intersecting the portal
	 * @return true if the person is intersecting the portal and false otherwise
	 */
	public boolean isInside(Person p) {
		Point center = new Point((int)(p.getX() + p.getWidth() / 2), (int)(p.getY() + p.getHeight() / 2));
		return contains(center);
	}
	

}
