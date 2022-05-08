package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;


public class Monster extends Person {
	
	
	
	public Monster(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
		
	}
	
	
	
	public void act(ArrayList<Platform> platforms) {
		super.act(platforms);
		if(super.standing(platforms)) {
			
		}
	}
	
	

}
