package screens;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import core.DrawingSurface;
import processing.core.PImage;

/**
 * This class represents the menu screen where the player can choose what level to play
 * @author Ilai Tamari
 */
public class Menu extends Screen {
	
	private PImage background, returnIcon;
	private Ellipse2D[] levelButtons;
	private Ellipse2D returnButton;

	/**
	 * Construct a new menu screens with buttons
	 * @param surface	Surface on which screen is drawn
	 */
	public Menu(DrawingSurface surface) {
		super(800,600, surface);
		levelButtons = new Ellipse2D[10];
		returnButton = new Ellipse2D.Double(10, 10, 50, 50);
		addLevelButtonsToArray();
	}


	private void addLevelButtonsToArray() {
		levelButtons[0] = new Ellipse2D.Double(100, 300, 100, 100);
		levelButtons[1] = new Ellipse2D.Double(225, 300, 100, 100);
		levelButtons[2] = new Ellipse2D.Double(350, 300, 100, 100);
		levelButtons[3] = new Ellipse2D.Double(475, 300, 100, 100);
		levelButtons[4] = new Ellipse2D.Double(600, 300, 100, 100);
		levelButtons[5] = new Ellipse2D.Double(100, 450, 100, 100);
		levelButtons[6] = new Ellipse2D.Double(225, 450, 100, 100);
		levelButtons[7] = new Ellipse2D.Double(350, 450, 100, 100);
		levelButtons[8] = new Ellipse2D.Double(475, 450, 100, 100);
		levelButtons[9] = new Ellipse2D.Double(600, 450, 100, 100);
	}

	/**
	 * Set up background picture and return button icon
	 */
	public void setup() {
		background = surface.loadImage("img/menuBackground.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		returnIcon = surface.loadImage("img/return.png");
		returnIcon.resize(50, 50);
	}

	/**
	 * Draw background and buttons
	 */
	public void draw() {
		surface.image(background, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.image(returnIcon, 10, 10);
		surface.textFont(surface.createFont("Skia-Regular_Bold", 80));
		for (int i = 0 ; i < levelButtons.length ; i++) {
			if (Level.isCompleted(i)) surface.fill(0, 255, 0);
			else surface.fill(255, 255, 0);
			surface.circle((float) levelButtons[i].getCenterX(), 
					(float) levelButtons[i].getCenterY(), 
					(float) levelButtons[i].getWidth());
			surface.fill(0);
			float coX = i + 1 < 10 ? 9f / 32 : 1f / 8;
			surface.text(i+1, (float) (levelButtons[i].getX() + levelButtons[i].getWidth() * coX), 
					(float) (levelButtons[i].getY() + levelButtons[i].getHeight() / 16 * 11));
		}
	}
	
	/**
	 * Go to the level that corresponds to each button when a button is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (levelButtons[0].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL1);
		if (levelButtons[1].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL2);
		if (levelButtons[2].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL3);
		if (levelButtons[3].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL4);
		if(levelButtons[4].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL5);
		if(levelButtons[5].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL6);
		if(levelButtons[6].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL7);
		if(levelButtons[7].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL8);
		if(levelButtons[8].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL9);
		if(levelButtons[9].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL10);
		
		if (returnButton.contains(p))
			surface.switchScreen(ScreenSwitcher.BEGINNING_SCREEN);
	}

	
}