package core;


import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.Menu;
import screens.Beginning;
import screens.Instructions;
import screens.Level;
import screens.Screen;
import screens.ScreenSwitcher;

public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;

	
	public DrawingSurface() {
		
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		
		
		Beginning screen1 = new Beginning(this);
		screens.add(screen1);
		
		Menu screen2 = new Menu(this);
		screens.add(screen2);
		
		Level level1 = new Level(1, this);
		screens.add(level1);
		
		Level level2 = new Level(2, this);
		screens.add(level2);
		
		Level level3 = new Level(3, this);
		screens.add(level3);
		
		Level level4 = new Level(4, this);
		screens.add(level4);
		
		Level level5 = new Level(5, this);
		screens.add(level5);
		
		Instructions instructions = new Instructions(this);
		screens.add(instructions);
		
		Level level6 = new Level(6, this);
		screens.add(level6);
		
		Level level7 = new Level(7, this);
		screens.add(level7);
		
		Level level8 = new Level(8, this);
		screens.add(level8);
		
		Level level9 = new Level(9, this);
		screens.add(level9);
		
		Level level10 = new Level(10, this);
		screens.add(level10);
		
		activeScreen = screens.get(0);
	}
	
	public void setup() {
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		pop();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
		if (key == ESC)  // This prevents a processing program from closing on escape key
			key = 0;
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
		
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}

}
