package platforms;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public class Platform extends Rectangle2D.Double{
	
	private double x, y, width, height;
	private PImage image;
	private Rectangle2D.Double body;
	
	public Platform(PImage image, double x, double y, double width, double height) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		body = new Rectangle2D.Double(x, y, width, height);
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y, (float) width, (float) height);
	}
	
	public Rectangle2D.Double getPlatform() {
		return body;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	
}
