public class GuitarString
{
  private int times = 0;
  RingBuffer ring;
  private int N; // длина буфера
  private double const_ugas = 0.994; //0.994
  public GuitarString(double frequency) // создает гитарную струну с данной частотой, используя частоту дискретизации of 44,100
  {
    N = (int) Math.round(44100 / frequency);
    ring = new RingBuffer(N);
    for (int i = 0; i < N; i++)
    {
      ring.enqueue(0);
    }
  }

  public GuitarString(double[] init) // создает гитарную струну, размер и  значения которой задаются массивом-параметром init
  {
    ring = new RingBuffer(init.length);
    N = init.length;
    for (int j = 0; j < N; j++)
    {
      ring.enqueue(init[j]);
    }
  }

  public void pluck() // записывает в буфер белый шум
  {
    for (int i = 0; i < N; i++)
    {
      ring.dequeue();
      ring.enqueue(Math.random() - 0.5);
    }
  }

  public void tic() // производит один временной шаг симуляции
  {
   double a1 = ring.dequeue();
   double a2 = ring.peek();
   double a3 = (a1 + a2) * 0.5 * const_ugas;
   ring.enqueue(a3);
   times++;
  }

  public double sample() // возвращает текущий сэмпл
  {
   return ring.peek();
  }

  public int time() // возвращает количество сделанных tic-ов
  {
    return times;
  }
}
