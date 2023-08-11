package Exe.Ex4.geo;

import java.util.ArrayList;

import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	
	//Fields:
	private ArrayList<Point2D> _polygon;
	
	//Constructor:
	public Polygon2D () {
		this._polygon = new ArrayList<>();
	}
	public void add(Point2D p) {
		this._polygon.add(p);
	}
	
	
	@Override
	/**
	 * The method checks if a point is in the polygon or not.
	 * The method takes the horizontal line "y=c" ( c represent the number that this line crossed the y axis)
	 * and checks how many lines of the polygon this line crossed.
	 * After that the method collects all the x values that the line crosses the polygon's lines
	 * and checks how many is from the right of the ot.x,
	 * if this number is odd then it means that the point was in the polygon.
	 */
	public boolean contains(Point2D ot) {
		
		Point2D p1;
		Point2D p2;
		ArrayList<Double> xs = new ArrayList<>();
		
		//Collecting all the x's values that the horizontal line "y = ot.y" is cutting the line between p1 to p2 that they are at.
		for(int i=0; i<_polygon.size()-1; i++) {
			p1 = this._polygon.get(i);
			p2 = this._polygon.get(i+1);
			if( y_Between_ys(ot.y(), p1, p2)) {
				xs.add(cuttedX(p1, p2, ot.y()));
			}
		}
		//Handling the line between the last point and the first one:
		if(_polygon.size() > 2) {
			p1 = this._polygon.get(_polygon.size()-1);
			p2 = this._polygon.get(0);
			if( y_Between_ys(ot.y(), p1, p2)) {
				xs.add(cuttedX(p1, p2, ot.y()));
			}
		}
		
		int counterRight = 0;
		//Checking how many cutting points there are from the right side of "ot".
		for(int i=0; i<xs.size(); i++) {
			if(xs.get(i) > ot.x()) {
				counterRight++;
			}
		}
		
		if( counterRight%2 ==0) {
			return false;
		}
		return true;
	}

	@Override
	/**
	 * This method using the "Shoelace formula" to compute the area of the polygon.
	 */
	public double area() {
		int len = _polygon.size(), j=0;
		double area = 0;
		
		for (int i=0; i<len; i++) {
			j = (i+1) % len;
			area = area + (_polygon.get(i).x() * _polygon.get(j).y());
			area = area - (_polygon.get(j).x() * _polygon.get(i).y());
		}
		
		area = Math.abs(area) /2;
		return area;
	}
	
	@Override
	/**
	 * This method computes the perimeter of the polygon.
	 */
	public double perimeter() {
		double perimeter = 0;
		
		for(int i=0; i<_polygon.size(); i++) {
			if (i<_polygon.size()-1) {
				perimeter = perimeter + _polygon.get(i).distance(_polygon.get(i+1));
			}
			else {
				perimeter = perimeter + _polygon.get(i).distance(_polygon.get(0));
			}
		}
		return perimeter;
	}

	@Override
	/**
	 * This method move every point of the polygon by a given vector.
	 * The method simply using the 'move' method of Point2D
	 */
	public void move(Point2D vec) {
		for(int i=0; i<_polygon.size(); i++) {
			_polygon.get(i).move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		Polygon2D polygon2 = new Polygon2D();
		for(int i=0; i<this._polygon.size(); i++) {
			polygon2.add(new Point2D (this._polygon.get(i)));
		}
		return polygon2;
	}

	@Override
	/**
	 * This method simply scaling every point of the polygon by the 'scale' method of Point2. 
	 */
	public void scale(Point2D center, double ratio) {
		for(int i=0; i<this._polygon.size(); i++) {
			this._polygon.get(i).scale(center, ratio);
		}
	}

	@Override
	/**
	 * This method simply rotating every point of the polygon by the 'rotate' method of Point2. 
	 */
	public void rotate(Point2D center, double angleDegrees) {
		for(int i=0; i<this._polygon.size(); i++) {
			this._polygon.get(i).rotate(center, angleDegrees);
		}
	}

	@Override
	/**
	 * The method returns an array that contains all the points of the polygon by order. 
	 */
	public Point2D[] getPoints() {
		Point2D[] res = new Point2D[this._polygon.size()];
		for(int i=0; i<this._polygon.size(); i++) {
			res[i] = this._polygon.get(i);
		}
		return res;
	}
	@Override
	public String toString() {
		String res = "Polygon2D";
		for(int i=0; i<this._polygon.size(); i++) {
			res = res +"," + this._polygon.get(i).toString();
		}
		return res;
	}
	/**
	 * This function giving true if the value y is between the y values of two given points
	 * @param y - the checks y
	 * @param p1 - point 1.
	 * @param p2 - point 2
	 * @return whether y is between p1.y and p2.y or not.
	 */
	private boolean y_Between_ys(double y, Point2D p1, Point2D p2) {
		if( (y > p1.y() && y > p2.y()) || (y < p1.y() && y < p2.y()) ) {
			return false;
		}
		return true;
	}
	/**
	 * The function gets 2 points and a y coordinate, 
	 * calculating the x value in which the horizontal line in y height cutting the line between p1 to p2.
	 * Note: the function assumed that the value of y is somewhere between p1.y to p2.y !
	 * @param p1 - the first point 
	 * @param p2 - the second point
	 * @param y - the height value of the horizontal line. 
	 * @return double that represent the value of the x where cut the line between p1 to p2.
	 */
	private double cuttedX (Point2D p1, Point2D p2, double y) {
		//Slope:
		double m = (p1.y()- p2.y()) / (p1.x()- p2.x());
		//The b of "y = mx +b" .
		double b = p1.y() - m*p1.x();
		//The needed x value:
		double x = (y-b) / m;
		return x;
	}
	
}
