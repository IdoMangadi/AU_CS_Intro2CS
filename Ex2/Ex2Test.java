package Exe.Ex2;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * This JUnit class represents a very simple unit testing for Ex2.
 * This class should be improved and generalized significantly.
 * Make sure you add documentations to this Tesing class.
 * @author boaz.ben-moshe
 *
 */

class Ex2Test {
	static double[] po1={2,0,3, -1,0}, po2 = {0.1,0,1, 0.1,3};
	
	//The number after the name of the functions references to the serial of the function in Ex2.java
	
	@Test
	void testEquals1() {
		//checking if the function equals is working right:
		// for equals polynomials:
		double [] po3 = {3,2,5.2,6,9} , po4 = {3,2,5.2,6,9};
		assertTrue(Ex2.equals(po3, po4));
		//for not equals polynomials (not the same length):
		double [] po5 = {3,2,5.2,6,9} , po6 = {0,3,2,5.2,6,9};
		assertFalse(Ex2.equals(po5, po6));
		
		//checking if the function do ignore a difference below EPS:
		double [] po7 = {3.000001,2,5.2,6,9} , po8 = {3.00001,2,5.2,6,9};
		assertTrue(Ex2.equals(po7, po8));
		
		//checking if the function dos'nt ignore a difference above EPS:
		double [] po9 = {3.01,2,5.2,6,9} , po10 = {3.001,2,5.2,6,9};
		assertFalse(Ex2.equals(po9, po10));
		
		//testing if the function dealing an arrays with 1 cell of 0
		double [] po11 = {0} , po12 = {0};
		assertTrue(Ex2.equals(po11, po12));
		
		//testing if the function dealing an empty arrays
		double [] po13 = {} , po14 = {};
		assertTrue(Ex2.equals(po13, po14));
	}

	@Test
	void testF2() {
		//testing positive values:
		double[] po3 = {2,0,3, -1,0};
		double fx0 = Ex2.f(po3, 0);
		double fx1 = Ex2.f(po3, 1);
		double fx2 = Ex2.f(po3, 2);
		assertEquals(fx0,2);
		assertEquals(fx1,4);
		assertEquals(fx2,6);
		
		//testing negative values:
		fx0 = Ex2.f(po3, -1);
		fx1 = Ex2.f(po3, -2);
		assertEquals(fx0,6);
		assertEquals(fx1,22);
		
		//testing polynomial from power 0
		double [] po4 = {8};
		fx0 = Ex2.f(po4, 2);
		assertEquals(fx0,8);
	}
	
	@Test 
	void testpoly3() {
		//testing simple test on poly:
		double[] po3 = {2,0,3, -1,0};
		double[] po4 = {0.1,0,1, 0.1,3};
		assertEquals("-x^3 +3.0x^2 +2.0" , Ex2.poly(po3));
		assertEquals("3.0x^4 +0.1x^3 +x^2 +0.1" , Ex2.poly(po4));
		
		//testing poly on an 1 cell array that contains only 0:
		double [] po5 = {0};
		assertEquals("" ,Ex2.poly(po5));
		
		//testing poly on an empty array:
		double [] po6 = {};
		assertEquals("" ,Ex2.poly(po6));
	}

	@Test
	void testRoot4() {
		//note: this function returns double type variable, 
		//So I asked Aharon (Arkady) and he said not to test an input that is an empty array or an array with 0 power,
		//because it will be needed to return null and we didn't learn it yet.
		//(He said in that case we will need to build another function that in case of correct input send it to the root function,
		// and in case of a wrong input it will return EROR)
		
		//testing root for an regular arrays:
		double [] po3 = {2,0,3,-1,0};
		double r3 = Ex2.root(po3, 0, 10, Ex2.EPS);
		assertEquals(3.1958, r3, Ex2.EPS);
		
		double [] po4 = {4,-10,4};
		double r4_1 = Ex2.root(po4, -10, 1, Ex2.EPS);
		double r4_2 = Ex2.root(po4, 1, 10, Ex2.EPS);
		assertEquals(0.5, r4_1, Ex2.EPS);
		assertEquals(2, r4_2, Ex2.EPS);
		
		double [] po5 = {-4,0,1};
		double r5_1 = Ex2.root(po5, -10, 0, Ex2.EPS);
		double r5_2 = Ex2.root(po5, 0, 10, Ex2.EPS);
		assertEquals(-2, r5_1, Ex2.EPS);
		assertEquals(2, r5_2, Ex2.EPS);
		
	}
	
