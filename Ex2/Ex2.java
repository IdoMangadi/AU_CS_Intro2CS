package Exe.Ex2;
/** 
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * 
 * Your goal is to complete the functions below, see the marking: // *** add your code here ***
 *
 * @author boaz.benmoshe
 * 
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	
	/** Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	
	// This function checks first if the lengths are equals, in case they are not,
	//or in case there is a cell in the array that holds a different value then the twin one from the other array, 
	// the function will return false. The other case, means they are exactly the same, the function will return true.
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		
		if (p1.length != p2.length) {
			return false;
		}
		
		for (int i=0 ; i<= p1.length-1 ; i++) {
			if (Math.abs(p1[i] - p2[i]) > EPS) {
				return false;
			}
		}
		
		return ans;
	}
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */
	// the function will go through all the array's cells and will do the number in the cell, 
	//multiply with the variable that is in power of number according to the cell's index (represent the power).   
	public static double f(double[] poly, double x) {
		double ans = 0;
		
		for (int i=0 ; i <=poly.length-1 ; i++) {
			ans = ans + (poly[i] * (Math.pow(x, i)));
		}
		
		return ans;
	}
	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	//This function go through every cell of the array, end to start, not including the last two, and cast them to string.
	//The last two are casted in a different way, because they have specific variables that are not represent in the same way.
	public static String poly(double[] poly) {
		String ans = "";
		
		// Dealing with all the 'x' that has power.
		for (int i=poly.length-1 ; i>=2 ; i--) {
				// the cases i dos'nt need to add "+" before the number:
				if (poly[i] < 0 || i == poly.length-1) {
					if (poly[i] != 1 && poly[i] != -1){
						ans = ans + Double.toString(poly[i]) + "x^" + String.valueOf(i) + " ";
					}
					if (poly[i] == 0 ) {
						ans = "";
					}
					if (poly[i] == -1) {
						ans = ans + "-x^" + String.valueOf(i) + " ";
					}
					if (poly[i] == 1) {
						ans = ans + "x^" + String.valueOf(i) + " ";
					}
				}
				// the cases i do need to add "+" before the number:
				if (poly[i] > 0 && i != poly.length-1) {
					if (poly[i] != 1) {
						ans = ans + "+" + Double.toString(poly[i]) + "x^" + String.valueOf(i) + " ";
					}
					if (poly[i] == 1) {
						ans = ans + "+x^" + String.valueOf(i) + " ";
					}
				}
		}
		
		
		// Dealing with all the 'x' that has'nt power.
		if (poly.length > 1) {
			if (poly[1] < 0.000) {
				if (poly[1] != -1) {
					ans = ans + Double.toString(poly[1]) + "x ";
				}
				if (poly[1] == -1) {
					ans = ans + "-x ";
				}
			}
			if (poly[1] > 0.000) {
				if (poly.length !=2 && poly[1] != 1) {
					ans = ans + "+" + Double.toString(poly[1]) + "x ";
				}
				if (poly.length !=2 && poly[1] == 1) {
					ans = ans + "+x ";
				}
				if (poly.length ==2 && poly[1] != 1) {
					ans = ans + Double.toString(poly[1]) + "x ";
				}
				if (poly.length ==2 && poly[1] == 1) {
					ans = ans + "x ";
				}
			}
		}
		if (poly.length > 0) {
			if (poly[0] < 0.000) {
				ans = ans + Double.toString(poly[0]);
			}
			if (poly[0] > 0.000) {
				ans = ans + "+" + Double.toString(poly[0]);
			}
		}
		return ans;
	}
	/**
	 * Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	// The function first checks which of the numbers of the range gives a positive value to the function and put it into 'b' and the other one to a,
	// Then the function checks if the value of the function with the middle number between a to b is the root of the function,
	// if not, the function will check if the function value is bigger or smaller then 0 and the motivation will be to input the function
	// the value that will give the function the closer value to 0 by bringing the number closer to the number that gives bigger or smaller value to the function respectively
	public static double root(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		double a, b;
		
		if (f(p, x1) > f(p,x2)) {
			b = x1;
			a = x2;
		}
		else {
			b = x2;
			a = x1;
		}
		
		double fValue = f(p, x12);
		
		while (Math.abs(fValue) > eps) {
			if (fValue > 0) {
				b = x12;
			}
			if (fValue < 0) {
				a = x12;
			}
			x12 = (a+b) / 2;
			fValue = f(p, x12);
		}
		
		return x12;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented recursivly.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	// This function will check if x12 is the root of the function, if so it will return it and if not, 
	//the function will check which of the numbers of the range gives the polinom value bigger then 0 and put it to b, and the other one to a,
	//after that it will call this function again with after giving the internal function the range by this condition:
	//if the value of the polinom was bigger then 0, the range will be from a to x12 and if the value of the polinom was smaller then 0 the range will be from x12 to b.
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		
		double fValue = f(p, x12);
		
		if (Math.abs(fValue) < eps) {
			return x12;
		}
		
		double a, b;
		if (f(p, x1) > f(p,x2)) {
			b = x1;
			a = x2;
		}
		else {
			b = x2;
			a = x1;
		}
		
		if (fValue > 0) {
			x12 = root_rec(p, a, x12, eps);
		}
		if (fValue < 0) {
			x12 = root_rec(p, x12, b, eps);
		}
		
		return x12;

	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
	 */
	// This function will check which of x1 , x2 is the one giving that: p1(with it) - p2(with it) > 0 , 
	// ans put that one in b and the other one in a. thats how we know that as much as we getting  x12 closer to b, p1(x12) will be bigger than p2(x12) 
	//and the same idea for a to get close to the x that will give p(x12) will be smaller than p2(x12).
	//Now the function will check if when x12 is in the middle of a and b, abs(p1(x12) - p2(x12) < eps (means x12 is a value that gives the same result for p1 and p2).
	// if so, the function will return it, and if not, the function will check if the value is positive and then it will bring b to be x12 (that way, the next time in the loop, x12 will make p1 and p2 value to be closer to what a gives)
	// and if the value is negative it will bring a to be x12 (the next time will be the same like before but from the other side) and go over again.
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		double a =0 , b=0;
		
		if (f(p1,x1) - f(p2,x1) > 0) {
			b = x1;
			a = x2;
		}
		if (f(p1,x2) - f(p2,x2) > 0) {
			b = x2;
			a = x1;
		}
		
		double polysValue = f(p1,x12) - f(p2,x12);
		
		while (Math.abs(polysValue) > eps) {
			if (polysValue > 0 ) {
				b = x12; 
			}
			if (polysValue < 0 ) {
				a = x12;
			}
			x12 = (a+b) / 2;
			polysValue = f(p1,x12) - f(p2,x12);
		}
		
		
		return x12;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes". 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfBoxes - a natural number representing the number of boxes between x1 and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	// The main idea of this function is to divide the area between the polynoms to 'buildings' that are 1 box weight. 
	//The height of each building is the range between p1 to p2 for any point in the range.
	//That means that the area of each building is that height * 1 box.
	// The function will split the range between x1 to x2 according to the number of boxes, and will go through all this ranges (1 box length each step),
	// any iteration will check what the height of any building for each building (with 1 box weight) between x1 to x2. 
	// The function will sum all this little building's areas and return it.
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfBoxes) {
		double ans = 0;
		
		double rOfEachBox = (x2-x1) / numberOfBoxes;
		
		for (int i = 0; i <= numberOfBoxes; i++) {
			ans = ans + Math.abs(   f( p1 , x1 + (i*rOfEachBox) )   -   f( p2 , x1 + (i*rOfEachBox) )   ) * rOfEachBox;
		}
		
		return ans;
	}
	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note:given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
	 */
	//This function takes the string and split it by " " into an array named parts.
	//Then the function take every cell in parts and put it into 2d array size [parts.length][2] dividing it by the coefficient to [parts.length][0] and the power in [parts.length][1].
	//Then the function creates new double array named 'mekadems' and put in it all the values in 'temp'
