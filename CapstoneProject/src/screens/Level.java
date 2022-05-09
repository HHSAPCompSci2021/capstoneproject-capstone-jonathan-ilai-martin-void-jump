package screens;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


import characters.Monster;
import characters.Person;
import characters.Player;
import core.DrawingSurface;
import ilaitm12.shapes.Line;
//import ilaitm12.shapes.Line;
import platforms.Platform;
//import portals.LineIlai;
import portals.Portal;
import platforms.Spikes;
import platforms.Wall;
import processing.core.PImage;



public class Level extends Screen {
	
	 protected double startX, startY, keyX, keyY, gateX, gateY;
	 protected ArrayList<Platform> platforms;
	 protected PImage returnIcon, gate, key, dungeon;
	 protected Ellipse2D returnButton, noPortalZone;
	 private Player player;
	 private Monster monster;
	 private int level;
	 private boolean keyTaken, canPortal;
	 private Portal[] portals;
	 private PImage p1, p2;
	 private static boolean[] completed;
	 private ArrayList<Person> characters;
	 
	 public Level(int level, DrawingSurface surface) {
		 super(800, 600, surface);
		 this.level = level;
		 platforms = new ArrayList<Platform>();
		 returnButton = new Ellipse2D.Double(10, 10, 50, 50);
		 completed = new boolean[12];
		 characters = new ArrayList<Person>();
	 }
	 
	 private void initializeLevel() {
		if (level == 1) {
			startX = 50;
			startY = 200;
			keyX = 200;
			keyY = 300;
			gateX = 600;
			gateY = 400;
		} else if (level == 2) {
			startX = 600;
			startY = 100;
			keyX = 100;
			keyY = 100;
			gateX = 600;
			gateY = 400;
		} else if (level == 3) {
			startX = 50;
			startY = 100;
			keyX = 400;
			keyY = 100;
			gateX = 600;
			gateY = 400;
			monster = new Monster(surface.loadImage("img/zombie.png"), 350, 150, true);
			 characters.add(monster);
		}
	}
	 
	public void reset() {
		keyTaken = false;
		for (Person character : characters) 
			character.reset();
		player.moveTo(startX, startY - player.HEIGHT);
		for (Portal portal : portals) portal.setDrawn(false);
	}

	public void setup() {
		initializeLevel();
		player = new Player(surface.loadImage("img/Wizard.png"), startX, startY - player.HEIGHT);
		characters.add(player);
		returnIcon = surface.loadImage("img/return.png");
		returnIcon.resize(50, 50);
		dungeon = surface.loadImage("img/dungeon.jpg");
		dungeon.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		gate = surface.loadImage("img/gate.png");
		gate.resize(100, 100); 
		key = surface.loadImage("img/key.png");
		key.resize(50, 50);
		addPlatforms();
		p1 = surface.loadImage("img/portalIn.png");
		p2 = surface.loadImage("img/portalOut.png");
		portals = new Portal[2];
		portals[0] = new Portal(p1, 0, 0, false);
		portals[1] = new Portal(p2, 0, 0, false);
		double centerX = gateX + gate.width / 2;
		double centerY = gateY + gate.height / 2;
		noPortalZone = new Ellipse2D.Double(centerX - 150, centerY - 150, 300, 300);
	 }

