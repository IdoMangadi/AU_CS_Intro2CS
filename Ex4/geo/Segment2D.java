package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	//Fields: 
	Point2D _p1;
	Point2D _p2;
	
	//Constructor:
	public Segment2D (Point2D p1, Point2D p2) {
		this._p1 = p1;
		this._p2 = p2;
	}
	public Segment2D (Segment2D s2) {
		this._p1 = new Point2D(s2._p1);
		this._p2 = new Point2D(s2._p2);
	}
	
	//Methods: 
	/**
	 * The method computes if the distance between the two points of the segment id equal to the sum of the distances between ot and each
	 * of the two points.
	 */
	@Override
	public boolean contains(Point2D ot) {
		//Distance between p1, p2:
		double d = Math.sqrt( Math.pow(_p1.x()-_p2.x(),2) +Math.pow(_p1.y()-_p2.y(),2));
		//Distance between p1, ot:
		double a = Math.sqrt( Math.pow(_p1.x()-ot.x(),2) +Math.pow(_p1.y()-ot.y(),2));
		//Distance between ot, p2:
		double b = Math.sqrt( Math.pow(ot.x()-_p2.x(),2) +Math.pow(ot.y()-_p2.y(),2));
		
		if(Math.abs(d- (a+b)) > Ex4_Const.EPS1) {
			return false;
		}
		return true;
	}
	
	/**
	 * As written in the interface, the method should return 0 in case of segment (or point).
	 */
	@Override
	public double area() {
		return 0;
	}
	/**
	 * As written in the interface, the method should return twice the distance in case of segment.
	 */
	@Override
	public double perimeter() {
		double d = Math.sqrt( Math.pow(_p1.x()-_p2.x(),2) +Math.pow(_p1.y()-_p2.y(),2));
		return d*2;
	}
	/**
	 *This method uses the move method of Point2D for each point of the segment.
	 */
	@Override
	public void move(Point2D vec) {
		_p1.move(vec);
		_p2.move(vec);
	}

	@Override
	/**
	 *This method creates a new segment same as the previous one.
	 */
	public GeoShapeable copy() {
		return new Segment2D(this);
	}
	/**
	 *This method uses the scale method of Point2D for each point of the segment.
	 */
	@Override
	public void scale(Point2D center, double ratio) {
		this._p1.scale(center, ratio);
		this._p2.scale(center, ratio);	
	}
	@Override
	/**
	 *This method uses the rotate method of Point2D for each point of the segment.
	 */
	public void rotate(Point2D center, double angleDegrees) {
		this._p1.rotate(center, angleDegrees);
		this._p2.rotate(center, angleDegrees);
	}
	@Override
	/**
	 * This method creates a new array of points contains the two points of the segment and return it. 
	 */
	public Point2D[] getPoints() {
		Point2D[] res = {_p1, _p2};
		return res;
	}
	@Override
	public String toString() {
		return "Segment2D,"+_p1+","+_p2;
	}
	
}