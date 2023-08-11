package Exe.Ex4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	//Fields:
	private ArrayList<GUI_Shapeable> _shapes;
	
	///Constructor:
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	
	//Methods:
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		return _shapes.remove(i);
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		if(s!=null && s.getShape()!=null && i>=0 && i<_shapes.size()) {
			_shapes.add(i, s);
		}
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() {
		ShapeCollectionable cop = new ShapeCollection();
		for(int i=0; i<this._shapes.size(); i++) {
			cop.add(_shapes.get(i).copy());
		}
		return cop; 
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);
	}

	@Override
	/**
	 * This method save the shapes collection's to string into a file.
	 */
	public void save(String file) {
		File f1 = new File(file);
		try {
			BufferedWriter bufferW = new BufferedWriter(new FileWriter(f1));
			for(int i=0; i<_shapes.size(); i++) {
				bufferW.write(_shapes.get(i).toString()+"\n");
			}
			bufferW.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file) {
		removeAll();
		File fileToLoad = new File (file);
		try {
			FileReader reader = new FileReader(fileToLoad);
			BufferedReader bufferR = new BufferedReader (reader);
			String line = bufferR.readLine();
			while(line  != null) {
				GUI_Shapeable s = new GUIShape(line);
				this._shapes.add(s);
				line = bufferR.readLine();
			}
			bufferR.close();
			System.out.println("Loaded succesfully");
		}
		catch(Exception ex) {
			System.out.println("Not loaded");
		}
	}
	
	/**
	 * This method going all over the gui_shapes in the collection and going over all the points of each 
	 * and saving the max x coordinate, minimum x coordinate, max y coordinate and minimum y coordinate.
	 * Then the method returning a rectangle with this points.
	 *Note: the method return null if there is no shapes in the collection!
	 *Note: the method assuming the map is 10x10 coordinate as given in this program.
	 */
	@Override
	public Rect2D getBoundingBox() {
		
		if(_shapes.size() == 0) {
			return null;
		}
		
		//The map is 10x10 coordinate.
		double max =0, mix=10, may=0, miy=10;
		Point2D[] pts;
		
		for(int i=0; i<_shapes.size(); i++) {
			//Circle case:
			if(_shapes.get(i) instanceof Circle2D) {
				pts = _shapes.get(i).getShape().getPoints();
				Circle2D c1 = (Circle2D) _shapes.get(i).getShape();
				max = Math.max(max, pts[0].x()+ c1.getRadius());
				mix = Math.min(mix, pts[0].x()- c1.getRadius());
				may = Math.max(may, pts[0].y()+ c1.getRadius());
				miy = Math.min(miy, pts[0].y()- c1.getRadius());
			}
			//Any other case:
			else {
				pts = _shapes.get(i).getShape().getPoints();
				for(int j=0; j<pts.length; j++) {
					max = Math.max(max, pts[j].x());
					mix = Math.min(mix, pts[j].x());
					may = Math.max(max, pts[j].y());
					miy = Math.min(miy, pts[j].y());
				}
			}
		}
		
		Point2D pTL = new Point2D(mix, may);
		Point2D pBR = new Point2D(max, miy);
		Rect2D ans = new Rect2D(pTL, pBR);
		return ans;
	}
	
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i)+"\n";
		}
		return ans;
	}
	

}
