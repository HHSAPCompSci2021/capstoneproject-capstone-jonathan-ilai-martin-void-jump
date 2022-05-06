package screens;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import characters.Player;
import core.DrawingSurface;
import platforms.Platform;
import portals.Portal;
import processing.core.PApplet;
import platforms.SpikePlatform;
import processing.core.PImage;
import processing.event.MouseEvent;
import levi.shapes.Line;
import levi.shapes.Rectangle;


public class Level extends Screen {
	
	 protected double startX, startY, keyX, keyY, gateX, gateY;
	 protected ArrayList<Platform> platforms;
	 protected PImage returnIcon, gate, key;
	 protected Ellipse2D returnButton;
	 private Player player;
	 private int level;
	 private boolean keyTaken;
	 private Portal portal1, portal2;
	 private PImage p1, p2;
	 
	 public Level(int level, DrawingSurface surface) {
		 super(800, 600, surface);
		 this.level = level;
		 platforms = new ArrayList<Platform>();
		 returnButton = new Ellipse2D.Double(10, 10, 50, 50);
		 portal1 = new Portal(p1, 0, 0, false);
		 portal2 = new Portal(p2, 0, 0, false);
	 }
	 
	 private void initializeLocations() {
		if (level == 1) {
			startX = 50;
			startY = 200;
			keyX = 200;
			keyY = 200;
			gateX = 600;
			gateY = 400;
		}
	}

	public void setup() {
		initializeLocations();
		player = new Player(surface.loadImage("img/Wizard.png"), startX, startY - player.HEIGHT);
		returnIcon = surface.loadImage("img/return.png");
		returnIcon.resize(50, 50);
		gate = surface.loadImage("img/gate.png");
		gate.resize(100, 100); 
		key = surface.loadImage("img/key.png");
		key.resize(50, 50);
		addPlatforms();
		p1 = surface.loadImage("img/portalIn.png");
		p2 = surface.loadImage("img/portalOut.png");
		
	 }

	 protected void addPlatforms() {
		 if (level == 1) {
				PImage platform = surface.loadImage("img/platform.png");
				platform.resize(100, 30);
				platforms.add(new Platform(platform, startX, startY, 150, 30));
				platforms.add(new Platform(platform, gateX - gate.width / 2, gateY + gate.height, gate.width * 2, 30));
				platforms.add(new SpikePlatform(platform, 0, 560, 1000, 30));
			}
	 }
		
	 public void draw() {
		 surface.background(0);
		 surface.image(returnIcon, 10, 10);
		 surface.image(gate, (int) gateX, (int) gateY);
		 if (!keyTaken) surface.image(key, (int) keyX, (int) keyY);
		 player.draw(surface);
		 for (Platform platform : platforms) {
			 if (platform != null) platform.draw(surface);
		 }
		 if (player.contains(new Point((int) keyX, (int) keyY))) {
			 keyTaken = true;
			 player.takeKey();
		 }
		 if (surface.isPressed(KeyEvent.VK_LEFT))
			player.walk(-1);
		 if (surface.isPressed(KeyEvent.VK_RIGHT))
			player.walk(1);
		 if (surface.isPressed(KeyEvent.VK_UP))
				player.jump();
		 player.act(platforms);
		
		 if(portal1.getDrawn()) {
			portal1.draw(surface); 
		 }
		 if(portal2.getDrawn()) {
			 portal2.draw(surface);
		 }
		 
		 
	}
	 
	 public void mousePressed() {
			
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (returnButton.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			
		if (surface.mouseButton == surface.LEFT) {
			boolean draw = true;
			Line sight = new Line((float)(surface.mouseX + 35),(float)(surface.mouseY + 50), (float) (player.getX() + 35), (float)(player.getY() + 50));
			
			for(Platform platform : platforms) {
				float x1 = (float)platform.getX();
				float x2 = (float)(platform.getX() + platform.getHeight());
				float y1 = (float)platform.getY();
				float y2 = (float)(platform.getY() + platform.getHeight());
				
				Line l1 = new Line(x1, y1, x1, y2);
				Line l2 = new Line(x1, y1, x2, y1);
				Line l3 = new Line(x2, y1, x2, y2);
				Line l4 = new Line(x1, y2, x2, y2);

				
				if(sight.intersects(l1) || sight.intersects(l2) || sight.intersects(l3) || sight.intersects(l4)) {
					draw = false;
				}
			}
			portal1.setDrawn(draw);
			portal1.setX(surface.mouseX);
			portal1.setY(surface.mouseY);
		}
	 
		if (surface.mouseButton == surface.RIGHT) {
			boolean draw = true;
			Line sight = new Line((float)(surface.mouseX + 35),(float)(surface.mouseY + 50), (float) (player.getX() + 35), (float)(player.getY() + 50));
			
			for(Platform platform : platforms) {
				float x1 = (float)platform.getX();
				float x2 = (float)(platform.getX() + platform.getHeight());
				float y1 = (float)platform.getY();
				float y2 = (float)(platform.getY() + platform.getHeight());
				
				Line l1 = new Line(x1, y1, x1, y2);
				Line l2 = new Line(x1, y1, x2, y1);
				Line l3 = new Line(x2, y1, x2, y2);
				Line l4 = new Line(x1, y2, x2, y2);

				
				if(sight.intersects(l1) || sight.intersects(l2) || sight.intersects(l3) || sight.intersects(l4)) {
					draw = false;
				}
			}
			portal2.setDrawn(draw);
			portal2.setX(surface.mouseX);
			portal2.setY(surface.mouseY);
		}
	 }

	 


}
