package platforms;

import processing.core.PImage;

public class FallingPlatform extends Platform{

	public FallingPlatform(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
	}

	
	
	public void fall() {
		while(getY() <= 0) 
			super.setY(getY() - 5);
	}
}
