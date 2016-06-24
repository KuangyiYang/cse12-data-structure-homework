package hw4;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */

import java.util.*;

public class MyStack<T> implements Stack_QueueInterface<T>
{
	private DoubleEndedLL<T> stack = new DoubleEndedLL<T>();

	// method to check if stack is empty
	@Override
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}

	// method to add the element in the top of the stack
	@Override
	public void add(T newItem)
	{
		stack.addFirst(newItem);
	}

	// method to remove the element from the top and return it
	@Override
	public T get()
	{
		//if (isEmpty())
			//throw new EmptyCollectionException ("stack");
		return stack.removeFirst();
	}

	// method to get the numbers in the stack
	@Override
	public int size()
	{
		return stack.size();
	}

}