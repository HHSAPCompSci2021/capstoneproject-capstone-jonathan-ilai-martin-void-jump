package screens;



import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import core.DrawingSurface;
import processing.core.PImage;


public class Beginning extends Screen {
	
	private PImage background;
	private Rectangle button;

	public Beginning(DrawingSurface surface) {
		super(800, 600, surface);
		this.surface = surface;
		button = new Rectangle(DRAWING_WIDTH / 2 - 150, DRAWING_HEIGHT * 3 / 4 - 50, 300, 100);
	}
	
	public void setup() {
		background = surface.loadImage("img/background.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}

	public void draw() {
		surface.background(255,255,255);
		surface.image(background, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(255, 255, 0);
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		surface.textFont(surface.createFont("SignPainter", 85));
		String str = "START";
		//surface.textSize(50);
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/4 * 3);
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	




	

}

