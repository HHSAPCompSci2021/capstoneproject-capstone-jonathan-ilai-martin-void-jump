package characters;

import core.DrawingSurface;

public class Player extends Person {

	public Player(double width, double height, int x, int y) {
		super(width, height, x, y);
	}
	
	public void setIcon(DrawingSurface surface) {
		super.setIcon(surface, "img/Wizard.png");
	}

}
