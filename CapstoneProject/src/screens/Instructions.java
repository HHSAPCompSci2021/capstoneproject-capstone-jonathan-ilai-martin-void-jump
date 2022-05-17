package screens;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import core.DrawingSurface;
import processing.core.PImage;

public class Instructions extends Screen {

	private PImage background, returnIcon;
	private Ellipse2D returnButton;
	
	public Instructions(DrawingSurface surface) {
		super(800,600, surface);
		returnButton = new Ellipse2D.Double(10, 10, 50, 50);
	}
	
	public void setup() {
		background = surface.loadImage("img/instructions.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		returnIcon = surface.loadImage("img/return.png");
		returnIcon.resize(50, 50);
	}
	
	public void draw() {
		// drawing stuff
		
		surface.background(255,255,255);
		surface.image(background, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.image(returnIcon, 10, 10);
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (returnButton.contains(p))
			surface.switchScreen(ScreenSwitcher.BEGINNING_SCREEN);
	}

}
