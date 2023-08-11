package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	
	// Fields representing the four points of the rect:
	private Point2D _pTL;    //Top left
	private Point2D _pTR;    //Top right
	private Point2D _pBL;    //Bottom left
	private Point2D _pBR;    //Bottom right
	
	//Constructors:
	public Rect2D(Point2D pTL, Point2D pTR, Point2D pBL, Point2D pBR) {
		this._pTL = pTL;
		this._pTR = pTR;
		this._pBL = pBL;
		this._pBR = pBR;
	}
	public Rect2D(Point2D pTL, Point2D pBR) {
		this._pTL = pTL;
		this._pTR = new Point2D(pBR.x(), pTL.y());
		this._pBL = new Point2D(pTL.x(), pBR.y());
		this._pBR = pBR;
	}
	public Rect2D(Rect2D r1) {
		this._pTL = new Point2D(r1._pTL);
		this._pTR = new Point2D(r1._pTR);
		this._pBL = new Point2D(r1._pBL);
		this._pBR = new Point2D(r1._pBR);
	}
	
	/**
	 *The method checks if the area of all the rectangle is equal to the area of all the 4 triangles that occurred with the point.
	 */
	@Override
	public boolean contains(Point2D ot) {
		//Dividing the rectangle to 4 triangles:
		Triangle2D t1 = new Triangle2D(ot, _pTL, _pBL);
		Triangle2D t2 = new Triangle2D(ot, _pTL, _pTR);
		Triangle2D t3 = new Triangle2D(ot, _pTR, _pBR);
		Triangle2D t4 = new Triangle2D(ot, _pBL, _pBR);
		//Checking if the area of the 4 occurred triangles is equal to the area of all the rectangle:
		if(Math.abs(this.area() -(t1.area() +t2.area() +t3.area() +t4.area())) > Ex4_Const.EPS1) {
			return false;
		}
		return true;
	}
	
	/**
	 * The method returns the area of the rectangle by simple calculating.  
	 */
	@Override
	public double area() {
		double hight = Math.sqrt( Math.pow(_pTL.x()-_pBL.x(), 2) + Math.pow(_pTL.y()-_pBL.y(), 2) );
		double width = Math.sqrt( Math.pow(_pTL.x()-_pTR.x(), 2) + Math.pow(_pTL.y()-_pTR.y(), 2) );
		return hight * width;
	}
	/**
	 * The method returns the perimeter of the rectangle by simple calculating.  
	 */
	@Override
	public double perimeter() {
		double hight = Math.sqrt( Math.pow(_pTL.x()-_pBL.x(), 2) + Math.pow(_pTL.y()-_pBL.y(), 2) );
		double width = Math.sqrt( Math.pow(_pTL.x()-_pTR.x(), 2) + Math.pow(_pTL.y()-_pTR.y(), 2) );
		double peri = hight*2 + width*2;
		return peri;
	}
	
	/**
	 *This method uses the move method of Point2D for each point of the rectangle.
	 */
	@Override
	public void move(Point2D vec) {
		_pTL.move(vec);
		_pTR.move(vec);
		_pBL.move(vec);
		_pBR.move(vec);
	}

	@Override 
	public GeoShapeable copy() {
		return new Rect2D(this);
	}
	/**
	 * This method uses the scale method of Point2D for each point of the rectangle.
	 */
	@Override
	public void scale(Point2D center, double ratio) {
		this._pTL.scale(center, ratio);
		this._pTR.scale(center, ratio);
		this._pBL.scale(center, ratio);
		this._pBR.scale(center, ratio);
	}
	/**
	 * This method uses the rotate method of Point2D for each point of the rectangle.
	 */
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		this._pTL.rotate(center, angleDegrees);
		this._pTR.rotate(center, angleDegrees);
		this._pBL.rotate(center, angleDegrees);
		this._pBR.rotate(center, angleDegrees);
	}
	/**
	 * The method returns the points that are bottom left and top right in case the rectangle is balanced.
	 * in other case, it returns 2 counter points.
	 */
	@Override
	public Point2D[] getPoints() {
		Point2D[] res = { _pBL, _pTR};
		return res;
	}
	@Override
	public String toString() {
		return "Rect2D,"+_pTL+","+_pTR+","+_pBL+","+_pBR;
	}
	
	//More methods I built:
	public Point2D getCenter() {
		double cenX = (_pBL.x() + _pTR.x())/2;
		double cenY = (_pBR.y() + _pTL.y())/2;
		return new Point2D(cenX , cenY);
	}
	public double getHight() {
		double hight = Math.sqrt( Math.pow(_pTL.x()-_pBL.x(), 2) + Math.pow(_pTL.y()-_pBL.y(), 2) );
		return hight;
	}
	public double getWidth() {
		double width = Math.sqrt( Math.pow(_pTL.x()-_pTR.x(), 2) + Math.pow(_pTL.y()-_pTR.y(), 2) ); 
		return width;
	}
	public Point2D[] get4Points() {
		Point2D[] res = {_pTR, _pTL, _pBL, _pBR};
		return res;
	}

}
