package screens;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import characters.Lazer;
import characters.Monster;
import characters.Person;
import characters.Player;
import core.DrawingSurface;
import ilaitm12.shapes.Line;
import ilaitm12.shapes.Rectangle;
import platforms.ForceBarrier;
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
	 protected PImage returnIcon, gate, key, dungeon, lazerIcon;
	 protected Ellipse2D returnButton, noPortalZone;
	 private Player player;
	 private int level;
	 private boolean keyTaken, canPortal;
	 private Portal[] portals;
	 private PImage p1, p2;
	 private static boolean[] completed;
	 private ArrayList<Person> characters;
	 private ArrayList<Lazer> lazers;
	 
	 public Level(int level, DrawingSurface surface) {
		 super(800, 600, surface);
		 this.level = level;
		 platforms = new ArrayList<Platform>();
		 returnButton = new Ellipse2D.Double(10, 10, 50, 50);
		 completed = new boolean[12];
		 characters = new ArrayList<Person>();
		 lazers = new ArrayList<Lazer>();
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
			Monster monster = new Monster(surface.loadImage("img/zombie.png"), 350, 150, true);
			characters.add(monster);
			lazerIcon = surface.loadImage("img/lazer.png");
			lazers.add(new Lazer(lazerIcon, 10, 200, 0, 400));
		} else if(level == 4) {
			startX = 350;
			startY = 100;
			keyX = 375;
			keyY = 300;
			gateX = 350;
			gateY = 400;
			Monster monster = new Monster(surface.loadImage("img/zombie.png"), 125, 250, true);
			Monster monster1 = new Monster(surface.loadImage("img/zombie.png"), 550, 250, true);
			characters.add(monster);
			characters.add(monster1);
			
		}
		
	}
	 
	public void reset() {
		keyTaken = false;
		for (Person character : characters) 
			character.reset();
		player.moveTo(startX, startY - player.HEIGHT);

		for(Person p: characters) {
			if(p instanceof Monster) {
				Monster m = (Monster) p;
				m.moveTo(m.getStartX(), m.getStartY());
			}
		}
		for (Portal portal : portals) portal.setDrawn(false);
	}

	public void setup() {
		initializeLevel();
		player = new Player(surface.loadImage("img/Wizard.png"), startX, startY - player.HEIGHT);
		characters.add(player);
		returnIcon = surface.loadImage("img/return.png");
		lazerIcon = surface.loadImage("img/lazer.png");
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
		else if(level == 4) {
			platforms.add(new Platform(platform, startX, startY, 100, 30));
			platforms.add(new Platform(platform, startX - 175, startY + 250, 100, 30));
			platforms.add(new Platform(platform, startX + 150, startY + 250, 100, 30));
			platforms.add(new Platform(platform, startX - 75, startY + 400, 250, 30));
			platforms.add(new ForceBarrier(platform, startX - 75, -10, 30, 150));
		//	platforms.add(new ForceBarrier(platform, startX + 150, -10, 30, 150));
			platforms.add(new ForceBarrier(platform, startX + 140, -10, 30, 150));
			platforms.add(new ForceBarrier(platform, startX + 150, -10, 30, 150));
			platforms.add(new ForceBarrier(platform, startX + 150, -10, 30, 150));

		} else if(level == 5) {
			platforms.add(new Platform(platform, startX, startY, 50, 30));
			platforms.add(new Wall(wall, 337, 0, 30, 275));
			platforms.add(new Wall(wall, 337, 425, 30, 300));
			platforms.add(new Platform(platform, 250, 260, 215, 30));
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
		for (Lazer lazer : lazers)
			if (lazer != null) lazer.draw(surface);
		surface.noFill();
		surface.stroke(255);
		surface.strokeWeight(6);
		surface.circle((float) noPortalZone.getCenterX(), (float) noPortalZone.getCenterY(), (float) noPortalZone.getWidth());

		for (Person character : characters) {
			if(character instanceof Monster) {
				Monster monster = (Monster) character;
				monster.draw(surface);
				monster.act(platforms);
				if (monster.kill(player))
					reset();
			}
		}
		
		if (!keyTaken) 
			surface.image(key, (int) keyX, (int) keyY);
		 player.draw(surface);


	
		
		double x1 = player.x;
		double x2 = player.x + player.width;
		double y1 = player.y;
		double y2 = player.y + player.height;
		Line down = new Line(x1, y2, x2, y2);
		Line left = new Line(x1, y1, x1, y2);
		Line up = new Line(x1, y1, x2, y1);
		Line right = new Line(x2, y1, x2, y2);
		for (Lazer lazer : lazers) {
			if (lazer != null) {
				if (right.intersects(lazer) || left.intersects(lazer) || up.intersects(lazer) || down.intersects(lazer)) {
					reset();
				}
			}
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
//			 for (int j = 0 ; j < lazers.size() ; j++) {
//				 Lazer lazer = lazers.get(j);
//				 if (lazer != null && portal.getDrawn() && other.getDrawn()) {
//					 Rectangle portalBody = new Rectangle(portal.getX(), portal.getY(), portal.width, portal.height);
//					 if (lazer.intersects(portalBody)) {
//						 //System.out.println("Yes");
//						 Lazer second = ((Lazer) lazer).breakLine(portal.getCenterX(), portal.getCenterY());
//						 double secondX = lazer.getX() > portal.getX() ? portal.getMaxX() + 10 : portal.getMinX() - 10;
//						 //double secondY = lazer.getY() > portal.getY() ? portal.getMaxY() + 10 : portal.getMinY() - 10;
//						 double firstX = lazer.getX() < portal.getX() ? other.getMaxX() + 10 : other.getMinX() - 10;
//						 //double firstY = lazer.getY() > portal.getY() ? other.getMaxY() + 10 : other.getMinY() - 10;
//						 System.out.println("Before: " + lazer.getX2());
//						 lazer.setPoint2((int) secondX, (int) portal.getCenterY());
//						 System.out.println("After: " + lazers.get(j).getX2());
//						 //lazers.remove(j);
//						 second.setPoint(firstX, other.getCenterY());
//						 //lazers.add(new Lazer(lazerIcon, lazer.getX(), lazer.getY(), 0, lazer.getX2() - lazer.getX()));
//						 lazers.add(second);
//					 }
//				 }
//			 }
			 
			 
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
			if (!returnButton.contains(new Point(surface.mouseX, surface.mouseY))) sight.draw(surface);
			
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
