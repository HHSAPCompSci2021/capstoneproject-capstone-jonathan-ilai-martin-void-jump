package portals;

import processing.core.PApplet;

public class Line {

	
	
	//Fields
	
	
	float x1, y1, x2, y2;
	
//	float topRight1, topLeft1, bottomRight1, bottomLeft1, topRight2, topLeft2, bottomRight2, bottomLeft2;;
	
	

	//Constructors
	
	public Line (float mouseX, float mouseY, float mouseX2, float mouseY2) {
		
		x1 = mouseX;
		y1 = mouseY;
		x2 = mouseX2;
		y2 = mouseY2;
	}
	
	
	
	
	//Methods
	
	public void draw(PApplet drawingSurface) {
		
		drawingSurface.line(x1, y1, x2, y2);
	}
	
	
	public void setPoint2(float x2, float y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
		
	public float getIntersectionX(Line other) {
		
		float x = ( ((x1 * y2) - (y1 * x2)) * (other.x1 - other.x2) - (x1 - x2)*(other.x1 * other.y2 - other.y1 * other.x2))
				/ ( (x1 - x2)*(other.y1 - other.y2) - (y1 - y2)*(other.x1 - other.x2) );
		return x;
	}
	
	
	
	public float getIntersectionY(Line other) {
		
		float y = ( ( (x1 * y2) - (y1 * x2) ) * (other.y1 - other.y2) - (y1-y2) * (other.x1 * other.y2 - other.y1 * other.x2) )
				/ ( (x1 -x2) * (other.y1 - other.y2) - (y1 - y2)*(other.x1 - other.x2) );
		return y;
		
	}
	
	
	public boolean checkLocation(float intersection, float q, float p) {
		if( (q<intersection) && (intersection<p)) {
			return true;
		}
		if( (q>intersection) && (intersection>p)) {
			return true;
		}
		return false;
	}
	
	
	
	public boolean intersects(Line line) {
		boolean intersects = false;
		 
		if((checkLocation(getIntersectionX(line),x1,x2)) && (checkLocation(getIntersectionY(line),line.y1,line.y2)) ) {
			intersects = true;
		}
		return intersects;
	}
	
	
}
