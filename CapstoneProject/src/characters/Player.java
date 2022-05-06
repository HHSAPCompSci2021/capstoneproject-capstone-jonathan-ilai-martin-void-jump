package characters;

import core.DrawingSurface;
import processing.core.PImage;

public class Player extends Person {
	
	public static double WIDTH = 70, HEIGHT = 100;

	public Player(PImage image, double x, double y) {
		super(image, x, y, WIDTH, HEIGHT);
	}
	
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
