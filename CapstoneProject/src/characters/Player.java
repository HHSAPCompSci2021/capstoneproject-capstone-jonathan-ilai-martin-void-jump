package characters;

import processing.core.PImage;

public class Player extends Person {
	
	public static double WIDTH = 70;
	public static double HEIGHT = 100;
	private boolean hasKey;

	public Player(PImage image, double x, double y) {
		super(image, x, y, WIDTH, HEIGHT);
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void takeKey() {
		hasKey = true;
	}
	
	public void reset() {
		super.reset();
		hasKey = false;
	}

	
	

}
