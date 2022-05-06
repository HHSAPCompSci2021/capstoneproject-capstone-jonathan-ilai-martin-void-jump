package portals;

import java.awt.geom.Rectangle2D;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;



public class Portal extends Rectangle2D.Double {

	
	
	public final double WIDTH = 70, HEIGHT = 100;
	public double x, y;
	private PImage image;

	
	public Portal(PImage image, double x, double y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	
	
	public void draw(PApplet surface) {
		if (image != null)
			surface.image(image, (float) x, (float) y, (float)WIDTH,(float)HEIGHT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
