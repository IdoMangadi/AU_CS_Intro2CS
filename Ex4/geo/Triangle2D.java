package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	
	//Fields:
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	
	//Constructor:
	public Triangle2D (Point2D p1, Point2D p2, Point2D p3) {
		this._p1 = p1;
		this._p2 = p2;
		this._p3 = p3;
	}
	public Triangle2D(Triangle2D t2) {
		this._p1 = new Point2D(t2._p1);
		this._p2 = new Point2D(t2._p2);
		this._p3 = new Point2D(t2._p3);
	}
	
	//Methods:
	/**
	 * The method checks if a point is in a triangle by calculating the area of the three triangles that the point made.
	 */
	@Override
	public boolean contains(Point2D ot) {
		Triangle2D t1 = new Triangle2D(ot, _p1, _p2); 
		Triangle2D t2 = new Triangle2D(ot, _p2, _p3);
		Triangle2D t3 = new Triangle2D(ot, _p3, _p1);
		if(Math.abs(this.area() -(t1.area() +t2.area() +t3.area())) > Ex4_Const.EPS1) {
			return false;
		}
		return true;
	}
	/**
	 * Using 'Heron' formula to find the area of a triangle:
	 */
	@Override
	public double area() {
		//Length of the edges:
		double a = Math.sqrt( Math.pow(_p1.x()-_p2.x(),2) + Math.pow(_p1.y()-_p2.y(),2) );
		double b = Math.sqrt( Math.pow(_p2.x()-_p3.x(),2) + Math.pow(_p2.y()-_p3.y(),2) );
		double c = Math.sqrt( Math.pow(_p3.x()-_p1.x(),2) + Math.pow(_p3.y()-_p1.y(),2) );
		//Half of the perimeter:
		double s = (a+b+c) /2;
		//Using 'Heron' formula:
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	@Override
	/**
	 * The method returns the perimeter of the rectangle by simple calculating.  
	 */
	public double perimeter() {
		double a = Math.sqrt( Math.pow(_p1.x()-_p2.x(), 2) + Math.pow(_p1.y()-_p2.y(), 2) );
		double b = Math.sqrt( Math.pow(_p2.x()-_p3.x(), 2) + Math.pow(_p2.y()-_p3.y(), 2) );
		double c = Math.sqrt( Math.pow(_p3.x()-_p1.x(), 2) + Math.pow(_p3.y()-_p1.y(), 2) );
		return a+b+c;
	}

	@Override
	/**
	 *This method uses the move method of Point2D for each point of the triangle.
	 */
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	/**
	 *This method creates a new triangle same as the previous one.
	 */
	public GeoShapeable copy() {
		return new Triangle2D(this);
	}

	@Override
	/**
	 *This method uses the scale method of Point2D for each point of the triangle.
	 */
	public void scale(Point2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);
		this._p3.scale(center, ratio);
	}
	@Override
	/**
	 *This method uses the rotate method of Point2D for each point of the triangle.
	 */
	public void rotate(Point2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
		this._p3.rotate(center, angleDegrees);
	}
	@Override
	/**
	 * This method creates a new array of points contains the three points of the triangle and return it. 
	 */
	public Point2D[] getPoints() {
		Point2D[] res = {_p1, _p2, _p3};
		return res;
	}
	@Override
	public String toString() {
		return "Triangle2D,"+_p1+","+_p2+","+_p3;
	}
	
}
