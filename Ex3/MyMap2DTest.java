package Exe.EX3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class MyMap2DTest {
	
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int GREEN = Color.GREEN.getRGB();
	public static final int RED = Color.RED.getRGB();
	public static final int YELLOW = Color.YELLOW.getRGB();
	public static final int BLUE = Color.BLUE.getRGB();
	
	static MyMap2D map = new MyMap2D (10,10);
	static final Point2D p0 = new Point2D(0,0);
	static final Point2D p1 = new Point2D(map.getWidth()-1, map.getHeight()-1);
	
	

	@Test
	void testDrawSegment() {
		
		//Testing drawing a diagonal:
		map.fill(WHITE);
		map.drawSegment(p0, p1, BLACK);
		for(int i=0; i<10;i++) {
			assertEquals(map.getPixel(i,i), BLACK);
		}
		
		//Testing giving the same point twice to draw:
		map.fill(WHITE);
		Point2D p2 = new Point2D(0,0);
		Point2D p3 = new Point2D(0,0);
		map.drawSegment(p2, p3, BLACK);
		assertEquals(map.getPixel(p2), BLACK);
		
		//More tests implemented in the complex combined tested next by (testFill and more).
	}

	@Test
	void testDrawRect() {
		
		//Testing full map rectangle:
		map.fill(WHITE);
		map.drawRect(p0, p1, RED);
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				assertEquals(map.getPixel(i, j), RED);
			}
		}
		
		//Testing for two points in the same place:
		map.fill(WHITE);
		map.drawRect(p0, p0, RED);
		assertEquals(map.getPixel(0, 0), RED);
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(!(i==0 && j==0)) {
					assertEquals(map.getPixel(i, j), WHITE);
				}
			}
		}
		
		//Testing color changing in an existed rectangle:
		map.fill(WHITE);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(7,7);
		map.drawRect(p2, p3, BLUE);
		//Points inside:
		for(int i=2; i<8; i++) {
			for(int j=2; j<8; j++) {
				if(!(i==0 && j==0)) {
					assertEquals(map.getPixel(i, j), BLUE);
				}
			}
		}
		//Points outside:
		assertEquals(map.getPixel(0,0), WHITE);
		assertEquals(map.getPixel(0,9), WHITE);
		assertEquals(map.getPixel(9,9), WHITE);
		assertEquals(map.getPixel(9,0), WHITE);
		//Redraw:
		map.drawRect(p2, p3, RED);
		//Points inside:
		for(int i=2; i<8; i++) {
			for(int j=2; j<8; j++) {
				if(!(i==0 && j==0)) {
					assertEquals(map.getPixel(i, j), RED);
				}
			}
		}
		//Points outside:
		assertEquals(map.getPixel(0,0), WHITE);
		assertEquals(map.getPixel(0,9), WHITE);
		assertEquals(map.getPixel(9,9), WHITE);
		assertEquals(map.getPixel(9,0), WHITE);
		
		//Testing smaller rectangle inside the previous bigger one:
		Point2D p4 = new Point2D(4,4);
		Point2D p5 = new Point2D(5,5);
		map.drawRect(p4, p5, YELLOW);
		//Smaller rectangle points:
		for(int i=4; i<6; i++) {
			for(int j=4; j<6; j++) {
				if(!(i==0 && j==0)) {
					assertEquals(map.getPixel(i, j), YELLOW);
				}
			}
		}
		//Bigger rectangle points:
		assertEquals(map.getPixel(2,2), RED);
		assertEquals(map.getPixel(7,2), RED);
		assertEquals(map.getPixel(7,7), RED);
		assertEquals(map.getPixel(2,7), RED);
		//Points outside both:
		assertEquals(map.getPixel(0,0), WHITE);
		assertEquals(map.getPixel(0,9), WHITE);
		assertEquals(map.getPixel(9,9), WHITE);
		assertEquals(map.getPixel(9,0), WHITE);
		
		//More tests implemented in the complex combined tested next by (testFill and more).
	}

	@Test
	void testDrawCircle() {
		
		//Testing points in and out a circle
		map.fill(WHITE);
		Point2D p2 = new Point2D (4,4);
		map.drawCircle(p2, 3, BLUE);
		//Points inside the circle:
		assertEquals(map.getPixel(4,4), BLUE);
		assertEquals(map.getPixel(5,5), BLUE);
		//Points outside the circle:
		assertEquals(map.getPixel(9,9), WHITE);
		assertEquals(map.getPixel(0,0), WHITE);
		
		//Testing circle inside the previous circle:
		map.drawCircle(p2, 2, RED);
		//Points inside the inner circle:
		assertEquals(map.getPixel(4,4), RED);
		assertEquals(map.getPixel(4,3), RED);
		//Points in the outsider circle:
		assertEquals(map.getPixel(5,6), BLUE);
		assertEquals(map.getPixel(6,5), BLUE);
		//Points outside the circles:
		assertEquals(map.getPixel(9,9), WHITE);
		assertEquals(map.getPixel(0,0), WHITE);
		
		//Testing circle from the zero point:
		map.fill(WHITE);
		map.drawCircle(p0, 5, BLUE);
		//Points inside the circle:
		assertEquals(map.getPixel(3,3), BLUE);
		assertEquals(map.getPixel(4,3), BLUE);
		assertEquals(map.getPixel(3,4), BLUE);
		//Points outside the circle:
		assertEquals(map.getPixel(9,9), WHITE);
		assertEquals(map.getPixel(6,6), WHITE);
		
		//More tests implemented in the complex combined tested next by (testFill and more).
	}

	@Test
	void testFill() {
		
		//Testing filling divided map by diagonal:
		map.fill(WHITE);
		map.drawSegment(p0, p1, BLACK);
		map.fill(1, 0, BLUE);
		map.fill(0, 1, RED);
		assertEquals(map.getPixel(5,5), BLACK);
		assertEquals(map.getPixel(5,4), BLUE);
		assertEquals(map.getPixel(4,5), RED);
		
		//Testing filling rectangle crossed by 1's slope diagonal:
		map.fill(WHITE);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(2,8);
		Point2D p4 = new Point2D(8,8);
		Point2D p5 = new Point2D(8,2);
		//The same previous diagonal:
		map.drawSegment(p0, p1, BLACK);  
		//The rectangle:
		map.drawSegment(p2, p3, BLACK);
		map.drawSegment(p3, p4, BLACK);
		map.drawSegment(p4, p5, BLACK);
		map.drawSegment(p5, p2, BLACK);
		map.fill(5, 4, BLUE);
		map.fill(5, 6, RED);
		//Testing two sides inside the rectangle:
		assertEquals(map.getPixel(6,4), BLUE);
		assertEquals(map.getPixel(4,6), RED);
		//Checking outside the rectangle:
		assertEquals(map.getPixel(0,1), WHITE);
		assertEquals(map.getPixel(1,0), WHITE);
		
		//Testing filling a circle:
		map.fill(WHITE);
		Point2D p6 = new Point2D(4,4);
		map.drawCircle(p6, 3, BLUE);
		map.fill(p6, RED);
		////Checking inside the circle:
		assertEquals(map.getPixel(4,2), RED);
		//Checking outside the rectangle:
		assertEquals(map.getPixel(p1), WHITE);
		
		//Testing filling a hole 160x160 map:
		MyMap2D map160 = new MyMap2D (160,160);
		map160.fill(BLACK);
		assertEquals(map160.getPixel(5,5), BLACK );
	}

	@Test
	void testShortestPathAndDist() {
		
		//Testing that it is the shortest:
		map.fill(WHITE);
		Point2D p2 = new Point2D(2,5);
		Point2D p3 = new Point2D(5,2);
		//Testing the length only:
		int pathL = map.shortestPathDist(p2, p3);
		assertEquals(pathL, 7);
		//Testing the path itself:
		Point2D[] path = map.shortestPath(p2, p3);
		for(int i=0; i<pathL; i++) {
			map.setPixel(path[i], BLACK);
		}
		for(int i=5; i<1; i--) {
				assertEquals(map.getPixel(2, i), map.getPixel(i, 2));
		}
		
		//Testing complex path:
		//Visual illustration: the path between the two @:
		//  _____________________
		// |                     |
		// |                     |
		// | xxxxxxxx            |
		// |        x            |
		// |        x            |
		// |        x            |
		// |        x            |
		// |        x            |
		// |        x            |
		// |_______@x@___________|
		map.fill(WHITE);
		Point2D p4 = new Point2D(4,0);
		Point2D p5 = new Point2D(4,7);
		Point2D p6 = new Point2D(1,7);
		map.drawSegment(p4, p5, BLACK);
		map.drawSegment(p5, p6, BLACK);
		Point2D pS = new Point2D(3,0);
		Point2D pE = new Point2D(5,0);
		path = map.shortestPath(pS, pE);
		pathL = path.length;
		assertEquals(pathL, 25);
		
		//Testing same point input:
		map.fill(WHITE);
		Point2D sP = new Point2D(0,0);
		Point2D[] sPath = map.shortestPath(sP, sP);
		int sPathL = sPath.length;
		assertEquals(sPathL, 1);
		
		//Testing where there is no path between two points:
		map.fill(WHITE);
		map.drawSegment(p0, p1, BLACK);
		Point2D[] noPath = map.shortestPath(p2, p3);
		assertEquals(noPath, null);
	}

	@Test
	void testShortestPathDist() {
		//Tests implemented in the previous test of shortestPath.
	}

	@Test
	void testNextGenGol() {
		
		//Testing empty small rectangle:
		map.fill(WHITE);
		//generation 1:
		map.setPixel(1, 1, BLACK);
		map.setPixel(2, 1, BLACK);
		map.setPixel(3, 1, BLACK);
		map.setPixel(3, 2, BLACK);
		map.setPixel(3, 3, BLACK);
		map.setPixel(2, 3, BLACK);
		map.setPixel(1, 3, BLACK);
		map.setPixel(1, 2, BLACK);
		//generation 2:
		map.nextGenGol();
		//live:
		assertEquals(map.getPixel(1,1), BLACK);
		assertEquals(map.getPixel(3,1), BLACK);
		assertEquals(map.getPixel(1,3), BLACK);
		assertEquals(map.getPixel(3,3), BLACK);
		assertEquals(map.getPixel(2,0), BLACK);
		assertEquals(map.getPixel(2,4), BLACK);
		assertEquals(map.getPixel(0,2), BLACK);
		assertEquals(map.getPixel(4,2), BLACK);
		//dead:
		assertEquals(map.getPixel(2,1), WHITE);
		assertEquals(map.getPixel(3,2), WHITE);
		assertEquals(map.getPixel(2,3), WHITE);
		assertEquals(map.getPixel(1,2), WHITE);
	}
}
