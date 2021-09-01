/******************************************************
*
* Author: Evgeniy Veselkov
*
* Compilation:  javac Art.java
* Execution:    java Art n
*
* Выводит забор(?) с узором
* n - the depth of recursion
*
****************************************************/
public class Art{
	public static void main(String[] args){
		int n = Integer.parseInt(args[0]); // n - the depth of recursion
		double r = 1;
		int k = 1;
		double x0 = 0;
		double y0 = 0;
		double scale = 4.0;
		StdDraw.setCanvasSize(768, 768);
		StdDraw.setXscale(-scale, scale);
		StdDraw.setYscale(-scale, scale);
		rec(x0, y0, r, n, k);

	}
	
	public static void rec(double x0, double y0, double r, int n, int k)	// первая рекурсия
	{
		if (n == 0) return;
		int rk = Math.abs(k-1);
		
		StdDraw.arc(x0, y0, r, (rk) * 180, (k) * 180);
		StdDraw.line(x0 + r, y0, x0 + r, y0 - 2*r);
		StdDraw.line(x0 - r, y0, x0 - r, y0 - 2*r);
		
		rec(x0 + 1.5 * r, y0 - r, r / 2, n - 1, k);
		int d = 0;
		rec2(x0 + 0.5 * r, y0, r / 2, n - 1, rk, d);
		d++;
		rec2(x0 - 0.5 * r, y0, r / 2, n - 1, rk, d);
		rec(x0 - 1.5 * r, y0 - r, r / 2, n - 1, k);
		
	}
	
	public static void rec2(double x0, double y0, double r, int n, int k, int d)	// вторая рекурсия - отвечает за узор
	{
		if (n==0) return;
		int rk = Math.abs(k-1);
		StdDraw.arc(x0, y0, Math.abs(r), rk * 180, k * 180);
		double r1 = - r;
		if (d==0) rec2(x0 + r1 / 2, y0, r1 / 2, n - 1, rk, d);
		else rec2(x0 - r1 / 2, y0, r1 / 2, n - 1, rk, d);
		
		
		
	}
	
}