	@Test
	void testRoot_rec5() {
		//note: this function returns double type variable, 
		//So I asked Aharon (Arkady) and he said not to test an input that is an empty array or an array with 0 power,
		//because it will be needed to return null and we didn't learn it yet.
		//(He said in that case we will need to build another function that in case of correct input send it to the root function,
		// and in case of a wrong input it will return EROR)
		
		//testing root for an regular arrays:
		double [] po3 = {2,0,3,-1,0};
		double r3 = Ex2.root_rec(po3, 0, 10, Ex2.EPS);
		assertEquals(3.1958, r3, Ex2.EPS);
		
		double [] po4 = {4,-10,4};
		double r4_1 = Ex2.root_rec(po4, -10, 1, Ex2.EPS);
		double r4_2 = Ex2.root_rec(po4, 1, 10, Ex2.EPS);
		assertEquals(0.5, r4_1, Ex2.EPS);
		assertEquals(2, r4_2, Ex2.EPS);
		
		double [] po5 = {-4,0,1};
		double r5_1 = Ex2.root_rec(po5, -10, 0, Ex2.EPS);
		double r5_2 = Ex2.root_rec(po5, 0, 10, Ex2.EPS);
		assertEquals(-2, r5_1, Ex2.EPS);
		assertEquals(2, r5_2, Ex2.EPS);
	}
	
	
	@Test
	void testSameValue6() {
		//Note: according to the input assumes in this function there is x1 and x2 so that : (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0
		//so we does'nt need to check the result of the input of two polynomials from power 0 or an empty one.
		
		//Testing simple input for the function:
		
		double [] po3 = {10,4,1} , po4 = {8,-3,-2};
		//the first value:
		assertEquals(-2.0 , Ex2.sameValue(po3, po4, -1 ,-9 , Ex2.EPS) , Ex2.EPS);
		//the second value:
		assertEquals(-0.33349609375, Ex2.sameValue(po3, po4, -1 ,9 , Ex2.EPS) ,Ex2.EPS);
		
		double [] po5 = {4,-10,4} , po6 = {-4,0,1};
		//the first value:
		assertEquals(2 , Ex2.sameValue(po5, po6, 1.5 ,9 , Ex2.EPS) , Ex2.EPS);
		//the second value:
		assertEquals(1.3333 , Ex2.sameValue(po5, po6, -10 ,1.5 , Ex2.EPS) , Ex2.EPS);
		
	}
	
	@Test
	void testArea7() {
		
		//Testing "area" for two functions:
		double [] po3 = {-5,2,1} , po4 = {-1,2};
		double a = Ex2.sameValue(po3, po4, 0, -10,Ex2.EPS);
		double b = Ex2.sameValue(po3, po4, 0, 10 ,Ex2.EPS);
		int numberOfBoxes = 100;
		assertEquals(10.665 , Ex2.area(po3, po4, a, b, numberOfBoxes) ,Ex2.EPS);
		
		//Note: I first checked by myself what is the real area between this two functions.
	}
	
	@Test
	public void testFromString8() {
		
		//testing a simple array:
		double[] po3 = {-1.1,2.3,3.1};
		String spo3 = Ex2.poly(po3);
		double[] po4 = Ex2.getPolynomFromString(spo3);
		boolean isSame = Ex2.equals(po4, po3);
		if(!isSame) {fail();}
		assertEquals(spo3, Ex2.poly(po4));
		
		//Testing an array of zero:
		double[] p10 = {0};
		String sp10 = Ex2.poly(p10);
		double[] p11 = Ex2.getPolynomFromString(sp10);
		boolean isSame10 = Ex2.equals(p11, p10);
		if(!isSame10) {fail();}
		assertEquals(sp10, Ex2.poly(p11));
		
	}
	
