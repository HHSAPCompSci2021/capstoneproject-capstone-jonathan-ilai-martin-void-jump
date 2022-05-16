package screens;



import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import core.DrawingSurface;
import processing.core.PImage;


public class Beginning extends Screen {
	
	private PImage background;
	private Rectangle start, instructions;

	public Beginning(DrawingSurface surface) {
		super(800, 600, surface);
		this.surface = surface;
		start = new Rectangle(DRAWING_WIDTH / 4 - 150, DRAWING_HEIGHT * 3 / 4 - 50, 300, 100);
		instructions = new Rectangle(DRAWING_WIDTH / 3 * 2 - 100, DRAWING_HEIGHT * 3 / 4 - 50, 300, 100);
	}
	
	public void setup() {
		background = surface.loadImage("img/background.png");
		background.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
	}

	public void draw() {
		surface.background(255,255,255);
		surface.image(background, 0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		surface.fill(255, 255, 0);
		surface.rect(start.x, start.y, start.width, start.height, 10, 10, 10, 10);
		surface.rect(instructions.x, instructions.y, instructions.width, instructions.height, 10, 10, 10, 10);
		surface.fill(0);
		
		surface.textFont(surface.createFont("SignPainter", 85));
		String str = "START";
		float w = surface.textWidth(str);
		surface.text(str, start.x+start.width/2-w/2, start.y+start.height/4 * 3);
		
		surface.textFont(surface.createFont("SignPainter", 60));
		str = "HOW TO PLAY";
		w = surface.textWidth(str);
		surface.text(str, instructions.x+instructions.width/2-w/2, instructions.y + instructions.height/8 * 5);
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (start.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		if (instructions.contains(p))
			surface.switchScreen(ScreenSwitcher.INSTRUCTIONS);
	}
	




	

}

