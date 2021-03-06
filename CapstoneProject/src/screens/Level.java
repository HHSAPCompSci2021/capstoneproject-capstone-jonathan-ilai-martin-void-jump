package screens;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import characters.Lazer;
import characters.Monster;
import characters.Person;
import characters.Player;
import characters.Teleporter;
import core.DrawingSurface;
import ilaitm12.shapes.Line;
import platforms.BoostPlatform;
import platforms.FallingPlatform;
import platforms.ForceBarrier;
import platforms.Platform;
import portals.Portal;
import platforms.Spikes;
import platforms.Wall;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * This class is a general class for all levels. Some of the methods in this class are made for specific 
 * levels.
 * @author Ilai Tamari
 */
public class Level extends Screen {
	
	 private double startX, startY, keyX, keyY, gateX, gateY, clock;
	 private ArrayList<Platform> platforms;
	 private PImage returnIcon, gate, key, dungeon, lazerIcon, clouds, teleporter;
	 private Ellipse2D returnButton;
	 private ArrayList<Ellipse2D> noPortalZone;
	 private Player player;
	 private int level;
	 private boolean keyTaken, canPortal;
	 private Portal[] portals;
	 private PImage p1, p2, platform, wall, spikes, zombie, wizard, rightBoostPlatform, leftBoostPlatform;
	 private static boolean[] completed;
	 private ArrayList<Person> characters;
	 private ArrayList<Lazer> lazers;
	 
