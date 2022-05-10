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
		this.setColor(Color.WHITE, Color.WHITE);
		this.setStrokeWeight(6);
	}
	
	public void draw(PApplet surface) {
		surface.rotate((float) angle);
		surface.image(icon, 0, 0);
	}
}
