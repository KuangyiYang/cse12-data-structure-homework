/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
package hw6;

//import java.util.*;


class dHeap <T extends Comparable <? super T>> implements dHeapInterface<T> {
	private T[] heap;
	private int size = 0;
	private int d;
			
	//The constructor  takes one argument: an initial capacity
	//Used when binary heap is needed.
	@SuppressWarnings("unchecked")
	public dHeap (int heapSize)
    {
		this.heap = (T[]) new Comparable[heapSize];
		this.d = 2;
		this.size = 0;
	}

	//The constructor takes two arguments: an initial capacity
	//and the number of children, d
	//if d is less than one, throw IllegalArgumentException();
	@SuppressWarnings("unchecked")
	public dHeap (int d, int heapSize) {
		if (d < 1)
			throw new IllegalArgumentException("d cannot be smaller than 1");
		this.heap = (T[]) new Comparable[heapSize];
		this.d = d;
		this.size = 0;
	}

	//get numbers of elements stored in heap
	public int size () {
		return size;
	}

	//insert element into the heap
	public void add (T data) {
		if (data == null)
		{
			throw new IllegalArgumentException("cannot add null");
		}
		
		if (size() == heap.length)
		{
			enlarge();
		}
		int index = size;
		int parentIndex = getParentIndex(index);
		while (index != 0 && data.compareTo(heap[parentIndex]) < 0)
		{
			heap[index] = heap[parentIndex];
			index = parentIndex;
			parentIndex = getParentIndex(index);
		}
		heap[index] = data;
		size = size + 1;
		
	}
	
	// return the top element and remove it from the heap
	public T removeSmallest () 
	{
		if (size == 0)
			return null;
		int top = 0;
		T temp = heap[top];
		heap[top] = heap[size - 1];
		size = size - 1;
		T newTemp = heap[top];
		int smallest = findSmallestChild(top * d + 1, top * d + d);
		while (smallest < size && newTemp.compareTo(heap[smallest]) > 0)
		{
			heap[top] = heap[smallest];
			top = smallest;
			smallest = findSmallestChild(smallest * d + 1, smallest * d + d);
		}
		heap[top] = newTemp;
		return temp;
	}
	
	//return index of the parent element
	private int getParentIndex(int index)
	{
		return (index - 1)/d;
	}
	
	//expand the array
	private void enlarge()
	{
		@SuppressWarnings("unchecked")
		T[] newHeap = (T[]) (new Comparable[heap.length*2]);
		for (int i = 0; i < size; i++)
		{
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}
	
	// find the smallest element in the children
	private int findSmallestChild(int start, int end)
	{
		int smallest = start;
		for (int i = start + 1; i <= end && i < size;  i++)
		{
			if (heap[smallest].compareTo(heap[i]) > 0)
			{
				smallest = i;
			}
		}
		return smallest;
	}
	
	// get element stored in index
	public T get(int index)
	{
		return heap[index];
	}

}





