/******************************************************
*
* Author: Evgeniy Veselkov
*
* Compilation:  javac Quilt.java
* Execution:    java Quilt numBlocks numFrames numCircles
*
* Выводит принт одеяла, состоящий из 5-ти рисунков.
*
*
****************************************************/
public class Quilt
{
  public static void main(String[] args)
	{
	  int numBlocks = Integer.parseInt(args[0]);
	  int numFrames = Integer.parseInt(args[1]);
	  int numCircles = Integer.parseInt(args[2]);
	  int num_draw = 5;
	  StdDraw.setCanvasSize(768, 768);
	  StdDraw.setXscale(- 2, numBlocks + 1);
	  StdDraw.setYscale( - numBlocks - 1, 2);
	  double k;
	  int pause = 1000;
	  /*
	  int place_petal = 0; // Доделать, там не нули!
	  int place_cabin = 1;
	  int place_PetalandCabin = 2;
	  int place_krest = 3;
	  int place_aim = 4;
	  */
	  // k = 0.2 
	 for (int d = 0; d < (numBlocks * numBlocks); d++)
	 {
		 int x =  d % (numBlocks); // x = 0, 1, ..., numBlocks - 1
		 int y = - d / (numBlocks); // y = 0, - 1, ..., - numBlocks + 1
		 int Ostatok = (x + y) % num_draw; // Помогает понять расположение
		 int negOstatok = (- y - x) % num_draw;
		 
		 // Строим клетку, далее проверяем, что туда разместить.
		 StdDraw.square(x, y, 0.5); 
		 if ((Ostatok == 0)) petal(x, y);
		 if ((Ostatok == 1) || (negOstatok == 4)) cabin(x, y, numFrames, numFrames);
		 if ((Ostatok == 2) || (negOstatok == 3)) PetalandCabin(x, y, numFrames);
		 //if ((Ostatok == 3) || (negOstatok == 2)) krest(x, y, k);
		 if ((Ostatok == 4) || (negOstatok == 1)) aim(x , y, numBlocks, numCircles);
	 }
	 StdDraw.enableDoubleBuffering();
	 while (true){ // Случайным образом выбирает рисунок, одновременно заполняет клетки этим рисунком, повторяет через некоторое время pause
		 k = Math.random() / 2;
		for (int d = 0; d < (numBlocks * numBlocks); d++) // Скрипт заполнения всех клеток
		{
		 int x =  d % (numBlocks); // x = 0, 1, ..., numBlocks - 1
		 int y = - d / (numBlocks); // y = 0, - 1, ..., - numBlocks + 1
		 int Ostatok = (x + y) % num_draw;
		 int negOstatok = (- y - x) % num_draw;
		 
		 if ((Ostatok == 3) || (negOstatok == 2))
		 {
			 StdDraw.setPenColor(StdDraw.WHITE);
			 StdDraw.filledSquare(x, y, 0.5);
			 StdDraw.setPenColor(StdDraw.BLACK);
			 krest(x, y, k);
		 }
		}
		StdDraw.pause(pause);
		StdDraw.show();
	 }
	}
	public static void aim(int x,int y, int numBlocks, int numCircles){
		for (double j = 1; j < numCircles + 1; j++)
		{
		
		double x1 = x;
		double y1 = y;
		double numCircles1 = numCircles;
		StdDraw.circle(x1, y1, j / (2 * (numCircles1 + 1)));
		}
	}
	public static void petal(int x,int y){
		double x1 = x;
		double y1 = y;
		double r = 0.25;
		StdDraw.arc(x1, y - r, r, 0, 180);
		StdDraw.arc(x1, y + r, r, 180, 0);
		StdDraw.arc(x1 + r, y, r, 90, 270);
		StdDraw.arc(x1 - r, y, r, 270, 90);
	}
	
	public static void cabin(int x, int y, int numFrames, int numFrames_fact){
		double x1 = x;
		double y1 = y;
		double numFrames1 = numFrames;
		double frameWidth = 1 / (2 * numFrames1 + 1 );
		double lx = x1 - 0.5; // Координаты верхнего левого угла квадрата
		double uy = y1 + 0.5;	
		for (int k = 1; k < numFrames_fact + 1; k++)
		{
			double halfLength = (1 - frameWidth * (2 * (k - 1) + 1)) / 2;
			double halfWidth = frameWidth / 2;
			StdDraw.rectangle(lx + halfWidth + (k - 1) * frameWidth, uy - (1 - frameWidth) / 2 , halfWidth, halfLength);			// Левые блоки
			StdDraw.rectangle(lx + 1 - halfWidth - (k - 1) * frameWidth, uy - 1 + (1 - frameWidth) / 2, halfWidth, halfLength);	// Правые блоки
			StdDraw.rectangle(lx + 1 - (1 - frameWidth) / 2, uy - halfWidth - (k - 1) * frameWidth, halfLength, frameWidth / 2 );		// Верхние блоки
			StdDraw.rectangle(lx  + (1 - frameWidth) / 2, uy - 1 + halfWidth + (k - 1) * frameWidth, halfLength, frameWidth / 2);		// Нижние блоки
		}
		
	}
	public static void PetalandCabin(int x, int y, int numFrames){
		cabin(x, y, numFrames, 1);
		petal(x, y);
	}
	public static void krest(int x, int y, double k){
														// Сделать анимацию по k
		double x1 = x;
		double y1 = y;
		double[] xcor = {x1 - k, x1 + k, x1, x1};		// Снизу
		double[] ycor = {y1 - 0.5, y1 - 0.5, y1, y1};
		StdDraw.filledPolygon(xcor, ycor);
		double[] xcor2 = {x1 - k, x1 + k, x1, x1};		// Сверху
		double[] ycor2 = {y1 + 0.5, y1 + 0.5, y1, y1};
		StdDraw.filledPolygon(xcor2, ycor2);
		double[] xcor3 = {x1 - 0.5, x1 - 0.5, x1, x1};
		double[] ycor3 = {y1 + k, y1 - k, y1, y1};		// Right
		StdDraw.filledPolygon(xcor3, ycor3); 			// left	
		double[] xcor4 = {x1 + 0.5, x1 + 0.5, x1, x1};
		double[] ycor4 = {y1 + k, y1 - k, y1, y1};
		StdDraw.filledPolygon(xcor4, ycor4);
		
	}
  }

