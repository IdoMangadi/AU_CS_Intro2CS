package Exe.EX3;

//Implemented by: Ido Izhar Mangadi 
//I.D: 315310250

import java.util.Stack;

/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. */
public class MyMap2D implements Map2D{
	
	// private:
	private int[][] _map;
	
	//constructors:
	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}
	
	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];
		
	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	
	//getters and setters:
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}
	
	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) { 
		setPixel(p.ix(), p.iy(), v);
	}
	
	
	//functions:
	
	/**
	 * The function gets 2 points and color represented by integer, and split to two cases:
	 * 1.If the delta x is bigger: 
	 * 	the function will go in a straight line from the point with the lower x value to the point with the higher x value
	 * 	and will check what should be the value of y in that point according to the slope between the two points.
	 * 2.If the delta y is bigger:
	 * 	the function will go in a straight line from the point with the lower y value to the point with the higher y value
	 * 	and will check what should be the value of x in that point according to the slope between the two points.
	 * 	Note: the function calculating the slope differently in case 1 and in case 2 (In case 2 The function actually refer the map like its tilt in 90 degrees)
	 */
	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) {
		
		int x0 = p1.ix();
		int y0 = p1.iy();
		int x1 = p2.ix();
		int y1 = p2.iy();
		
		int deltaX = Math.abs(x1 - x0); 
		int deltaY = Math.abs(y1 - y0);
		
		int steps = 0;
		double m; 
		
		//In case the delta of the x's is bigger, go over them:
		if (deltaX >= deltaY) {
			//changing (xo,y0) to be the point with the lower x value if needed to change:
			if (x0 > x1) {
				int temp = x0;
				x0 = x1;
				x1 = temp;
				temp = y0;
				y0 = y1;
				y1 = temp;
			}
			//defining the slope as usual: 
			m = (double)(y1-y0) / (double)(x1-x0);
			for (int i=x0; i<x1; i++) {
				_map[i][y0 + (int)(steps*m)] = v;
				steps++;
			}
			_map[x1][y1] = v;
		}
		//In case the delta of the y's is bigger, go over them: 
		else {
			//changing (xo,y0) to be the point with the lower y value if needed to change:
			if (y0 > y1) {
				int temp = y0;
				y0 = y1;
				y1 = temp;
				temp = x0;
				x0 = x1;
				x1 = temp;
			}
			//defining the slope unusual:
			m =  (double)(x1-x0) / (double)(y1-y0);
			for (int i=y0; i<y1; i++) {
				_map[x0 + (int)(steps*m)][i] = v;
				steps++;
			}
			_map[x1][y1] = v;
		}
	}
	
	/**
	 * This function gets two points and color represented by integer, and 'painting' the rectangle that created.
	 * The function go over all the rectangle and paint all the cells in it.
	 */
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {
		
		for(int i=Math.min(p1.ix(), p2.ix()); i<=Math.max(p1.ix(), p2.ix()); i++){
			for(int j=Math.min(p1.iy(), p2.iy()); j<=Math.max(p1.iy(), p2.iy()); j++) {
				this.setPixel(i, j, col);
			}
		}
	}
	
	/**
	 * This function gets point, radius and color represented by integer, and 'painting' the circle that created.
	 * In the main program the radius calculated by the distance between the points that the user pressed on.
	 */
	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		
		double currentDist = 0.0;
		
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j <this.getHeight(); j++) {
				currentDist = Math.sqrt( Math.pow((p.x()-i),2) + Math.pow((p.y()-j),2) );
				if (currentDist <= rad ) {
					this.setPixel(i, j, col);
				}
			}
		}
		this.setPixel(p , col);
	}
	
	/**
	 * This function simply call the other fill function that I implemented next by.
	 */
	@Override
	public int fill(Point2D p, int new_v) {
		
		fill(p.ix(), p.iy(), new_v);
		return 0;
	}
	
	/**
	 * This function will start from the given point and will "save" all the points surrounding it that have the same color.
	 * Then the function will check every one of the surrounding points if they have itself more points that have the same color surrounding them.
	 * The function will do it over and over until it will get to the limit of the map or to points that all the surrounding is
	 * not in the same color.
	 * The function wont check the points that are diagonal to the current checked point because in that way it will go over
	 * a line that could be there.
	 */
	@Override
	public int fill(int x, int y, int new_v) {
		
		Point2D p = new Point2D (x,y);
		int pre_v = this.getPixel(p);
		
		//2D map marks all the indexes already checked:
		boolean [][] marker = new boolean [this.getWidth()] [this.getHeight()];
		
		//Stack contains the current points that I check:
		Stack <Point2D> currentPoints = new Stack <Point2D>();
		
		currentPoints.push(p);
		
		while(!currentPoints.empty()) {
			p = currentPoints.pop();
			marker [p.ix()][p.iy()] = true;
			this.setPixel(p, new_v);
			
			//Checking the point from the right:
			if (p.ix()+1 < this.getWidth()) {
				if (marker[p.ix()+1][p.iy()] == false && _map[p.ix()+1][p.iy()] == pre_v) {
					currentPoints.push(new Point2D (p.ix()+1 , p.iy()));
				}
			}
			//Checking the point from the left:
			if (p.ix()-1 >= 0) {
				if (marker[p.ix()-1][p.iy()] == false && _map[p.ix()-1][p.iy()] == pre_v) {
					currentPoints.push(new Point2D (p.ix()-1 , p.iy()));
				}
			}
			//Checking the point above it:
			if (p.iy()+1 < this.getHeight()) {
				if (marker[p.ix()][p.iy()+1] == false && _map[p.ix()][p.iy()+1] == pre_v) {
					currentPoints.push(new Point2D (p.ix() , p.iy()+1));
				}
			}
			//Checking the point under it:
			if (p.iy()-1 >= 0) {
				if (marker[p.ix()][p.iy()-1] == false && _map[p.ix()][p.iy()-1] == pre_v) {
					currentPoints.push(new Point2D (p.ix() , p.iy()-1));
				}
			}
		}
		return 0;
	}
	
	/**
	 * This function first check if the two points are in the same place.
	 * Else way, the function generating the function pathMarking that I implemented next by (documentation there) and put the result into boolean 2D array.
	 * Now if there is a path between them, the function will go over the boolean array and paint the path that leads from p2 to p1 in the shortest way 
	 */
	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		//if the points are in the same place:
		if (p1.ix() == p2.ix() && p1.iy() == p2.iy()) {
			Point2D [] same = {p1};
			return same;
		}
		
		int [][] markedArr = pathMarking(p1,p2);
		//if there is no path between the points:
		if (markedArr[p2.ix()][p2.iy()] == -1) {
			return null;
		}
		
		//if there is a path between them:
		Point2D [] path = new Point2D [markedArr[p2.ix()][p2.iy()] +1];
		path [path.length-1] = p2;
		int x = p2.ix();
		int y = p2.iy();
		
		
		for (int i = path.length-2; i >=0; i--) {
			//left:
			if (x > 0 && markedArr[x-1][y] == i) {
				path[i] = new Point2D (x-1, y);
				x--;
				continue;
			}
			//right:
			if (x < this.getWidth()-1  && markedArr[x+1][y] == i) {
				path[i] = new Point2D (x+1, y);
				x++;
				continue;
			}
			//up:
			if (y < this.getHeight()-1 && markedArr[x][y+1] == i) {
				path[i] = new Point2D (x, y+1);
				y++;
				continue;
			}
			//down:
			if (y >0 && markedArr[x][y-1] == i) {
				path[i] = new Point2D (x, y-1);
				y--;
				continue;
			}
		}
		return path;
	}

	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		//if the points are in the same place:
		if (p1.ix() == p2.ix() && p1.iy() == p2.iy()) {
			return 0;
		}
		//else:
		int res = shortestPath(p1,p2).length;
		return res;
	}
	
	/**
	 * This function go all over the map and marking in a other array if the cell will live in the next generation.
	 * Next, the function will change the map according to the results of that new array.
	 * Note: The function using a private function named "willLive" implemented next by. 
	 */
	@Override
	public void nextGenGol() {
		
		boolean [][] nextGen = new boolean [this.getWidth()][this.getHeight()];
		
		//Going all over the map in willLive and check what will be the status in the next generation:
		for (int i= 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (willLive(i,j)){
					nextGen[i][j] = true;
				}
			}
		}
		//Going all over the nextGen array and changing the color of the cell in the map according to the next generation:
		for (int i= 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if( nextGen[i][j] == true) {
					_map[i][j] = BLACK;
				}
				else {
					_map[i][j] = WHITE;
				}
			}
		}
	}
	
	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}
	}
	
	/**
	 *private function that returning a map contains the numbers of steps of each cell from the starting point.
	 */
	private int [][] pathMarking (Point2D start, Point2D end){
		
		//The array that will help me know where I entered a value in the returning array (markedArr):
		boolean [][] wasHere = new boolean [this.getWidth()][this.getHeight()];
		
		//the array that will return as a marked array of the distances:
		int [][] markedArr = new int [this.getWidth()][this.getHeight()];
		for (int i=0; i<this.getWidth(); i++) {
			for (int j=0; j<this.getHeight(); j++) {
				markedArr[i][j] = -1;
			}
		}
		
		boolean stopper = false;
		int counter = 0;
		markedArr[start.ix()][start.iy()] = 0;
		wasHere[start.ix()][start.iy()] = true;
		int pre_v = _map[start.ix()][start.iy()];
		
		while (!wasHere[end.ix()][end.iy()] && !stopper) {
			
			stopper = true;
			
			for(int i=0; i < this.getWidth(); i++) {
				for (int j=0; j < this.getHeight(); j++) {
					//finding the current step:
					if (markedArr[i][j] == counter) {
						//left:
						if (i>0 && _map[i-1][j] == pre_v && !wasHere[i-1][j]) {
							markedArr[i-1][j] = counter+1;
							wasHere[i-1][j] = true;
							stopper = false;
						}
						//right:
						if (i<this.getWidth()-1 && _map[i+1][j] == pre_v && !wasHere[i+1][j]) {
							markedArr[i+1][j] = counter+1;
							wasHere[i+1][j] = true;
							stopper = false;
						}
						//up:
						if (j<this.getHeight()-1 && _map[i][j+1] == pre_v && !wasHere[i][j+1]) {
							markedArr[i][j+1] = counter+1;
							wasHere[i][j+1] = true;
							stopper = false;
						}
						//down:
						if (j>0 && _map[i][j-1] == pre_v && !wasHere[i][j-1]) {
							markedArr[i][j-1] = counter+1;
							wasHere[i][j-1] = true;
							stopper = false;
						}
					}
				}
			}
			counter++;
		}
		return markedArr;
	}
	
	/**
	 *Private function that by giving it an index representing the current checked cell of the map, giving back if it will live in the next generation.
	 */
	private boolean willLive (int x , int y) {
		
		int counter = 0; 
		
		//left:
		if (x>0 && _map[x-1][y] != WHITE) {
			counter++;
		}
		//up left:
		if (x>0 && y < this.getHeight()-1 && _map[x-1][y+1] != WHITE) {
			counter++;
		}
		//up:
		if (y < this.getHeight()-1 && _map[x][y+1] != WHITE) {
			counter++;
		}
		//up right:
		if (x < this.getWidth()-1  && y < this.getHeight()-1 && _map[x+1][y+1] != WHITE) {
			counter++;
		}
		//right:
		if (x < this.getWidth()-1  && _map[x+1][y] != WHITE) {
			counter++;
		}
		//down right:
		if (x < this.getWidth()-1  && y>0 && _map[x+1][y-1] != WHITE) {
			counter++;
		}
		//down:
		if (y > 0 && _map[x][y-1] != WHITE) {
			counter++;
		}
		//down left:
		if (x>0 && y>0 && _map[x-1][y-1] != WHITE) {
			counter++;
		}
		
		//if it alive now:
		if (_map[x][y] != WHITE && (counter ==2 || counter ==3)) {
			return true;
		}
		
		//if it dead now:
		if (_map[x][y] == WHITE && counter ==3) {
			return true;
		}
		
		return false;
	}
}
