package characters;

import java.awt.Color;

import ilaitm12.shapes.Line;
import processing.core.PApplet;
import processing.core.PImage;

public class Lazer extends Line {
	
	private PImage icon;
	private double angle;

	public Lazer(PImage image, double x, double y, double angle, double length) {
		super(x, y, x + length * Math.cos(angle * Math.PI / 180), y - length * Math.sin(angle * Math.PI / 180));
		icon = image;
		this.angle = angle;
	}
	
	public Lazer(PImage image, Line line) {
		super(line.getX(), line.getY(), line.getX2(), line.getY2());
		icon = image;
	}
	
	public void draw(PApplet surface) {
		//surface.rotate((float) angle);
		surface.image(icon, (float) getX(), (float) getY());
		//surface.rotate((float) -angle);
		//System.out.println(intersects(new Line(30, 30, 30, 230)));
	}
	
	public Lazer breakLine(double x, double y) {
		Line first = new Line(getX(), getY(), x, y);
		double length = getPerimeter() - first.getPerimeter();
		return new Lazer(icon, constructLineFromAngle(x, y, angle, length));
	}
}
