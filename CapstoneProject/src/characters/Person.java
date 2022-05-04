package characters;


import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class Person {

	private double x, y, width, height;
	private PImage image;
	
	public Person(double width, double height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void setIcon(DrawingSurface surface, String address) {
		image = surface.loadImage(address);
		image.resize(50, 80);
	}
	
	public void draw(PApplet surface) {
		if (image != null) surface.image(image, (float) x, (float) y);
	}
	
	public PImage getIcon() {
		return image;
	}
	
	
	
}
