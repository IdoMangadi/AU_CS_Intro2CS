package Exe.Ex4;
import java.awt.Color;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;
/**
 * A very simple main class for basic code for Ex4
 * 
 * t2: output:
 * GUIShape,0,true,1,Circle2D,3.0,4.0, 2.0
 * GUIShape,255,false,2,Circle2D,6.0,8.0, 4.0
 * 
 * @author boaz.ben-moshe
 *
 */
public class Ex4Main {

	public static void main(String[] args) {
		t1();
		t2();
		t3(); // won't work "out of the box" - requires editing the code (save, load..)
	}
	// Minimal empty frame (no shapes)
	public static void t1() {
		Ex4 ex4 = Ex4.getInstance();
		ex4.show();
	} 
	// Two simple circles
	public static void t2() {
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable shapes = ex4.getShape_Collection();
//		
//		Point2D p1 = new Point2D(1,1);
//		Point2D p2 = new Point2D(9,9);
//		Point2D p3 = new Point2D(6,5);
//		Point2D p4 = new Point2D(5,2);
//		Point2D p5 = new Point2D(1,4);
//		
//		Circle2D c1 = new Circle2D(p1,2);
//		Circle2D c2 = new Circle2D(p2,4);
//		
//		Rect2D r1 = new Rect2D(p1,p2);
//		
//		Triangle2D t1 = new Triangle2D(p1, p2, p3);
//		
//		Segment2D s1 = new Segment2D(p3, p4);
//		
//		Polygon2D poly1 = new Polygon2D();
//		poly1.add(p1);
//		poly1.add(p2);
//		poly1.add(p3);
//		poly1.add(p4);
//		poly1.add(p5);
//		
//		GUI_Shapeable gs1 = new GUIShape(c1, true, Color.black, 1);
//		GUI_Shapeable gs2 = new GUIShape(c2, false, Color.blue, 2);
//		GUI_Shapeable gs3 = new GUIShape(r1, true, Color.red, 3);
//		GUI_Shapeable gs4 = new GUIShape(t1, true, Color.yellow, 4);
//		GUI_Shapeable gs5 = new GUIShape(s1, false, Color.black, 5);
//		GUI_Shapeable gs6 = new GUIShape(poly1, false, Color.black, 5);
//		
//		shapes.add(gs1);
//		shapes.add(gs2);
//		shapes.add(gs3);
//		shapes.add(gs4);
//		shapes.add(gs5);
//		shapes.add(gs6);
		//ex4.init(shapes);
		ex4.show();
		System.out.print(ex4.getInfo());
		
	}
	// Loads a file from file a0.txt (Circles only).
	public static void t3() {
		Ex4 ex4 = Ex4.getInstance();
		ShapeCollectionable shapes = ex4.getShape_Collection();
		String file = "a0.txt"; //make sure the file is your working directory.
		shapes.load(file);
		ex4.init(shapes);
		ex4.show();
	}

}