	 /**
	  * Create a new level using level number
	  * @param level	Level number
	  * @param surface	Surface on which level is drawn
	  */
	 public Level(int level, DrawingSurface surface) {
		 super(800, 600, surface);
		 this.level = level;
		 platforms = new ArrayList<Platform>();
		 returnButton = new Ellipse2D.Double(10, 10, 50, 50);
		 completed = new boolean[12];
		 characters = new ArrayList<Person>();
		 lazers = new ArrayList<Lazer>();
		 noPortalZone = new ArrayList<Ellipse2D>();
		 clock = 0;
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
			Monster monster = new Monster(zombie, 350, 150, true);
			characters.add(monster);
			lazers.add(new Lazer(lazerIcon, 10, 200, 200));
		} else if(level == 4) {
			startX = 350;
			startY = 100;
			keyX = 375;
			keyY = 300;
			gateX = 350;
			gateY = 400;
			Monster monster = new Monster(zombie, 250, 250, true);
			Monster monster1 = new Monster(zombie, 400, 250, true);
			characters.add(monster);
			characters.add(monster1);
			lazers.add(new Lazer(lazerIcon, 0, startY + 250, startX - 300));
			lazers.add(new Lazer(lazerIcon, startX + 250, startY + 250, 500));
		} else if(level == 5) {
			startX = 50;
			startY = 100;
			keyX = 450;
			keyY = 475;
			gateX = 690;
			gateY = 300;
			Monster monster = new Monster(zombie, 300, 220, true);
			characters.add(monster);
		} else if (level == 6) {
			startX = 10;
			startY = 450;
			keyX = 40;
			keyY = 50;
			gateX = 700;
			gateY = 300;
			lazers.add(new Lazer(lazerIcon, 450, 415, 250));
			Monster monster = new Monster(zombie, 300, 300, true);
			characters.add(monster);
		} else if (level == 7) {
			startX = 50;
			startY = 300;
			keyX = 375;
			keyY = 150;
			gateX = 650;
			gateY = 200;
			
		} else if (level == 8) {
			startX = 150;
			startY = 100;
			keyX = 260;
			keyY = 225;
			gateX = 600;
			gateY = 400;
			Monster monster1 = new Monster(zombie, 520, 20, true);
			Monster monster2 = new Monster(zombie, 200, 150, true);
			characters.add(monster1);
			characters.add(monster2);
		} else if (level == 9) {
			startX = 400;
			startY = 250;
			keyX = 100;
			keyY = 350;
			gateX = 700;
			gateY = 150;
			Monster monster = new Monster(zombie, keyX, 350, true);
			characters.add(monster);
		} else if(level == 10) {
			startX = 400;
			startY = 100;
			keyX = 400;
			keyY = 300;
			gateX = 373;
			gateY = 415;
			Teleporter t = new Teleporter(teleporter, 0, 0, true, 220, 150);
			Teleporter t1 = new Teleporter(teleporter, 560, 0, true, 220, 150);
			characters.add(t);
			characters.add(t1);
			lazers.add(new Lazer(lazerIcon, 260, 230, 295));
		}
	}
	 
	/**
	 * Return characters to their original location and reset platforms and portals
	 */
	public void reset() {
		keyTaken = false;
		for (Person character : characters) 
			character.reset();
		for (Portal portal : portals) portal.setDrawn(false);
		platforms = new ArrayList<Platform>();
		addPlatforms();
	}

	/**
	 * Set up levels, images, and add players and portals
	 */
	public void setup() {
		loadImages();
		initializeLevel();
		player = new Player(wizard, startX, startY - Player.HEIGHT);
		characters.add(player);
		addPlatforms();
		
		portals = new Portal[2];
		portals[0] = new Portal(p1, 0, 0, false);
		portals[1] = new Portal(p2, 0, 0, false);
		double centerX = gateX + gate.width / 2;
		double centerY = gateY + gate.height / 2;
		noPortalZone.add(new Ellipse2D.Double(centerX - 150, centerY - 150, 300, 300));
		noPortalZone.add(new Ellipse2D.Double(player.x - 50, player.y - 50, player.width * 2, player.height * 2));
	 }

	 private void loadImages() {
		 platform = surface.loadImage("img/platform.png");
		 wall = surface.loadImage("img/wall.png");
		 spikes = surface.loadImage("img/spikes.png");
		 zombie = surface.loadImage("img/zombie.png");
		 wizard = surface.loadImage("img/Wizard.png");
		 p1 = surface.loadImage("img/portalIn.png");
		 p2 = surface.loadImage("img/portalOut.png");
		 lazerIcon = surface.loadImage("img/lazer.png");
		 clouds = surface.loadImage("img/clouds.png");
		 teleporter = surface.loadImage("img/teleporter.gif");
		 rightBoostPlatform = surface.loadImage("img/rightBoostPlatform.png");
		 leftBoostPlatform = surface.loadImage("img/leftBoostPlatform.png");
		 returnIcon = surface.loadImage("img/return.png");
		 returnIcon.resize(50, 50);
		
		
		leftBoostPlatform.resize(100, 300);
		 
		 dungeon = surface.loadImage("img/dungeon.jpg");
		 dungeon.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
		 
		 gate = surface.loadImage("img/gate.png");
		 gate.resize(100, 100); 
		 
		 key = surface.loadImage("img/key.png");
		 key.resize(50, 50);
		 
	}

	private void addPlatforms() {
		platform.resize(100, 30);
		spikes.resize(500, 70);
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
			platforms.add(new Platform(platform, startX - 125, startY + 250, 170, 30));
			platforms.add(new Platform(platform, startX + 50, startY + 250, 200, 30));
			platforms.add(new Platform(platform, startX - 350, startY + 400, 850, 30));
			platforms.add(new ForceBarrier(clouds, startX - 50, -10, 50, 150));
			platforms.add(new ForceBarrier(clouds, startX + 100, -10, 50, 150));

		} else if(level == 5) {
			platforms.add(new Platform(platform, startX, startY, 50, 30));
			platforms.add(new Wall(wall, 337, 0, 30, 180));
			platforms.add(new Wall(wall, 337, 360, 30, 400));
			platforms.add(new Platform(platform, 250, 165, 215, 30));
			platforms.add(new Platform(platform, 250, 355, 180, 30));
			platforms.add(new Platform(platform, gateX - gate.width / 2, gateY + gate.height, gate.width * 2, 30));
			platforms.add(new ForceBarrier(clouds, 200, 165, 50, 200));
		} else if (level == 6) {
			platforms.add(new Platform(platform, startX, startY, 200, 30));
			platforms.add(new ForceBarrier(clouds, 200, 10, 50, 200));
			platforms.add(new ForceBarrier(clouds, 200, 210, 50, 200));
			platforms.add(new Platform(platform, 250, 400, 200, 30));
			platforms.add(new Platform(platform, 700, 400, 100, 30));
		}
		
	
		else if(level == 7) {
			platforms.add(new Platform(platform, startX, startY, 50, 30));
			platforms.add(new BoostPlatform(rightBoostPlatform, 150, 300, 150, 30, true));
			platforms.add(new ForceBarrier(clouds, 300, 10, 50, 400));
			platforms.add(new BoostPlatform(leftBoostPlatform, 300, 300, 200, 30, false));
			platforms.add(new Platform(platform, 650, 300, 100, 30));
		 }

		else if(level == 8) {
			platforms.add(new Platform(platform, startX, startY, 150, 30));
			platforms.add(new BoostPlatform(rightBoostPlatform, 300, startY, 160, 30, true));
			platforms.add(new Platform(platform, 460, startY, 40, 30));
			platforms.add(new ForceBarrier(clouds, 350, 0, 50, 100));
			platforms.add(new ForceBarrier(clouds, 495, 100, 50, 200));
			platforms.add(new Platform(platform, 500, 130, 200, 30));
			platforms.add(new Platform(platform, 500, 300, 200, 30));
			platforms.add(new BoostPlatform(leftBoostPlatform, 355, 300, 145, 30, false));
			platforms.add(new Platform(platform, 170, 300, 180, 30));
			platforms.add(new Wall(wall, 140, 130, 30, 200));
			platforms.add(new Platform(platform, 400, gateY + gate.height, 400, 30));
			platforms.add(new Wall(wall, 400, 330, 30, 200));
			
		} else if (level == 9) {
			platforms.add(new Platform(platform, startX - 50, startY, 150, 30));
			platforms.add(new Platform(platform, 100, 450, 200, 30));
			platforms.add(new Wall(wall, startX - 50, startY - 100, 30, 100));
			platforms.add(new Wall(wall, startX + 100, startY - player.height * 2, 30, player.height * 2 + 30));
			platforms.add(new BoostPlatform(rightBoostPlatform, startX - 225, startY - 100, 100, 30, true));
			platforms.add(new FallingPlatform(platform, 350, 450, 100, 30));
			platforms.add(new FallingPlatform(platform, 500, 450, 100, 30));
			platforms.add(new FallingPlatform(platform, 650, 450, 100, 30));
			platforms.add(new Platform(platform, gateX - gate.width / 2, gateY + gate.height, 200, 30));
		}
		else if(level == 10) {
			platforms.add(new FallingPlatform(platform, 300, startY, 235, 30));
			platforms.add(new ForceBarrier(clouds, 260, 0, 50, 250));
			platforms.add(new ForceBarrier(clouds, 500, 0, 50, 250));
			platforms.add(new Platform(platform, 0, 230, 220, 30));
			platforms.add(new Platform(platform, 585, 230, 220, 30));
			platforms.add(new Platform(platform, 260, 500, 295, 30));


		}
		 

		for (int i = 0 ;i < 4 ; i++) {
			platforms.add(new Spikes(spikes, i * 250, DRAWING_HEIGHT - 70, 250, 70));
		}
	}
		
	/**
	 * Draw items and characters and manage the flow of them game including killing characters and reacting 
	 * to platforms and portals
	 */
	public void draw() {
        // Draw items
		clock++;
		surface.background(225);
		surface.image(dungeon, 0, 0);
		surface.image(returnIcon, 10, 10);
		surface.image(gate, (int) gateX, (int) gateY);
		for (Lazer lazer : lazers)
			if (lazer != null) lazer.draw(surface);
		surface.noFill();
		surface.stroke(255);
		surface.strokeWeight(6);
		for (Ellipse2D zone : noPortalZone) {
			surface.circle((float) zone.getCenterX(), (float) zone.getCenterY(), (float) zone.getWidth());
		}
		if (!keyTaken) surface.image(key, (int) keyX, (int) keyY);
		
		// Draw characters and kill player if possible
		for (Person character : characters) {
			character.draw(surface);
			if (!(character instanceof Teleporter)) {
				character.act(platforms);
			}
			if (character instanceof Monster) {
				Monster monster = (Monster) character;
				if (monster.isDrawn() && monster.kill(player))
					reset();
				if(monster instanceof Teleporter) {
					if(clock % 25 == 0) {
						Teleporter t = (Teleporter) monster;
						t.act();
					}
				}
			}
		}
		
		// Kill player if touching lazer
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
		
		// React to special platforms
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
			 if(platform instanceof BoostPlatform && player.standing(platforms) != null && player.standing(platforms).equals(platform)) {
				BoostPlatform boostPlatform = (BoostPlatform) platform;
				boostPlatform.boost(player);
			}
			if(platform instanceof FallingPlatform && player.standing(platforms) != null && player.standing(platforms).equals(platform)) {
				FallingPlatform fallingPlatform = (FallingPlatform) platform;
				fallingPlatform.fall(player);
			}
			 
		 }
		 
		 // Level-specific features
		 if (level == 6 && keyTaken && platforms.size() == 9) {
			 platforms.add(new Platform(platform, 10, 200, 100, 30));
		 }
