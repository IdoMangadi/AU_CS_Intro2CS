package Exe.EX3;

//Implemented by: Ido Izhar Mangadi 
//I.D: 315310250

import java.awt.Color;

/**
 * This class is a simple "inter-layer" connecting (aka simplifing) the
 * StdDraw_Ex3 with the Map2D interface.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * You should change this class!
 *
 */
public class Ex3 {
	private static  Map2D _map = null;
	private static Color _color = Color.blue;
	private static String _mode = "";
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	
	public static boolean firstClick_segment = false;
	public static boolean firstClick_rectangle = false;
	public static boolean firstClick_circle = false;
	public static boolean firstClick_path = false;
	public static Point2D p2;

	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);
		
	}
	private static void init(int x) {
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);		
	}
	
	 public static void drawGrid(Map2D map) {
		 int w = map.getWidth();
		 int h = map.getHeight();
		 for(int i=0;i<w;i++) {
			 StdDraw_Ex3.line(i, 0, i, h);
		 }
		 for(int i=0;i<h;i++) {
			 StdDraw_Ex3.line(0, i, w, i);
		 }
	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}		
		StdDraw_Ex3.show();
	}
	public static void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; }
		if(p.equals("White")) {_color = Color.WHITE; }
		if(p.equals("Black")) {_color = Color.BLACK; }
		if(p.equals("Red")) {_color = Color.RED; }
		if(p.equals("Yellow")) {_color = Color.YELLOW; }
		if(p.equals("Green")) {_color = Color.GREEN; }
		
		if(p.equals("Clear")) {_map.fill(WHITE);}
		if(p.equals("20x20")) {init(20);}
		if(p.equals("40x40")) {init(40);}
		if(p.equals("80x80")) {init(80);}
		if(p.equals("160x160")) {init(160);}

		drawArray(_map);
		
	}
	public static void mouseClicked(Point2D p) {
		System.out.println(p);
		int col = _color.getRGB();
		
		//drawing point:
		if(_mode.equals("Point")) {
			_map.setPixel(p,col);
		}
		
		//drawing segment:
		/**
		 * The function (implemented in MyMap2d file) gets 2 points and color represented by integer, and split to two cases:
		 * 1.If the delta x is bigger: 
		 * 	the function will go in a straight line from the point with the lower x value to the point with the higher x value
		 * 	and will check what should be the value of y in that point according to the slope between the two points.
		 * 2.If the delta y is bigger:
		 * 	the function will go in a straight line from the point with the lower y value to the point with the higher y value
		 * 	and will check what should be the value of x in that point according to the slope between the two points.
		 * 	Note: the function calculating the slope differently in case 1 and in case 2 (In case 2 The function actually refer the map like its tilt in 90 degrees)
		 */
		if(_mode.equals("Segment") && firstClick_segment) {
			_map.drawSegment(p, p2, col);
			_mode = "none";
			firstClick_segment = false;
		}
		if (_mode.equals("Segment") && !firstClick_segment) {
			firstClick_segment = true;
			firstClick_rectangle = false;
			firstClick_circle = false;
			firstClick_path = false;
			p2 = new Point2D(p);
		}
		
		//drawing rectangle:
		/**
		 * The function (implemented in MyMap2d file) gets two points and color represented by integer, and 'painting' the rectangle that created.
		 * The function go over all the rectangle and paint all the cells in it.
		 */
		if (_mode.equals("Rect") && firstClick_rectangle) {
			_map.drawRect(p, p2, col);
			_mode = "none";
			firstClick_rectangle = false;
		}
		if (_mode.equals("Rect") && !firstClick_rectangle) {
			firstClick_segment = false;
			firstClick_rectangle = true;
			firstClick_circle = false;
			firstClick_path = false;
			p2 = new Point2D(p);
		}
		
		//drawing circle:
		/**
		 * The function (implemented in MyMap2d file) gets point, radius and color represented by integer, and 'painting' the circle that created.
		 * In the main program the radius calculated by the distance between the points that the user pressed on.
		 */
		if (_mode.equals("Circle") && firstClick_circle) {
			double radius = Math.sqrt( Math.pow((p.x()-p2.x()),2) + Math.pow((p.y()-p2.y()),2) );
			_map.drawCircle(p2, radius, col);
			_mode = "none";
			firstClick_circle = false;
		}
		if (_mode.equals("Circle") && !firstClick_circle) {
			firstClick_segment = false;
			firstClick_rectangle = false;
			firstClick_circle = true;
			firstClick_path = false;
			p2 = new Point2D(p);
		}
		
		//drawing shortest path:
		/**
		 * The function (implemented in MyMap2d file) first check if the two points are in the same place.
		 * Else way, the function generating the function pathMarking that I implemented in MyMap2d file (documentation there) and put the result into boolean 2D array.
		 * Now if there is a path between them, the function will go over the boolean array and paint the path that leads from p2 to p1 in the shortest way 
		 */
		if (_mode.equals("ShortestPath") && firstClick_path) {
			Point2D [] toDrow = _map.shortestPath(p, p2);
			if ( toDrow != null) {
				for (int i=0; i<toDrow.length; i++) {
					_map.setPixel(toDrow[i], col);
				}
			}
			_mode = "none";
			firstClick_path = false;
		}
		if (_mode.equals("ShortestPath") && !firstClick_path) {
			firstClick_segment = false;
			firstClick_rectangle = false;
			firstClick_circle = false;
			firstClick_path = true;
			p2 = new Point2D(p);
		}
		
		//generating fill:
		/**
		 * The function (implemented in MyMap2d file) will start from the given point and will "save" all the points surrounding it that have the same color.
		 * Then the function will check every one of the surrounding points if they have itself more points that have the same color surrounding them.
		 * The function will do it over and over until it will get to the limit of the map or to points that all the surrounding is
		 * not in the same color.
		 * The function wont check the points that are diagonal to the current checked point because in that way it will go over
		 * a line that could be there.
		 */
		if(_mode.equals("Fill")) {
			_map.fill(p, col);
			_mode = "none";
		}
		
		//generating game of life:
		/**
		 * The function (implemented in MyMap2d file) go all over the map and marking in a other array if the cell will live in the next generation.
		 * Next, the function will change the map according to the results of that new array.
		 * Note: The function using a private function named "willLive" implemented in MyMap2d file. 
		 */
		if(_mode.equals("Gol")) {
			_map.nextGenGol();	
		}
		
		drawArray(_map);
		
	}
	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
}
