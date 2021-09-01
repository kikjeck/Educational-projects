/******************************************************
*
* Author: Evgeniy Veselkov
*
* Compilation:  javac GuitarHero.java
* Execution:    java GuitarHero
*
* Выводит графически симуляцию игры на гитаре
*
****************************************************/
public class GuitarHeroVisualizer {

    public static void main(String[] args) {

       String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
       GuitarString[] strings = new GuitarString[37];
       double hz = 440;
       int ind;
       StdDraw.setCanvasSize(1000, 400);
       StdDraw.setXscale(0, 200);
       StdDraw.setYscale(-1, 1);
       double x0 = 0;
       double sample0 = 0;
       double sample1 = 0;
       double sample2 = 0;
       double y0 = 0;
       double y1 = 0;

       //StdDraw.enableDoubleBuffering();
       for (int i = 0; i < 37; i++)
       {
         strings[i] = new GuitarString(hz * Math.pow(2, (i - 24) / 12.0));
       }

        // Create two guitar strings, for concert A and C
        //double[] CONCERT = new double[37];
        //double CONCERT_A = 440.0;
        //double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        //GuitarString stringA = new GuitarString(CONCERT_A);
        //GuitarString stringC = new GuitarString(CONCERT_C);



        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                ind = keyboard.indexOf(key);
                if (ind == -1) continue;
                else strings[ind].pluck();
                //if (key == 'a') { stringA.pluck(); }
                //if (key == 'c') { stringC.pluck(); }
            }

            // compute the superposition of the samples
            double sample = 0.0;
            for (int x = 0; x < 37; x++)
            {
              sample += strings[x].sample();
            }
            sample0 = sample1;
            sample1 = sample2;
            sample2 = sample;


            // send the result to standard audio
            //StdAudio.play(sample);
            double step = 0.5;
            double max = 200;
            //StdDraw.line(x0, ((sample0 + sample1) / 2), x0+step, ((sample1+sample2) / 2));
            StdDraw.line(x0, sample0, x0+step, sample1);
              // StdDraw.point(x0+0.1, (sample0 + sample1) / 2);
              // StdDraw.point(x0, sample0);
              // StdDraw.point(x0+0.2, sample1);
              // StdDraw.point(x0+0.4, sample2);
              // StdDraw.point(x0+0.3, (sample1 + sample2) / 2);
            x0 += step;
            if (x0 > max)
            {
              StdDraw.clear();
              x0 = 0;
            }
            // //StdOut.println(strings[0].sample());
            //StdDraw.show();
            //StdDraw.pause();
            //StdDraw.clear();
            // advance the simulation of each guitar string by one step
            for (int x = 0; x < 37; x++)
            {
              strings[x].tic();
            }
        }
    }

}
