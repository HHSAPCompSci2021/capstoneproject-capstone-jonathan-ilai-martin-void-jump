package platforms;

import java.util.ArrayList;

import characters.Person;
import processing.core.PImage;

public class BoostPlatform extends Platform {

	
	public BoostPlatform(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
	}

	public void boost(Person p) {
		double xSpeed = p.getXSpeed();
		if(xSpeed < 0) {
			p.setXSpeed(xSpeed + 10);
		}
		else if(xSpeed > 0) {
			p.setXSpeed(xSpeed - 10);
		}
	}
	
}
