package platforms;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class Platform {
	
	private int x, y, width, height;
	private PImage image;
	private Rectangle2D.Double body;
	
	public Platform(PImage image, int x, int y, int width, int height) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		body = new Rectangle2D.Double(x, y, width, height);
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y);
	}
	
	public Rectangle2D.Double getPlatform() {
		return body;
	}
}
