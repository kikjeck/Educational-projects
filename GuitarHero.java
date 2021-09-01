public class GuitarHero {

    public static void main(String[] args) {

       String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
       GuitarString[] strings = new GuitarString[37];
       double hz = 440;
       int ind;
       StdDraw.setXscale(0, 200);
       StdDraw.setYscale(-1, 1);
       double x0 = 0;
       double sample0 = 0;
       double sample1 = 0;
       double y0 = 0;
       double y1 = 0;

       //StdDraw.enableDoubleBuffering();
       for (int i = 0; i < 37; i++)
       {
         strings[i] = new GuitarString(hz * Math.pow(2, (i - 24) / 12.0));
       }

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
            //sample0 = sample1;
            //sample1 = sample;

            // send the result to standard audio
            StdAudio.play(sample);
            // StdDraw.line(x0, sample0, x0+0.1, sample1);
            // x0 += 1;
            // if (x0 > 200) x0 = 0;
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
