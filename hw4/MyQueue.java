package hw4;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
public class MyQueue<T> implements Stack_QueueInterface<T>
{
	private T [] queue;
	int front;
	int rear;
	int size;
	int defaultSize = 100;

	// Constructor to create a queue
	@SuppressWarnings("unchecked")
	public MyQueue()
	{
		queue = (T[]) (new Object[defaultSize]);
		front = 0;
		rear = 0;
		size = 0;
	}

	// method to check if queue is empty
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	// method to get numbers in queue
	@Override
	public int size()
	{
		return size;
	}

	// method to add the element in the rear of queue
	@Override
	public void add(T newItem)
	{
		if (size() == queue.length)
			enlargeSize();

		queue[rear] = newItem;
		rear = (rear + 1) % queue.length;
		size = size + 1;
	}

	// method to remove the element in the front of queue and return it
	@Override
	public T get()
	{
		//if (isEmpty())
			//throw new EmptyCollectionException ("queue");
		size--;
		T elt = queue[front];
		front = (front + 1) % queue.length;
		return elt;
	}

	// double the size of queue
	private void enlargeSize()
	{
		@SuppressWarnings("unchecked")
		T[] newQueue = (T[]) (new Object[queue.length*2]);
		for (int i = 0; i < size; i++)
		{
			newQueue[i] = queue[front];
			front = (front + 1) % queue.length;
		}

		front = 0;
		rear = size;
		queue = newQueue;
	}
}