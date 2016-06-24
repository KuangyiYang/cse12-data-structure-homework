package hw6;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
public class MyPriorityQueue<T extends Comparable <? super T>> {
	private dHeap<T> prioQueue;

	// Constructor use the binary heap
	public MyPriorityQueue(int size)
	{
		prioQueue = new dHeap<T> (size);
	}
	
	// add data into the priorty queue
	public boolean add(T data)
	{
		prioQueue.add(data);
		return true;
	}
	
	// return the top element and remove it from priority queue
	public T poll()
	{
		return prioQueue.removeSmallest();
	}
	
	// return the numbers of elements stored in hep
	public int size()
	{
		return prioQueue.size();
	}
	
	// get element stored in index
	public T get(int index)
	{
		return prioQueue.get(index);
	}
}
