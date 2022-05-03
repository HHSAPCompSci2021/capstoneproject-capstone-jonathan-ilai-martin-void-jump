package characters;

import processing.core.PApplet;
import processing.core.PImage;

public class Character {

	private double x, y, width, height;
	private PImage image;
	
	public Character(PImage image, double width, double height, int x, int y) {
		this.image = image;
		this.width = width;
		this.height = height;

		this.x = x;
		this.y = y;
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y);
	}
	
	
	
	
	
}
