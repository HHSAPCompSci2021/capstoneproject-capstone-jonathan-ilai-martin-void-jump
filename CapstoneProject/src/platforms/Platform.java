package platforms;

import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class Platform {
	
	private int x, y, width, height;
	private PImage image;
	
	public Platform(PImage image, int x, int y, int width, int height) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y);
	}
}
