package Exe.Ex4.gui;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

class Ex4Test {
	public static Ex4 ttt = Ex4.getInstance();
	
	/**
	 * I've combined all the tests in one test unit ahead to make it simpler.
	 * NOTE: all the required test are here.  
	 */
	@Test
	void testDrawShapes() {
		ShapeCollectionable sc1 = ttt.getShape_Collection();
		
		//Creating a red filled triangle:
		ttt.actionPerformed("Red");
		ttt.actionPerformed("Fill");
		ttt.actionPerformed("Triangle");
		ttt.mouseClicked(new Point2D (1.0, 1.0));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (6.0, 1.0));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (3.5, 7.0));
		assertEquals(1, sc1.size());
		
		//Creating a blue filled rectangle:
		ttt.actionPerformed("Blue");
		ttt.actionPerformed("Fill");
		ttt.actionPerformed("Rect");
		ttt.mouseClicked(new Point2D (1.1, 6.1));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (6.1, 1.1));
		assertEquals(2, sc1.size());
		
		//Creating a yellow filled circle:
		ttt.actionPerformed("Yellow");
		ttt.actionPerformed("Fill");
		ttt.actionPerformed("Circle");
		ttt.mouseClicked(new Point2D (5.5, 3.5));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (5.5, 5));
		assertEquals(3, sc1.size());
		
		//Creating a black segment:
		ttt.actionPerformed("Black");
		ttt.actionPerformed("Fill");
		ttt.actionPerformed("Segment");
		ttt.mouseClicked(new Point2D (1.0, 1.0));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (6.0, 1.0));
		assertEquals(4, sc1.size());
		
		//Creating a green polygon:
		ttt.actionPerformed("green");
		ttt.actionPerformed("Fill");
		ttt.actionPerformed("Polygon");
		ttt.mouseClicked(new Point2D (2.0, 1.0));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (6.0, 1.0));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (7.0, 6.0));
		ttt.mouseMoved(null);
		ttt.mouseClicked(new Point2D (4.0, 8.0));
		ttt.mouseMoved(null);
		ttt.mouseRightClicked(new Point2D (1.0, 4.0));
		assertEquals(5, sc1.size());
		
		//Selections:
		ttt.actionPerformed("None");
		//Point:
		ttt.actionPerformed("Point");
		ttt.mouseClicked(new Point2D (4.0, 7.999));
		assertEquals(true, sc1.get(4).isSelected());    //polygon (selected)
		assertEquals(false, sc1.get(3).isSelected());
		assertEquals(true, sc1.get(2).isSelected());    //circle (selected)
		assertEquals(false, sc1.get(1).isSelected());
		assertEquals(false, sc1.get(0).isSelected());
		
		//Anti:
		ttt.actionPerformed("Anti");
		assertEquals(false, sc1.get(4).isSelected());    //polygon (not selected)
		assertEquals(true, sc1.get(3).isSelected());
		assertEquals(false, sc1.get(2).isSelected());    //circle (not selected)
		assertEquals(true, sc1.get(1).isSelected());
		assertEquals(true, sc1.get(0).isSelected());
		
		//All:
		ttt.actionPerformed("All");
		for(int i=0; i<sc1.size(); i++) {
			assertEquals(true, sc1.get(i).isSelected());
		}
		//None:
		ttt.actionPerformed("None");
		for(int i=0; i<sc1.size(); i++) {
			assertEquals(false, sc1.get(i).isSelected());
		}
		
		//Sorting:
		ttt.actionPerformed("ByToString");
		assertEquals(true, sc1.get(4).getShape() instanceof Circle2D);
		assertEquals(true, sc1.get(3).getShape() instanceof Polygon2D);
		assertEquals(true, sc1.get(2).getShape() instanceof Rect2D);
		assertEquals(true, sc1.get(1).getShape() instanceof Segment2D);
		assertEquals(true, sc1.get(0).getShape() instanceof Triangle2D);
		
		//Remove:
		sc1.get(0).setSelected(true);
		ttt.actionPerformed("Remove");
		assertEquals(4, sc1.size());
		
		//Clear:
		ttt.actionPerformed("Clear");
		assertEquals(0, sc1.size());
	}

}