	 protected void addPlatforms() {
		 PImage platform = surface.loadImage("img/platform.png");
		 PImage wall = surface.loadImage("img/wall.png");
		 PImage spikes = surface.loadImage("img/spikes.png");
		 spikes.resize(500, 70);
			platform.resize(100, 30);
		 if (level == 1) {
			platforms.add(new Platform(platform, startX, startY, 100, 30));
			platforms.add(new Platform(platform, gateX - gate.width / 2, gateY + gate.height, gate.width * 2, 30));
		} else if (level == 2) {
			platforms.add(new Platform(platform, startX, startY, 100, 30));
			platforms.add(new Platform(platform, gateX - gate.width / 2, gateY + gate.height, gate.width * 2, 30));
			platforms.add(new Platform(platform, keyX - 50, keyY + 50, 150, 30));
		}
		else if(level == 3) {
			platforms.add(new Platform(platform, startX, startY, 50, 30));
			platforms.add(new Wall(wall, 200, 0, 30, 200));
			platforms.add(new Platform(platform, 150, 400, 50, 30));
			platforms.add(new Platform(platform, 300, 250, 200, 30));
			platforms.add(new Platform(platform, gateX - gate.width / 2, gateY + gate.height, gate.width * 2, 30));
			

			
			
		}
		 for (int i = 0 ;i < 4 ; i++) {
				platforms.add(new Spikes(spikes, i * 250, DRAWING_HEIGHT - 70, 250, 70));
			}
	 }
		
	 public void draw() {
		surface.background(225);
		surface.image(dungeon, 0, 0);
		surface.image(returnIcon, 10, 10);
		surface.image(gate, (int) gateX, (int) gateY);
		surface.noFill();
		surface.stroke(255);
		surface.strokeWeight(6);
		surface.circle((float) noPortalZone.getCenterX(), (float) noPortalZone.getCenterY(), (float) noPortalZone.getWidth());
		if (!keyTaken) 
			surface.image(key, (int) keyX, (int) keyY);
		 player.draw(surface);
		if(monster != null) {
			monster.draw(surface);
			monster.act(platforms);
			 	
		}
		 for (Platform platform : platforms) {
			 if (platform != null) platform.draw(surface);
			 if (platform instanceof Spikes) {
				 for (Person character : characters) {
					 if (platform.getPlatform().contains(new Point((int) (character.x + character.width), (int) (character.y + character.height)))) {
						 character.disappear();
						 if (character instanceof Player) reset();
					 }
				 }
				 
			 }
		 }
		 if (!keyTaken && (player.contains(new Point((int) keyX, (int) keyY))
				 || player.contains(new Point((int) (keyX + key.width), (int) keyY))
				 || player.contains(new Point((int) (keyX), (int) keyY + key.height))
				 || player.contains(new Point((int) (keyX + key.width), (int) keyY + key.height)))) {
			 keyTaken = true;
			 player.takeKey();
		 }
		 if (keyTaken && (player.contains(new Point((int) (gateX + gate.width / 2), (int) (gateY + gate.height / 2))))) {
			 surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			 completed[level - 1] = true;
			 reset();
		 }
		 
		 if (!player.touchingWall(platforms)) {
			 if (surface.isPressed(KeyEvent.VK_LEFT))
				player.walk(-1);
			 if (surface.isPressed(KeyEvent.VK_RIGHT))
				 player.walk(1);
		 }
		
		 if (surface.isPressed(KeyEvent.VK_UP))
				player.jump(platforms);
		 player.act(platforms);
		
		 for (Portal portal : portals) {
			 Point center = new Point((int)(portal.getX() + portal.getWidth()/2),(int) (portal.getY() + portal.getHeight()/2));
			 if (portal.getDrawn() && !returnButton.contains(center) && !noPortalZone.contains(center)) 
				 portal.draw(surface);
		 }
			 
		 if (!canPortal) canPortal = canPortal();
			
		 for (int i = 0 ; i < portals.length ; i++) {
			 Portal portal = portals[i];
			 Portal other = i == 0 ? portals[1] : portals[0];
			 for (Person character : characters) {
//				 Portal portal = portals[i];
//				 Portal other = i == 0 ? portals[1] : portals[0];
				 if (character != null && portal.getDrawn() && other.getDrawn() && portal.isInside(character) && canPortal) {
					 character.moveTo(other.getCenterX() - character.width / 2, other.getCenterY() - character.height / 2);
					 canPortal = false;
					 break;
				 }
			 }
			 
		 }
	}
	 
	private boolean canPortal() {
		for (Portal portal : portals) {
			for (Person character : characters) {
				if (character != null && portal.intersects(character)) return false;
			}
		}
		return true;
	}

