package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;


public class Monster extends Person {
	
	
	public static double WIDTH = 70, HEIGHT = 100;
	private boolean turnRight;
	
	public Monster(PImage image, double x, double y, boolean right) {
		super(image, x, y, WIDTH, HEIGHT);
		turnRight = right;
	}
	
	
	public void walk(ArrayList<Platform> platforms) {
		if (getPlatform(platforms) != null) {
			if (turnRight) walk(1);
			else walk(-1);
			
			if (Math.abs(getX() - (getPlatform(platforms).getX() + getPlatform(platforms).getWidth() - getWidth())) < 1) turnRight = !turnRight;
			if (Math.abs(getX() - (getPlatform(platforms).getX())) < 1) turnRight = !turnRight;
			
//			if(getX() < getPlatform(platforms).getX() + getPlatform(platforms).getWidth() - getWidth() && right) {
//				super.walk(1);
//			}
//			else {
//				right = !right;
//				super.walk(-1);
//				right = false;
//				if(getX() == getPlatform(platforms).getX())
//					right = true;
//			}
		
		}
	}
	
	
	
	
	public void act(ArrayList<Platform> platforms) {
		super.act(platforms);
		walk(platforms);
		
	}
	
	

}
