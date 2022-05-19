package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;

public class Teleporter extends Monster {

	private double width, height;
	public Teleporter(PImage image, double x, double y, boolean right, double width, double height) {
		super(image, x, y, right);
		this.width = width;
		this.height = height;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void teleport() {
		double rHeight = Math.random() * height + 1;
		double rWidth = Math.random() * width + 1;
		if(rHeight >= y && rHeight <= height && rWidth >= x && rWidth <= width) {
			setX(rWidth);
			setY(rHeight);
		}
		
	}
	
	public void act(ArrayList<Platform> platforms) {
		
		
		//System.out.println(jumping);
		
//		if (standing(platforms) == null) {
//			ySpeed += GRAVITY;
//			xSpeed *= 0.9;
//		} else {
//			jumping = false;
//			ySpeed = 0;
//			xSpeed *= 0.6;
//		}
	}
	
	

}