public static double[] getPolynomFromString(String p) {
		
		if ( p.matches(".*\\d+.*") == false  && p.contains("x") == false ) {
			return ZERO;
		}
		
		String a= p.replaceAll("\\^" , "c");
		
		String [] parts = a.split(" ");
		String [][] temp = new String [parts.length][2];
		
		for (int i=0; i<=temp.length-1; i++) {
			temp[i][1] = "0";
		}
		
		//Inputting every cell of parts into 2D array 'temp':
		for (int i=0; i<=parts.length-1; i++) {
			
			if (parts[i].contains("x") && parts[i].contains("c") == false){
				temp[i][0] = parts[i];
				temp[i][1] = "1";
			}
			if (parts[i].contains("c") == true) {
				temp [i] = parts[i].split("c");
			}
			if (parts[i].contains("c") == false && parts[0].contains("x") == false) {
				temp[i][0] = parts[i];
				temp[i][1] = "0";
			}
	
		}
		
		
		//Creating new double array named 'mekadems' and put in it all the values in 'temp':
		int maxPow = Integer.parseInt(temp[0][1]);
		
		double [] mekadems = new double [maxPow+1];
		
		
		int powIndex = 0;
		double value = 0.0;
		
		for (int i=0; i<= temp.length-1; i++) {
			
			if (temp[i][1].equals("0") == false && temp[i][1].equals("1") == false ) {
				
				powIndex = Integer.parseInt(temp[i][1]);
				
				if (temp[i][0].equals("x") || temp[i][0].equals("+x")) {
					mekadems[powIndex] = 1;
				}
				if (temp[i][0].equals("-x")) {
					mekadems[powIndex] = -1;
				}
				if (temp[i][0].equals("x")== false && temp[i][0].equals("+x")== false && temp[i][0].equals("-x")== false) {
					value = Double.parseDouble(temp[i][0].substring(0, temp[i][0].length() - 1));
					mekadems[powIndex] = value;
				}
			}
			
			
			else {
				temp[i][0] = parts[i];
				if (temp[i][0].charAt(temp[i][0].length()-1)  == 'x') {
					
					if (temp[i][0].equals("x") || temp[i][0].equals("+x") ) {
						mekadems[1] = 1;
					}
					if (temp[i][0].equals("-x")) {
						mekadems[1] = -1;
					}
					if (temp[i][0].equals("x") == false && temp[i][0].equals("+x") == false && temp[i][0].equals("-x") == false) {
						value = Double.parseDouble(temp[i][0].substring(0, temp[i][0].length() - 1));
						mekadems[1] = value;
					}
					
				}
				else {
					value = Double.parseDouble(temp[i][0]);
					mekadems[0] = value;
				}
			}

		}
		
		return mekadems;
	}
	
	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	// If the length of p1 is bigger than the function will take all number from p2, add it to the same value in the same index in p1 and return p1.
	// And if the length of p1 is bigger than the function will take all number from p2, add it to the same value in the same index in p1 and return p1.
	public static double[] add(double[] p1, double[] p2) {
		
		if (p1.length >= p2.length) {
			for (int i=0; i<=p2.length-1; i++) {
				p1[i] = p1[i] + p2[i];
			}
			//erasing empty values from p1:
			if (p1[p1.length-1] == 0.0) {
				p1 = openingZeroErasing(p1);
			}
			return p1;
		}
		
		else {
			for (int i=0; i<=p1.length-1; i++) {
				p2[i] = p2[i] + p1[i];
			}
			return p2;
		}
	}
	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		
		if ((p1.length ==1 && p1[0] == 0) || (p2.length ==1 && p2[0] == 0)) {
			double [] res0 = {0};
			return res0;
		}
		
		//It was written here taht we need to return p1 but in case that p2 have an x in power 1 at least, 
		//the returned array should be bigger than p1.
		
		double[] res = new double [p1.length + p2.length - 1];
		
		for (int i=0; i<=p1.length-1; i++) {
			
			for (int j=0; j<=p2.length-1; j++) {
				if (p1[i] == 0) {
					break;
				}
				res [i+j] = res[i+j] + (p1[i] * p2[j]);
			}
		}
		
		return res;
		
	}
	/**
	 * This function computes the derivative polynom of po.
	 * @param po
	 * @return
	 */
	//This function is taking every coefficient of 'po' except of the first one, multiply it with it's power,  
	// and put the result in the previous index of the result array. 
	//This way every part of the polinom will be in the exact place it need to be according to it's power. 
	public static double[] derivative (double[] po) {
		
		if (po.length == 1) {
			po[0] = 0;
			return po;
		}
		 double [] res = new double [po.length-1];
		 for (int i=0; i<=res.length-1; i++) {
			 res [i] = po[i+1] * (i+1); 
		 }
		 return res;
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * Note: this function only works for a set of points containing three points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 * Note: you can assume xx[0]!=xx[1]!=xx[2]
	 */
	//This function takes three points and computing the polynomial that they are on by the formula that I took from this site "https://math.stackexchange.com/questions/680646/get-polynomial-function-from-3-points"
	// Note: I asked Boaz if it is okay to do that and he said it is okay if I will mention where I took it from.
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		double [] ans = null;
		
		
		if(xx!=null && yy!=null && xx.length==3 && yy.length==3) {
			
			ans = new double [3];
			
			ans [2] = (xx[0]*(yy[2]-yy[1]) + xx[1]*(yy[0]-yy[2]) +xx[2]*(yy[1]-yy[0])) / ((xx[0]-xx[1])*(xx[0]-xx[2])*(xx[1]-xx[2]));
			ans [1] = (yy[1]-yy[0]) / (xx[1]-xx[0]) - ans[2]*(xx[0]+xx[1]);
			ans [0] = yy[0] - ans[2]*(Math.pow(xx[0], 2)) - ans[1]*xx[0]; 
		}
		
		return ans;
	}
	
	
	///////////////////// Private /////////////////////
	// you can add any additional functions (private) below
	
	
	//This function receiving an array that starts with 0
	//and erasing opening zeros from it and returning a fixed one.
	private static double [] openingZeroErasing(double [] pToFix) {
		
		if (pToFix.length == 1) {
			return pToFix;
		}
		
		int counter = 0;
		for (int i=pToFix.length-1; i>=0; i--) {
			if (pToFix[i] !=0) {
				break;
			}
			counter++;
		}
		
		double [] fixedPo = new double [pToFix.length - counter];
		for (int i=0; i<=fixedPo.length-1 ; i++) {
			fixedPo[i] = pToFix[i];
		}
		
		return fixedPo;
	}
	
	
}
