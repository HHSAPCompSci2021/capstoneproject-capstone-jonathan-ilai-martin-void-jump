package characters;

import java.awt.Color;

import ilaitm12.shapes.Line;
import processing.core.PApplet;

public class Lazer extends Line {

	public Lazer(double x, double y, double angle, double length) {
		super(x, y, x + length * Math.cos(angle * Math.PI / 180), y - length * Math.sin(angle * Math.PI / 180));
		this.setColor(Color.WHITE, Color.WHITE);
		this.setStrokeWeight(6);
	}
	
//	public void draw(PApplet surface) {
//		surface.strokeWeight(6);
//		super.draw(surface);
//	}
}
