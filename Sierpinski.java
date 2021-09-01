/******************************************************
*
* Author: Evgeniy Veselkov
*
* Compilation:  javac Sierpinski.java
* Execution:    java Sierpinski n
*
* Выводит пирамиду Серпинского
* n - the depth of recursion
*
****************************************************/
public class Sierpinski
{
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]); // n - the depth of recursion
		double a = 1;
		double x0 = 0;
		double y0 = 0;
		double scale = 2.0;
		StdDraw.setXscale(-scale, scale);
		StdDraw.setYscale(-scale, scale + a);
		
		
		double[] X = {x0, x0 - a, x0 + a};
		double[] Y = {y0 + 2 * a, y0, y0};
		StdDraw.polygon(X, Y);		// outer triangle
		
		sierpinski(x0, y0, a, n); // start of recursion
	}
	public static void triangle(double x0, double y0, double na){ // black triangle
		double[] X = {x0, x0 - na, x0 + na};
		double[] Y = {y0, y0 + 2 * na, y0 + 2 * na};
		StdDraw.filledPolygon(X, Y);
	}
	public static void sierpinski(double x0, double y0, double a, int n){ // recursion
		if (n == 0) return;
		double na = a/2;
		triangle(x0, y0, na);
		sierpinski(x0 - na, y0, na, n - 1);
		sierpinski(x0 + na, y0, na, n - 1);
		sierpinski(x0, y0 + a, na, n - 1);

			
	}
}