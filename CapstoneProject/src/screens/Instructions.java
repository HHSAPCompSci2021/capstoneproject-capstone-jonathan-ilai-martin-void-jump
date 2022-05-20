package screens;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import core.DrawingSurface;
import processing.core.PImage;

/**
 * This class includes the instructions to how to play the game
 * @author Ilai Tamari
 *
 */
public class Instructions extends Screen {

	private PImage background, returnIcon;
	private Ellipse2D returnButton;
	
	/**
	 * Create a new instructions screen
	 * @param surface	Surface on which screen is drawn
	 */
	public Instructions(DrawingSurface surface) {
		super(800,600, surface);
		returnButton = new Ellipse2D.Double(10, 10, 50, 50);
	}
	
	/**
	 * Set up background picture and return button
	 */
	public void setup() {
		background = surface.loadImage("img/instructions.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		returnIcon = surface.loadImage("img/return.png");
		returnIcon.resize(50, 50);
	}
	
	/**
	 *  Draw background picture and return button
	 */
	public void draw() {
		surface.image(background, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.image(returnIcon, 10, 10);
	}
	
	/**
	 * Go to the beginning screen when return button is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (returnButton.contains(p))
			surface.switchScreen(ScreenSwitcher.BEGINNING_SCREEN);
	}

}
