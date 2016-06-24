package hw4;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */

public interface DoubleEndedLLInterface<T> {
 
	// returns true if the list is empty, false otherwise
    public boolean isEmpty();
 
    // returns the number of elements in the list
    public int size();
 
    //adds a new node to the front of the list
    public void addFirst(T newItem);
 
    //adds a new node to the end of the list 
    public void addLast(T newItem);
 
   //removes a node from the end of the list
    public T removeFirst();
    
  //removes a node from the end of the list
    public T removeLast();
    
 
}