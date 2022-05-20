package screens;

import core.DrawingSurface;

/**
 * This class represents a general screen that can appear on the drawing surface
 * @author Ilai Tamari
 */
public abstract class Screen {

	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	protected DrawingSurface surface;
	
	/**
	 * Create a new screen with givens width and height
	 * @param width		Width of screen
	 * @param height	Height of screen
	 * @param surface	Surface on which screen is drawn
	 */
	public Screen(int width, int height, DrawingSurface surface) {
		this.surface = surface;
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	abstract public void setup();
	
	abstract public void draw();
	
	abstract public void mousePressed();	
	
}
