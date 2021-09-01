/******************************************************
*
* Author: Evgeniy Veselkov
*
* Compilation:  javac NBody.java
* Execution:    java NBody T delta_t < planets.txt
*
* Выводит симуляцию солнечной системы, но данные планет можно изменить.
*
*
****************************************************/
public class NBody {
 public static void main(String[] args) {
	 double T = Double.parseDouble(args[0]);		//Длительность симуляции
	 double delta_t = Double.parseDouble(args[1]);
	 int N = StdIn.readInt();					// Число планет
	 double R = StdIn.readDouble();

	 StdDraw.setXscale(-R, +R);						// Размеры вселенной
	 StdDraw.setYscale(-R, +R);

	 String imageToDraw = "images/starfield.jpg";
	 double data[][] = new double[N][5];
	 String data_gif[] = new String[N];
	 double G = 6.67 * Math.pow(10, -11);

	 StdDraw.picture(0, 0, "starfield.jpg" );

	 // Переводим данные в двумерный массив и названия изображений в другой массив
	 for (int i = 0; i < N; i++)
	 {
		 for (int j = 0; j < 5; j++)
		 {
			 data[i][j] = StdIn.readDouble();
		 }
		 data_gif[i] = StdIn.readString();
	 }
	 for (int n = 0; n < N; n++)
	 {
		 StdDraw.picture(data[n][0], data[n][1], data_gif[n] );
	 }

	 StdAudio.play("2001.wav");
	 StdDraw.enableDoubleBuffering();  // Начинаем анимацию
	 while (delta_t < T)
	 {
		// Для каждой планеты k расчитываем её взимадоействие с остальными планетами
		 for (int k = 0; k < N; k++)
		 {

			 double Fx_sum = 0;
			 double Fy_sum = 0;
			 for (int l = 0; l < N; l++)
			 {

				 if (k == l) continue;
				 double dx = (data[l][0] - data[k][0]);
				 double dy = (data[l][1] - data[k][1]);
				 double m1 = data[k][4];
				 double m2 = data[l][4];
				 double r = Math.sqrt((dx) * (dx) +  (dy) * (dy)); // дистанция между планетами
				 double F = ((G * m1 * m2) / (r*r));
				 double Fx = ((F * dx) / r);
				 double Fy = ((F * dy) / r);
				 Fx_sum = (Fx_sum + Fx);
				 Fy_sum = (Fy_sum + Fy);
			 }
			 double a_x = Fx_sum / data[k][4];						// Задаём ускорение
			 double a_y = Fy_sum / data[k][4];
			 data[k][2] = (data[k][2] + delta_t * a_x);
			 data[k][3] =  data[k][3] + delta_t * a_y;
			 data[k][0] = data[k][0] + delta_t * (data[k][2]);   //Задаём новую координату
			 data[k][1] = data[k][1] + delta_t * (data[k][3]);
			 StdDraw.picture(data[k][0], data[k][1], data_gif[k]);


		 }
     // Обновляем картинку
		 StdDraw.show();
		 StdDraw.pause(1);
		 StdDraw.clear();
		 StdDraw.picture(0, 0, "starfield.jpg" );


		 T = T - delta_t;


	 }

	 // Выводим данные планет после симуляции
	 for (int i = 0; i < N; i++)
	 {
		 for (int j = 0; j < 5; j++)
		 {
			 System.out.printf("%e\t", data[i][j]);
		 }
		 System.out.println(data_gif[i] + "\n");
	 }
 }
}
