package screens;


import java.awt.Point;
import java.awt.geom.Ellipse2D;

import core.DrawingSurface;
import processing.core.PImage;

public class Menu extends Screen {
	
	private PImage background, returnIcon;
	private Ellipse2D[] levelButtons;
	private Ellipse2D returnButton;


	public Menu(DrawingSurface surface) {
		super(800,600, surface);
		this.surface = surface;
		levelButtons = new Ellipse2D[12];
		returnButton = new Ellipse2D.Double(10, 10, 50, 50);
		addLevelButtonsToArray();
	}


	private void addLevelButtonsToArray() {
		levelButtons[0] = new Ellipse2D.Double(50, 300, 100, 100);
		levelButtons[1] = new Ellipse2D.Double(175, 300, 100, 100);
		levelButtons[2] = new Ellipse2D.Double(300, 300, 100, 100);
		levelButtons[3] = new Ellipse2D.Double(425, 300, 100, 100);
		levelButtons[4] = new Ellipse2D.Double(550, 300, 100, 100);
		levelButtons[5] = new Ellipse2D.Double(675, 300, 100, 100);
		levelButtons[6] = new Ellipse2D.Double(50, 450, 100, 100);
		levelButtons[7] = new Ellipse2D.Double(175, 450, 100, 100);
		levelButtons[8] = new Ellipse2D.Double(300, 450, 100, 100);
		levelButtons[9] = new Ellipse2D.Double(425, 450, 100, 100);
		levelButtons[10] = new Ellipse2D.Double(550, 450, 100, 100);
		levelButtons[11] = new Ellipse2D.Double(675, 450, 100, 100);
	}


	public void setup() {
		background = surface.loadImage("img/menuBackground.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		returnIcon = surface.loadImage("img/return.png");
		returnIcon.resize(50, 50);
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		// drawing stuff
		
		surface.background(255,255,255);
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
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (levelButtons[0].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL1);
		if (Level.isCompleted(0)) {
			if (levelButtons[1].contains(p))
				surface.switchScreen(ScreenSwitcher.LEVEL2);
			if (Level.isCompleted(1)) {
				if (levelButtons[2].contains(p))
					surface.switchScreen(ScreenSwitcher.LEVEL3);
			}
		}
		if (levelButtons[1].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL2);
		if (levelButtons[2].contains(p))
			surface.switchScreen(ScreenSwitcher.LEVEL3);
		
		if (returnButton.contains(p))
			surface.switchScreen(ScreenSwitcher.BEGINNING_SCREEN);
	}

	
}