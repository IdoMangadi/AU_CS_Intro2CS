package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;


class ShapeCollectionTest {
	public static final double EPS = Ex4_Const.EPS;
	
	/**
	 * I've combined all the tests in one test unit ahead to make it simpler.
	 * NOTE: all the required test are here.  
	 */
	@Test
	void testAdd_AddAt_copy() {
		ShapeCollectionable sc1 = new  ShapeCollection();
		
		//Testing add:
		Point2D t1 = new Point2D (1.0, 1.0);
		Point2D t2 = new Point2D (6.0, 1.0);
		Point2D t3 = new Point2D (3.5, 7.0);
		GeoShapeable gt = new Triangle2D(t1, t2, t3);
		Color col = new Color(255);
		GUI_Shapeable gs1t = new GUIShape(gt, true, col, 1);
		GUI_Shapeable gs2t = gs1t.copy();
		gs2t.setTag(2);
		sc1.add(gs1t);
		assertEquals(1, sc1.size());
		sc1.add(gs2t);
		assertEquals(2, sc1.size());
		//Testing if it is the same pointer:
		assertEquals(true, sc1.get(1)== gs2t);
		//Testing deep copy:
		assertEquals(false, sc1.get(1) == gs1t);
		
		
		//Testing addAt:
		Point2D s1 = new Point2D (1.0, 1.0);
		Point2D s2 = new Point2D (6.0, 1.0);
		GeoShapeable g3 = new Segment2D(s1, s2);
		GUI_Shapeable gs2s = new GUIShape(g3, false, col, 2);
		sc1.addAt(gs2s, 1);
		
		assertEquals(3, sc1.size());
		//Testing if gs3 is between gs1 and gs2:
		assertEquals(true, sc1.get(1)== gs2s);
		sc1.removeElementAt(2);
		
		//Testing sort:
		//Creating the GeoShapes:
		Point2D c1 = new Point2D (5.5, 3.5);
		double rad = 1.5;
		Circle2D gc = new Circle2D(c1, rad);
		
		Point2D r1 = new Point2D (1.1, 6.1);
		Point2D r2 = new Point2D (6.1, 1.1);
		Rect2D gr = new Rect2D(r1, r2);
		
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
		
		GUI_Shapeable gs3c = new GUIShape(gc, false, col, 3);
		GUI_Shapeable gs4r = new GUIShape(gr, false, col, 4);
		GUI_Shapeable gs5p = new GUIShape(poly1, false, col, 5);
		
		sc1.add(gs3c);
		sc1.add(gs4r);
		sc1.add(gs5p);
		
		Comparator<GUI_Shapeable> comp = new ShapeComp(Ex4_Const.Sort_By_Area);
		sc1.sort(comp);
		assertEquals(true, sc1.get(0) == gs2s);
		assertEquals(true, sc1.get(1) == gs3c);
		assertEquals(true, sc1.get(2) == gs1t);
		assertEquals(true, sc1.get(3) == gs4r);
		assertEquals(true, sc1.get(4) == gs5p);
		
		comp = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
		sc1.sort(comp);
		assertEquals(true, sc1.get(4) == gs2s);
		assertEquals(true, sc1.get(3) == gs3c);
		assertEquals(true, sc1.get(2) == gs1t);
		assertEquals(true, sc1.get(1) == gs4r);
		assertEquals(true, sc1.get(0) == gs5p);
		
		comp = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
		sc1.sort(comp);
		assertEquals(true, sc1.get(0) == gs3c);
		assertEquals(true, sc1.get(1) == gs2s);
		assertEquals(true, sc1.get(2) == gs1t);
		assertEquals(true, sc1.get(3) == gs4r);
		assertEquals(true, sc1.get(4) == gs5p);

		comp = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
		sc1.sort(comp);
		assertEquals(true, sc1.get(4) == gs3c);
		assertEquals(true, sc1.get(3) == gs2s);
		assertEquals(true, sc1.get(2) == gs1t);
		assertEquals(true, sc1.get(1) == gs4r);
		assertEquals(true, sc1.get(0) == gs5p);

		comp = new ShapeComp(Ex4_Const.Sort_By_toString);
		sc1.sort(comp);
		assertEquals(true, sc1.get(4) == gs3c);
		assertEquals(true, sc1.get(3) == gs5p);
		assertEquals(true, sc1.get(2) == gs4r);
		assertEquals(true, sc1.get(1) == gs2s);
		assertEquals(true, sc1.get(0) == gs1t);

		comp = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
		sc1.sort(comp);
		assertEquals(true, sc1.get(0) == gs3c);
		assertEquals(true, sc1.get(1) == gs5p);
		assertEquals(true, sc1.get(2) == gs4r);
		assertEquals(true, sc1.get(3) == gs2s);
		assertEquals(true, sc1.get(4) == gs1t);

		comp = new ShapeComp(Ex4_Const.Sort_By_Tag);
		sc1.sort(comp);
		assertEquals(true, sc1.get(0) == gs1t);
		assertEquals(true, sc1.get(1) == gs2s);
		assertEquals(true, sc1.get(2) == gs3c);
		assertEquals(true, sc1.get(3) == gs4r);
		assertEquals(true, sc1.get(4) == gs5p);

		comp = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
		sc1.sort(comp);
		assertEquals(true, sc1.get(4) == gs1t);
		assertEquals(true, sc1.get(3) == gs2s);
		assertEquals(true, sc1.get(2) == gs3c);
		assertEquals(true, sc1.get(1) == gs4r);
		assertEquals(true, sc1.get(0) == gs5p);
		
		//Load, save, remove all:
		sc1.save("fileForTest");
		sc1.removeAll();
		assertEquals(0, sc1.size());
		sc1.load("fileForTest");
		assertEquals(5, sc1.size());
		
		//Remove element at:
		sc1.removeElementAt(0);
		assertEquals(4, sc1.size());
		assertEquals(gs4r.toString(), sc1.get(0).toString());
		
		//Testing bounding box:
		Rect2D rbb = sc1.getBoundingBox();
		assertEquals(30.599999, rbb.area(), EPS);
	}
}