	@Test
	void testAdd9() {
		
		//Testing the addition of po1 and po2, then the multiply of po2 with -1 and adding it as is it now to po1 
		//expecting to get po1 back again.
		double[] po1A2 = Ex2.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);
		double[] p1 = Ex2.add(po1A2, pp2);
		assertEquals(Ex2.poly(po1), Ex2.poly(p1));

		//Testing the addition of 2 polynomials of 0
		double [] po3 = {0};
		double [] po4 = {0};
		double [] po3A4 = Ex2.add(po3, po4);
		assertEquals(1 , po3A4.length);
		assertEquals(0 , po3A4[0]);
		
		//Testing if by calling add with polynomials that the addition of one to another
		//zeroing the first coefficients, returns an array without the zeroz at start:
		double [] po5 = {3,4,5,6,1} , po6 = {2,9,8,7,-1};
		double [] poRes = Ex2.add(po5, po6) , poReq = {5,13,13,13};
		assertArrayEquals(poReq , poRes);
		
	}

	@Test
	void testMulDoubleArrayDoubleArray10() {
		
		//Testing simple multiply:
		double [] po3 = {8,0,2,1};
		double [] po4 = {-1,3,0,4,2};
		double [] poR = Ex2.mul(po3, po4);
		double [] res = {-8,24,-2,37,19,8,8,2};
		assertArrayEquals(res , poR);
		
		//Testing the addition of two polynomials and the value of the created polynomial with x=5:
		double[] po1A2 = Ex2.add(po1, po2);
		double dd = Ex2.f(po1A2, 5);
		assertEquals(dd, 1864.6, Ex2.EPS);
		
		//Testing a multiplication of an array that have three zeros places at start:
		double [] po5 = {0,0,0,2};
		double [] po6 = {1,2,3};
		poR = Ex2.mul(po5, po6);
		double [] res2 = {0,0,0,2,4,6};
		assertArrayEquals(res2 , poR);
		
		//Testing multiplication of a polynomial with polynomial of {0}:
		double [] po0 = {0};
		res = Ex2.mul(po4, po0);
		assertArrayEquals(res , po0);
	}
	
	@Test
	void testDerivativeArrayDoubleArray11() {
		
		//Testing simple derivative:
		double[] po3 = {1,2,3}; // 3X^2+2x+1
		double[] dp1 = {2,6}; // 6x+2
		double[] dp2 = Ex2.derivative(po3);
		assertEquals(dp1[0], dp2[0],Ex2.EPS);
		assertEquals(dp1[1], dp2[1],Ex2.EPS);
		assertEquals(dp1.length, dp2.length);
		
		//Testing the derivative of polynomial from power of 0:
		po3 = Ex2.derivative(dp1);
		dp2 = Ex2.derivative(po3);
		assertEquals(dp2[0] ,0);
		
	}
	
	@Test
	void testPolynomFromPoints12() {
		
		//Testing simple example:
		double [] xx = {-5,3,10} , yy = {4,-4,4};
		double [] po3 = Ex2.PolynomFromPoints(xx, yy);
		assertEquals(-3.14285, po3[0], Ex2.EPS);
		assertEquals(-0.71428, po3[1], Ex2.EPS);
		assertEquals(0.14285, po3[2], Ex2.EPS);
		
		//Testing cases that the input was'nt correct (the length of xx is more then 3):
		xx = Ex2.mul(xx, yy);
		po3 = Ex2.PolynomFromPoints(xx, yy);
		assertEquals(null , po3);
		}
	
	@Test
	void testSwap13() {
		
		//testing if adding polynomial (represented first place in string) to itself, 
		//is equal to multiplying it by 2:
		String s_po3 = "4x^2 -x +4";
		double [] po3 = Ex2.getPolynomFromString(s_po3);
		double [] po3A = Ex2.add(po3, po3);
		double [] poBy2 = {2};
		double []po3M = Ex2.mul(po3, poBy2);
		String res = "8x^2 -2x +8";
		assertEquals(res.equals(Ex2.poly(po3M)) , res.equals(Ex2.poly(po3A)));
		
	}
	
}


	
