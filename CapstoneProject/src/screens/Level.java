package screens;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import core.DrawingSurface;
import platforms.Platform;
import processing.core.PImage;

public abstract class Level extends Screen {
	
	 protected double startX, startY, keyX, keyY, gateX, gateY;
	 protected Platform[] platforms;
	 protected PImage returnIcon;
	 protected Ellipse2D returnButton;
	 
	 
	 public Level(DrawingSurface surface, double startX, double startY, double keyX, double keyY, double gateX1, double gateX2) {
		 super(800, 600, surface);
		 this.startX = startX;
		 this.startY = startY;
		 this.keyX = keyX;
		 this.keyY = keyY;
		 this.gateX = gateX;
		 this.gateY = gateY;
		 platforms = new Platform[10];
		 returnButton = new Ellipse2D.Double(10, 10, 50, 50);
	 }
	 
	 public void setup() {
		 returnIcon = surface.loadImage("img/return.png");
			returnIcon.resize(50, 50);
		addPlatforms();
	 }

	 protected abstract void addPlatforms();
		
	 public void draw() {
		 surface.background(0);
		 surface.image(returnIcon, 10, 10);
		 for (Platform platform : platforms) {
			 if (platform != null) platform.draw(surface);
		 }
	}
	 
	 public void mousePressed() {
			Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
			if (returnButton.contains(p))
				surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}

}
