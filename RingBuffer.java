import java.util.Arrays;
public class RingBuffer
{
	private double[] n;
	private int first;
	private int last;
	private int size = 0; // количество элементов в буфере
	private int capacity; // емкость буфера
  public	RingBuffer(int capacity0) // создать пустой кольцевой буфер с максимальной емкостью, равной capacity
  	{
			capacity = capacity0;
			n = new double[capacity];
			first = 0;
			last = 0;
   	}
  public String toString() // возвращает строку - буффер
	{
		double[] N = new double[size];
		if (size == 0) return Arrays.toString(N);
		int k = 0;
		if (last <= first)
		{
			for (int d = 0; d < first; d++)
			{
				N[k+(capacity - first)] = n[d];
				k++;
			}
			k =0;
			for (int dd = first; dd < capacity; dd++)
			{
				N[k] = n[dd];
				k++;
			}
		}
		if (last > first)
		{
			for (int i = first; i < last; i++)
			{
				N[k] = n[i];
				k++;
			}
		}
		return Arrays.toString(N);
	}

  public int size() // возвратить количество элементов, находящихся в буфере
    {
			return size;
		}
  public boolean isEmpty() // буфер пуст? (size равен 0?)
  	{
			if (size == 0) return true;
  		else return false;
		}
  public boolean isFull() // буфер полон? (size равен capacity?)
	 	{
	 		if (size == capacity) return true;
	 		else return false;
	 	}
	public void enqueue(double x) // добавить элемент x в конец буфера
	 	{
			if (size == capacity) throw new RuntimeException("Enqueue: buffer full");
	 		n[last] = x;
	 		last++;
	 		if (last == capacity) last = 0;
			size++;
		}
  public double dequeue() // стереть элемент из начала буфера и возвратить его
	   {
			if (size == 0) throw new RuntimeException("Dequeue: empty buffer");
	 		double deq = n[first];
	 		//n[first] = 0;
	 		first++;
	 		if (first == capacity) first = 0;
	 		size--;
	 		return deq;
	 	}
	public double peek() // возвратить (не стирая) элемент из начала буфера
	{
		 if (size == 0) throw (new RuntimeException("Peek: empty buffer") );
			double deq = n[first];
			return deq;
		}
}
