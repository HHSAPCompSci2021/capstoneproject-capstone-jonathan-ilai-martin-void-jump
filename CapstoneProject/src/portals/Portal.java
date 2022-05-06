package portals;

import java.awt.geom.Rectangle2D;

import characters.Player;
import core.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Point;



public class Portal extends Rectangle2D.Double {

	
	
	public final double WIDTH = 70, HEIGHT = 100;
	public double x, y;
	private PImage image;
	private Rectangle2D.Double body;
	private boolean drawn;

	
	public Portal(PImage image, double x, double y, boolean drawn) {
		this.image = image;
		this.x = x;
		this.y = y;
		body = new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
		this.drawn = drawn;

	}
	
	public boolean getDrawn() {
		return drawn;
	}
	
	public void setDrawn(boolean drawn) {
		this.drawn = drawn;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public Rectangle2D.Double getPortal() {
		return body;
	}
	
	public void draw(PApplet surface) {
		if (image != null)
			surface.image(image, (float) x, (float) y, (float)WIDTH,(float)HEIGHT);
	}
	
	
	public boolean isInside(Player p) {
		
		Point center = new Point((int)(p.getX() + 35), (int)(p.getY() + 50));
		
		Rectangle2D.Double portalBody = this.getPortal();
		if (portalBody.contains(center)) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