//		 if (level == 8 && keyTaken && !(platforms.get(7) instanceof FallingPlatform)) {
		 if (level == 8 && keyTaken) {
			 //System.out.println(platforms.size() + ", " + platforms.indexOf(characters.get(0).standing(platforms)));
//			 platforms.set(7, new FallingPlatform(platform, 170, 300, 180, 30));
			 platforms.set(7, new BoostPlatform(rightBoostPlatform, 355, 300, 145, 30, true));
		 }
		 if (level == 9 && !keyTaken && characters.get(0).isDrawn()) {
			 keyX = characters.get(0).getX();
			 keyY = characters.get(0).getY();
		 }
		 
		 // Take key
		 if (!keyTaken && (player.contains(new Point((int) keyX, (int) keyY))
				 || player.contains(new Point((int) (keyX + key.width), (int) keyY))
				 || player.contains(new Point((int) (keyX), (int) keyY + key.height))
				 || player.contains(new Point((int) (keyX + key.width), (int) keyY + key.height)))) {
			 keyTaken = true;
			 player.takeKey();
		 }
		 // Complete level by opening gate with key
		 if (keyTaken && (player.contains(new Point((int) (gateX + gate.width / 2), (int) (gateY + gate.height / 2))))) {
			 surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
			 completed[level - 1] = true;
			 reset();
		 }
		 
		 // React to keys if not inside walls
		 if (!player.touchingWall(platforms) && !(player.standing(platforms) instanceof BoostPlatform)) {
			 if (surface.isPressed(KeyEvent.VK_LEFT))
				player.walk(-1);
			 if (surface.isPressed(KeyEvent.VK_RIGHT))
				 player.walk(1);
		 } else {
			 while(player.touchingWall(platforms)) {
				 if (player.getXSpeed() > 0) player.moveTo(player.getX() - 1, player.getY());
				 else player.moveTo(player.getX() + 1, player.getY());
			 }
		 }
		 if (surface.isPressed(KeyEvent.VK_UP))
				player.jump(platforms);
		
		 // Take care of portals
		 for (int i = 0 ; i < portals.length ; i++) {
			 Portal portal = portals[i];
			 Point center = new Point((int)(portal.getX() + portal.getWidth()/2),(int) (portal.getY() + portal.getHeight()/2));
			 if (portal.getDrawn() && !returnButton.contains(center) && !noPortalZone.get(0).contains(center)) 
				 portal.draw(surface);
			 Portal other = i == 0 ? portals[1] : portals[0];
			 for (Person character : characters) {
				 if (character != null && portal.getDrawn() && other.getDrawn() && portal.isInside(character) && canPortal) {
					 character.moveTo(other.getCenterX() - character.width / 2, other.getCenterY() - character.height / 2);
					 canPortal = false;
					 break;
				 }
			 }	 
		 }
		 
		 noPortalZone.set(1, new Ellipse2D.Double(player.x - 50, player.y - 50, player.width * 2, player.height * 2));
		 if (!canPortal) canPortal = canPortal();
	}
	 
	private boolean canPortal() {
		for (Portal portal : portals) {
			for (Person character : characters) {
				if (character != null && portal.intersects(character)) return false;
			}
		}
		return true;
	}

	/**
	 * Go to the manu screen when return button is pressed. Also, add portals when clicking on the screen,
	 * but only where portals can be drawn
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (returnButton.contains(p)) {
			reset();
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		}
		
		Point upRight = new Point((int) (surface.mouseX - Portal.WIDTH / 5), (int) (surface.mouseY - Portal.WIDTH / 5));
		Point upLeft = new Point((int) (surface.mouseX + Portal.WIDTH / 5), (int) (surface.mouseY - Portal.WIDTH / 5));
		Point downRight = new Point((int) (surface.mouseX - Portal.WIDTH / 5), (int) (surface.mouseY + Portal.WIDTH / 5));
		Point downLeft = new Point((int) (surface.mouseX + Portal.WIDTH / 5), (int) (surface.mouseY + Portal.WIDTH / 5));
		for (Platform platform : platforms) {
			Rectangle2D body = platform.getPlatform();
			if (body.contains(upLeft) || body.contains(upRight) || body.contains(downLeft) || body.contains(downRight))
				return;
		}
			
		
		if (!noPortalZone.get(1).contains(new Point(surface.mouseX, surface.mouseY))) {
			boolean draw = true;
			Line sight = new Line((float) (player.getX() + 35), (float)(player.getY() + 50), (float)(surface.mouseX),(float)(surface.mouseY));
			
			if (surface.mouseButton == PConstants.LEFT) {
				
				
				for(Platform platform : platforms) {
					float x1 = (float) platform.getX();
					float x2 = (float)(platform.getX() + platform.getWidth());
					float y1 = (float)(platform.getY());
					float y2 = (float)(platform.getY() + platform.getHeight());

					Line l1 = new Line(x1, y1, x1, y2);
					Line l2 = new Line(x1, y1, x2, y1);
					Line l3 = new Line(x2, y1, x2, y2);
					Line l4 = new Line(x1, y2, x2, y2);
					
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
		 
			if (surface.mouseButton == PConstants.RIGHT) {
				
				for(Platform platform : platforms) {
					float x1 = (float)platform.getX();
					float x2 = (float)(platform.getX() + platform.getWidth());
					float y1 = (float)(platform.getY());
					float y2 = (float)(platform.getY() + platform.getHeight());
					
					Line l1 = new Line(x1, y1, x1, y2);
					Line l2 = new Line(x1, y1, x2, y1);
					Line l3 = new Line(x2, y1, x2, y2);
					Line l4 = new Line(x1, y2, x2, y2);
					
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
	 }

	/**
	 * Check if a level has been completed
	 * @param level	Level number
	 * @return true if the level has been completed
	 */
	 public static boolean isCompleted(int level) {
		 return completed[level];
	 }
}
