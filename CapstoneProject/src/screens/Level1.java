package screens;

import core.DrawingSurface;
import platforms.Platform;
import processing.core.PImage;

public class Level1 extends Level {

	public Level1(DrawingSurface surface) {
		super(surface, 100, 100, 500, 500, 700, 100);
	}

	@Override
	protected void addPlatforms() {
		PImage platform = surface.loadImage("img/platform.png");
		platform.resize(100, 30);
		platforms[0] = new Platform(platform, 50, 150, 100, 30);
	}

}
