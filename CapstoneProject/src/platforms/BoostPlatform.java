package platforms;

import characters.Person;
import processing.core.PImage;

public class BoostPlatform extends Platform {

	private boolean isRight;
	
	public BoostPlatform(PImage image, double x, double y, double width, double height, boolean isRight) {
		super(image, x, y, width, height);
		this.isRight = isRight;
	}

	public void boost(Person p) {
		double xSpeed = p.getXSpeed();
		if(isRight) {
			p.setXSpeed(xSpeed + 10);
		}
		else{
			p.setXSpeed(xSpeed - 10);
		}
	}
	
}
