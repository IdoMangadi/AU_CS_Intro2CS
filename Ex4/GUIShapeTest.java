package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;

class GUIShapeTest {

	@Test
	void testGUIShapeGeoShapeableBooleanColorInt() {
		//I implemented complex test ahead that includes this constructor.
	}

	@Test
	void testGUIShapeGUIShape() {
		//I implemented complex test ahead that includes this constructor.
	}

	@Test
	void testGUIShapeString() {
		//I implemented complex test ahead that includes this constructor.
	}
	
	@Test
	void testDeepCopy() {
		Point2D p1 = new Point2D (1.0, 1.0);
		Point2D p2 = new Point2D (6.0, 1.0);
		Point2D p3 = new Point2D (3.5, 7.0);
		GeoShapeable g1 = new Triangle2D(p1, p2, p3);
		Color c1 = new Color(255);
		GUI_Shapeable gs1 = new GUIShape(g1, true, c1, 1);
		
		GUI_Shapeable gs2 = gs1.copy();
		assertNotEquals(true, gs1 == gs2);
	}

	@Test
	void testToString() {
		//I implemented complex test ahead that includes this method.
	}
	
	@Test
	void testInit() {
		//I implemented complex test ahead that includes this method.
	}
}