	public void mousePressed() {
			
		
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (returnButton.contains(p)) {
			reset();
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
		
			
		boolean draw = true;
		Line sight = new Line((float) (player.getX() + 35), (float)(player.getY() + 50), (float)(surface.mouseX),(float)(surface.mouseY));
		
		if (surface.mouseButton == surface.LEFT) {
			
			
			for(Platform platform : platforms) {
				float x1 = (float) platform.getX();
				float x2 = (float)(platform.getX() + platform.getWidth());
				float y1 = (float)(platform.getY());
				float y2 = (float)(platform.getY() + platform.getHeight());

				Line l1 = new Line(x1, y1, x1, y2);
				Line l2 = new Line(x1, y1, x2, y1);
				Line l3 = new Line(x2, y1, x2, y2);
				Line l4 = new Line(x1, y2, x2, y2);
				
				
//				l1.draw(surface);
//				l2.draw(surface);
//				l3.draw(surface);
//				l4.draw(surface);
				
				
				if(l1.intersects(sight) || l2.intersects(sight) || l3.intersects(sight) || l4.intersects(sight)) {
					draw = false;
				}
				
				if (!draw) {
			
					if (l1.intersects(sight)) sight.setPoint2((int) l1.getIntersectionX(sight), (int) l1.getIntersectionY(sight));
					else if (l2.intersects(sight)) sight.setPoint2((int) l2.getIntersectionX(sight), (int) l2.getIntersectionY(sight));
					else if (l3.intersects(sight)) sight.setPoint2((int) l3.getIntersectionX(sight), (int) l3.getIntersectionY(sight));
					else if (l4.intersects(sight)) sight.setPoint2((int) l4.getIntersectionX(sight), (int) l4.getIntersectionY(sight));
				}
			}
			
			surface.stroke(255,165,0);
			sight.draw(surface);
			
			if(draw) {
				
				portals[0].setX(surface.mouseX - portals[0].getWidth()/2);
				portals[0].setY(surface.mouseY - portals[0].getHeight()/2);
				portals[0].setDrawn(draw);
			}
			
			
			
			
			
		}
	 
		if (surface.mouseButton == surface.RIGHT) {
			
			for(Platform platform : platforms) {
				float x1 = (float)platform.getX();
				float x2 = (float)(platform.getX() + platform.getWidth());
				float y1 = (float)(platform.getY());
				float y2 = (float)(platform.getY() + platform.getHeight());
				
				Line l1 = new Line(x1, y1, x1, y2);
				Line l2 = new Line(x1, y1, x2, y1);
				Line l3 = new Line(x2, y1, x2, y2);
				Line l4 = new Line(x1, y2, x2, y2);

//				l1.draw(surface);
//				l2.draw(surface);
//				l3.draw(surface);
//				l4.draw(surface);
				
				
				if(l1.intersects(sight) || l2.intersects(sight) || l3.intersects(sight) || l4.intersects(sight)) {
					draw = false;
				}
				
				if (!draw) {
					if (l1.intersects(sight)) sight.setPoint2((int) l1.getIntersectionX(sight), (int) l1.getIntersectionY(sight));
					else if (l2.intersects(sight)) sight.setPoint2((int) l2.getIntersectionX(sight), (int) l2.getIntersectionY(sight));
					else if (l3.intersects(sight)) sight.setPoint2((int) l3.getIntersectionX(sight), (int) l3.getIntersectionY(sight));
					else if (l4.intersects(sight)) sight.setPoint2((int) l4.getIntersectionX(sight), (int) l4.getIntersectionY(sight));
				}
				
			}
			
			surface.stroke(173, 216, 230);
			sight.draw(surface);
			
			if(draw) {

				portals[1].setX(surface.mouseX - portals[1].getWidth()/2);
				portals[1].setY(surface.mouseY - portals[1].getHeight()/2);
				portals[1].setDrawn(draw);
			}
			
			
			
			
		}
	 }

	 public static boolean isCompleted(int level) {
		 return completed[level];
	 }
	 


}
