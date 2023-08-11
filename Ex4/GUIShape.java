package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import java.util.Arrays;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	public GUIShape(String data) {
		char c = data.charAt(data.length()-1);
		if(!Character.isDigit(c)) {
			data = data.substring(0, data.length()-1);
		}
		String[] splitedData = data.split(",");
		init(splitedData);
	}
	
	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		int col = _color.getBlue()+ _color.getGreen()*256 + _color.getRed()*256*256;
		return "GUIShape,"+col+","+_fill+","+_tag+","+_g;      
	}
	/**
	 * This method receiving a string array contain the information about the needed GUI_Shape and initiate it.
	 * @param ww - [int "color", boolean "fill", GeoShapeable "Circle2D", Point2D "points"] 
	 */
	private void init(String[] ww) {
		
		this._color = new Color (Integer.parseInt(ww[1]));
		this._fill = Boolean.parseBoolean(ww[2]);
		this._tag = Integer.parseInt(ww[3]);
		
		//Initiate circle:
		if(ww[4].equals("Circle2D")) {
			Point2D cen = new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6]));
			double rad = Double.parseDouble(ww[7]);
			this._g = new Circle2D(cen, rad);
		}
		//Initiate rectangle:
		if(ww[4].equals("Rect2D")) {
			//Do not change this order!
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]), Double.parseDouble(ww[8]));
			Point2D p3 = new Point2D(Double.parseDouble(ww[9]), Double.parseDouble(ww[10]));
			Point2D p4 = new Point2D(Double.parseDouble(ww[11]), Double.parseDouble(ww[12]));
			this._g = new Rect2D(p1, p2, p3, p4);
		}
		//Initiate triangle:
		if(ww[4].equals("Triangle2D")) {
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]), Double.parseDouble(ww[8]));
			Point2D p3 = new Point2D(Double.parseDouble(ww[9]), Double.parseDouble(ww[10]));
			this._g = new Triangle2D(p1, p2, p3);
		}
		//Initiate segment: 
		if(ww[4].equals("Segment2D")) {
			Point2D p1 = new Point2D(Double.parseDouble(ww[5]), Double.parseDouble(ww[6]));
			Point2D p2 = new Point2D(Double.parseDouble(ww[7]), Double.parseDouble(ww[8]));
			this._g = new Segment2D(p1, p2);
		}
		//Initiate polygon:
		if(ww[4].equals("Polygon2D")) {
			Polygon2D poly = new Polygon2D();
			Point2D fp;
			for(int i=5; i<ww.length-1; i=i+2) {
				fp = new Point2D(Double.parseDouble(ww[i]), Double.parseDouble(ww[i+1]));
				poly.add(fp);
			}
			this._g = poly;
		}
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShapeable g) {
		this._g = g;
	}
}
