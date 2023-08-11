package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class geoTest {
	
	public static final double EPS = Ex4_Const.EPS;
	
	@Test
	void pointTest() {
		//I implemented test for all the other "geo_shapes" and included inside tests for Point2D as well.
	}
	
	@Test
	void segmentTest() {
		Point2D p1 = new Point2D (1.0, 1.0);
		Point2D p2 = new Point2D (6.0, 6.0);
		Segment2D s1 = new Segment2D(p1, p2);
		
		//Contain:
		Point2D p3 = new Point2D (1.1, 1.1);
		Point2D p4 = new Point2D (3.1, 1.1);
		assertEquals(true, s1.contains(p3));
		assertEquals(false, s1.contains(p4));
		
		//Area:
		assertEquals(0, s1.area());
		
		//Perimeter:
		double dis = p1.distance(p2);
		assertEquals(dis*2, s1.perimeter(),EPS);
		
		//Move:
		Point2D vec = p3.vector(p4);
		s1.move(vec);
		assertEquals(false, s1.contains(p3));
		assertEquals(true, s1.contains(p4));
		
		//Deep copy:
		Segment2D s2 = (Segment2D) s1.copy();
		assertNotEquals(true, s1 == s2);
		
		//Scale:
		assertEquals(true, s1.contains(p4));
		s1.scale(p2, 0.9);
		assertEquals(false, s1.contains(p4));
		s1.scale(p2, 1.1);
		assertEquals(true, s1.contains(p4));
		
		//Rotate:
		s1.rotate(p2, 45);
		assertEquals(false, s1.contains(p4));
		p4 = new Point2D(8.0, 5.0);
		assertEquals(true, s1.contains(p4));
	}
	
	@Test
	void CircleTest() {
		Point2D p1 = new Point2D (5.5, 3.5);
		double rad = 1.5;
		Circle2D c1 = new Circle2D(p1, rad);
		
		//Contain:
		Point2D p2 = new Point2D (6.1, 3.6);
		Point2D p3 = new Point2D (1.2, 1.0);
		assertEquals(true, c1.contains(p2));
		assertEquals(false, c1.contains(p3));
		
		//Area:
		assertEquals(7.068583, c1.area(),EPS);
		assertNotEquals(7.0688, c1.area(),EPS);
		
		//Perimeter:
		assertEquals(9.424777, c1.perimeter(),EPS);
		assertNotEquals(9.4248, c1.perimeter(),EPS);
		
		//Move:
		Point2D vec = p1.vector(p3);
		c1.move(vec);
		assertEquals(false, c1.contains(p2));
		assertEquals(true, c1.contains(p3));
		
		//Deep copy:
		Circle2D c2 = (Circle2D) c1.copy();
		assertNotEquals(true, c1 == c2);
		
		//Scale:
		c1.scale(p2, 0.9);
		c2.scale(p2, 1.1);
		assertEquals(rad*0.9, c1.getRadius(), EPS);
		assertEquals(rad*1.1, c2.getRadius(), EPS);
	}
	
	@Test
	void triangleTest() {
		Point2D p1 = new Point2D (1.0, 1.0);
		Point2D p2 = new Point2D (6.0, 1.0);
		Point2D p3 = new Point2D (3.5, 7.0);
		Triangle2D t1 = new Triangle2D(p1, p2, p3);
		
		//Contain:
		Point2D p4 = new Point2D (3.5, 6.95);
		Point2D p5 = new Point2D (3.5, 0.9);
		assertEquals(true, t1.contains(p4));
		assertEquals(false, t1.contains(p5));
		
		//Area:
		assertEquals(15, t1.area(),EPS);
		
		//Perimeter:
		assertEquals(18, t1.perimeter(),EPS);
		
		//Move:
		Point2D vec = new Point2D(-0.1, -0.1);
		t1.move(vec);
		assertEquals(false, t1.contains(p4));
		assertEquals(true, t1.contains(p5));
		vec = new Point2D(0.1, 0.1);
		t1.move(vec);
		assertEquals(true, t1.contains(p4));
		assertEquals(false, t1.contains(p5));
		
		//Deep copy:
		Triangle2D t2 = (Triangle2D) t1.copy();
		assertNotEquals(true, t1 == t2);
		
		//Scale:
		p4 = new Point2D(3.5, 1.5);
		assertEquals(true, t1.contains(p4));
		t1.scale(p3,0.9);
		assertEquals(false, t1.contains(p4));
		assertEquals(false, t2.contains(p5));
		t2.scale(p3,1.1);
		assertEquals(true, t2.contains(p5));
		
		//Rotate:
		assertEquals(false, t1.contains(p4));
		t1.rotate(p3, 15);
		assertEquals(true, t1.contains(p4));
	}
	
	@Test
	void RectangleTest() {
		Point2D p1 = new Point2D (1.1, 6.1);
		Point2D p2 = new Point2D (6.1, 1.1);
		Rect2D r1 = new Rect2D(p1, p2);
		
		//Contain:
		Point2D p3 = new Point2D (6.0, 3.6);
		Point2D p4 = new Point2D (1.2, 1.0);
		assertEquals(true, r1.contains(p3));
		assertEquals(false, r1.contains(p4));
		
		//Area:
		assertEquals(25, r1.area(),EPS);
		assertNotEquals(25.1, r1.area(),EPS);
		
		//Perimeter:
		assertEquals(20, r1.perimeter(),EPS);
		assertNotEquals(21, r1.perimeter(),EPS);
		
		//Move:
		Point2D vec = new Point2D(-0.2, -0.2);
		r1.move(vec);
		assertEquals(false, r1.contains(p3));
		assertEquals(true, r1.contains(p4));
		
		//Deep copy:
		Rect2D r2 = (Rect2D) r1.copy();
		assertNotEquals(true, r1 == r2);
		
		//Scale:
		double preArea = r1.area();
		r1.scale(p1, 0.9);
		r2.scale(p1, 1.1);
		double newArea = preArea*0.9*0.9;
		assertEquals(newArea, r1.area(), EPS);
		newArea = preArea*1.1*1.1;
		assertEquals(newArea, r2.area(), EPS);
		
		//Rotate:
		r1.rotate(p1, 15);
		Point2D p5 = new Point2D(5.0, 6.2);
		Point2D p6 = new Point2D(5.8, 1.1);
		assertEquals(true, r1.contains(p5));
		assertEquals(false, r1.contains(p6));
	}
	
	@Test
	void PolygonTest() {
		Point2D p1 = new Point2D (2.0, 1.0);
		Point2D p2 = new Point2D (6.0, 1.0);
		Point2D p3 = new Point2D (7.0, 6.0);
		Point2D p4 = new Point2D (4.0, 8.0);
		Point2D p5 = new Point2D (1.0, 4.0);
		Polygon2D poly1 = new Polygon2D();
		poly1.add(p1);
		poly1.add(p2);
		poly1.add(p3);
		poly1.add(p4);
		poly1.add(p5);
		
		//Contain:
		Point2D p6 = new Point2D (4.0, 7.9);
		Point2D p7 = new Point2D (2.0, 0.9);
		assertEquals(true, poly1.contains(p6));
		assertEquals(false, poly1.contains(p7));
		
		//Area:
		assertEquals(29, poly1.area(),EPS);
		assertNotEquals(29.1, poly1.area(),EPS);
		
		//Perimeter:
		assertEquals(20.8668484492, poly1.perimeter(),EPS);
		assertNotEquals(20, poly1.perimeter(),EPS);
		
		//Move:
		Point2D vec = new Point2D(-1.0, -1.0);
		poly1.move(vec);
		assertEquals(false, poly1.contains(p6));
		assertEquals(true, poly1.contains(p7));
		Point2D vec2 = new Point2D(1.0, 1.0);
		poly1.move(vec2);
		assertEquals(true, poly1.contains(p6));
		assertEquals(false, poly1.contains(p7));
		
		//Deep copy:
		Polygon2D poly2 = (Polygon2D) poly1.copy();
		assertNotEquals(true, poly1 == poly2);
		
		//Scale:
		p7 = new Point2D (7.0,6.1);
		assertEquals(true, poly1.contains(p6));
		assertEquals(false, poly2.contains(p7));
		poly1.scale(p1, 0.9);
		poly2.scale(p1, 1.1);
		assertEquals(false, poly1.contains(p6));
		assertEquals(true, poly2.contains(p7));
		
		//Rotate:
		p6 = new Point2D(5.5, 1.0);
		assertEquals(true, poly1.contains(p6));
		poly1.rotate(p1, 15);
		assertEquals(false, poly1.contains(p6));
	}

}
