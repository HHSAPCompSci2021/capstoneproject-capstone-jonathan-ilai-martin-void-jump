package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;

public class Monster extends Person {
	

	public static double WIDTH = 50, HEIGHT = 100;
	private boolean turnRight;
	
	public Monster(PImage image, double x, double y, boolean right) {
		super(image, x, y, WIDTH, HEIGHT);
		turnRight = right;
	}
	
	
	public void walk(ArrayList<Platform> platforms) {
		if (getPlatform(platforms) != null) {
			if (turnRight) walk(1);
			else walk(-1);
			
			if (turnRight && Math.abs(getX() - (getPlatform(platforms).getX() + getPlatform(platforms).getWidth() - getWidth())) < 1) turnRight = !turnRight;
			if (!turnRight && Math.abs(getX() - (getPlatform(platforms).getX())) < 1) turnRight = !turnRight;
		
		}
	}

	public boolean kill(Player p) {
		if (intersects(p)) {
			p.disappear();
			return true;
		}
		return false;
	}
	
	
	public void act(ArrayList<Platform> platforms) {
		super.act(platforms);
		walk(platforms);
		
	}
	
	

}
