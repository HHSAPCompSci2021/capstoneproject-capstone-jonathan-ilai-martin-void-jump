package platforms;

import characters.Player;
import processing.core.PImage;

public class FallingPlatform extends Platform{

	public FallingPlatform(PImage image, double x, double y, double width, double height) {
		super(image, x, y, width, height);
	}

	public void fall(Player player) {
		setY(getY() + 5);
		player.setFalling(true);
	}
}
