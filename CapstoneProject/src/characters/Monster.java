package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;


public class Monster extends Person {
	
	
	public static double WIDTH = 70, HEIGHT = 100;
	
	public Monster(PImage image, double x, double y) {
		super(image, x, y, WIDTH, HEIGHT);
		
	}
	
	
	
	public void act(ArrayList<Platform> platforms) {
		super.act(platforms);
		int dir = 1;
		if(super.standing(platforms)) {
			super.walk(dir);
		}
		else {
			dir = dir*(-1);
		}
	}
	
	

}
