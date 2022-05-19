package characters;

import java.util.ArrayList;

import platforms.Platform;
import processing.core.PImage;

public class Teleporter extends Monster {

	private double width, height, startX, startY;
	public Teleporter(PImage image, double x, double y, boolean right, double width, double height) {
		super(image, x, y, right);
		this.width = width;
		this.height = height;
		this.startX = x;
		this.startY = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	
	public void teleport() {
		double rHeight = Math.random() * height + 1;
		rHeight += startY;
		double rWidth = Math.random() * width + 1;
		rWidth += startX;
		if(rHeight >= startY && rHeight <= height + startY && rWidth >= startX && rWidth <= width + startX) {
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
