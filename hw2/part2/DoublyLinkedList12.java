package hw2;

/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
import java.util.*;
/**
 * Title: class DoublyLinkedList12
 * Description: Implement a doubly-linked list
 * @author Kuangyi Yang
 * @version 2.0
 * @since 2015-10-08
 */

public class DoublyLinkedList12<E> extends AbstractList<E> {
  
  private int nelems;
  private Node head;
  private Node tail;
  
  protected class Node {
    E data;
    Node next;
    Node prev;
    
    /** Constructor to create singleton Node
      * @param element Element to add
      */
    public Node(E element)
    {
      data = element;
      next = null;
      prev = null;
    }
    
    /** Constructor to create singleton link it between previous and next 
      *   @param element Element to add, can be null
      *   @param prevNode predecessor Node, can be null
      *   @param nextNode successor Node, can be null 
      */
    public Node(E element, Node prevNode, Node nextNode)
    {
      data = element;
      next = nextNode;
      prev = prevNode;
      if (prevNode != null)
        prevNode.next = this;
      if (nextNode != null)
        nextNode.prev = this;
    }
    
    /** Remove this node from the list. Update previous and next nodes */
    public void remove()
    {
      
      this.getPrev().setNext(this.getNext());
      this.getNext().setPrev(this.getPrev());
      //nelems = nelems - 1;
    }
    
    /** Set the previous node in the list
      *  @param p new previous node
      */
    public void setPrev(Node p)
    {
      prev = p;
    }
    
    /** Set the next node in the list
      *  @param n new next node
      */
    public void setNext(Node n)
    {
      next = n;
    }
    
    /** Set the element 
      *  @param e new element 
      */
    public void setElement(E e)
    {
      data = e;
    }
    
    /** Accessor to get the next Node in the list 
      * @return next node
      */
    public Node getNext()
    {
      return next;
    }
    
    /** Accessor to get the prev Node in the list 
      * @return previous node
      */
    public Node getPrev()
    {
      return prev;
    } 
    
    /** Accessor to get the Nodes Element 
      * @return element
      */
    public E getElement()
    {
      return data;
    } 
  }
  
  /** ListIterator implementation */ 
  protected class MyListIterator implements ListIterator<E> {
    
    private boolean forward;
    private boolean canRemove;
    private Node left,right; // Cursor sits between these two nodes
    private int idx;        // Tracks current position. what next() would
    // return 
    public MyListIterator()
    {
      left = head;
      right = head.getNext();
      forward = true;
      canRemove = false;
      idx = 0;
    }
    
    @Override
    public void add(E e) throws  NullPointerException
    {
      if (e == null)
        throw new NullPointerException();
    

      DoublyLinkedList12.this.add(idx, e);
      left = left.getNext();
      idx = idx + 1;
      canRemove = false;
    }

    @Override
    public boolean hasNext()
    {
      return idx < nelems;
    }
    
    @Override
    public boolean hasPrevious()
    {
      return idx > 0;
    }

    @Override
    public E next() throws NoSuchElementException
    {
      if (!hasNext())
        throw new NoSuchElementException();

      right = right.getNext();
      left = right.getPrev();
      idx = idx + 1;

      forward = true;
      canRemove = true;

      return left.getElement();
    }

    @Override
    public int nextIndex()
    {
      return idx;
    }

    @Override
    public E previous() throws NoSuchElementException
    {
      if (!hasPrevious())
        throw new NoSuchElementException();
      
      left = left.getPrev();
      right = left.getNext();

      forward = false;
      canRemove = true;
      idx = idx - 1;

      return right.getElement();
    }
    
    @Override
    public int previousIndex()
    {
      return idx - 1;
    }

    @Override
    public void remove() throws IllegalStateException
    {
      if (!canRemove)
        throw new IllegalStateException();
      
      DoublyLinkedList12.this.remove(forward? idx-1 : idx);
      idx = idx - 1;
      canRemove = false;
    }

    @Override
    public void set(E e) 
      throws NullPointerException,IllegalStateException
    {
      if (e == null) throw new NullPointerException();
      if (!canRemove) throw new IllegalStateException();
      DoublyLinkedList12.this.set(forward ? idx-1: idx, e); 
    }  
  }
  
  
  //  Implementation of the DoublyLinkedList12 Class
  
  
  /** Only 0-argument constructor is define */
  public DoublyLinkedList12()
  {
    head = new Node(null);
    tail = new Node(null);

    head.setNext(tail);
    tail.setPrev(head);

    nelems = 0;
  }

  @Override
  public int size()
  {
    // need to implement the size method
    return nelems;
  }
  
  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
    if( isEmpty() || index < 0 || index > nelems)
      throw new IndexOutOfBoundsException();

    return getNth(index).getElement();
  }
  
  @Override
  /** Add an element to the list 
    * @param index  where in the list to add
    * @param data data to add
    * @throws IndexOutOfBoundsException
    * @throws NullPointerException
    */ 
    public void add(int index, E data) 
    throws IndexOutOfBoundsException,NullPointerException
  {
    if (data == null)
      throw new NullPointerException();
    if (index < 0 || index > size())
      throw new IndexOutOfBoundsException();

    Node where = getNth(index);
    new Node(data,where.getPrev(),where);
    nelems = nelems + 1;
  }
  /** Add an element to the end of the list 
    * @param data data to add
    * @throws NullPointerException throw excpetion when add null
    */ 
  public boolean add(E data) throws NullPointerException
  {
    if (data == null)
      throw new NullPointerException();
    
    this.add(this.nelems, data);

    return true;
  }
  
  /** Set the element at an index in the list 
    * @param index  where in the list to add
    * @param data data to add
    * @return element that was previously at this index.
    * @throws IndexOutOfBoundsException throw exception when out of bound
    * @throws NullPointerException throw exception when add null
    */ 
  public E set(int index, E data) 
    throws IndexOutOfBoundsException,NullPointerException
  {
    if (index < 0 || index > nelems)
      throw new IndexOutOfBoundsException();
    if (data == null)
      throw new NullPointerException();

    Node ori = getNth(index);
    E ori_data = ori.getElement(); 
    ori.setElement(data);
    return ori_data;
  }
  
  /** Remove the element at an index in the list 
    * @param index  where in the list to add
    * @return element the data found
    * @throws IndexOutOfBoundsException throw exception when index out of bound
    */ 
  public E remove(int index) throws IndexOutOfBoundsException
  {
    if (index < 0 || index > nelems)
      throw new IndexOutOfBoundsException();
    
    Node temp = getNth(index);
    temp.remove();
    nelems = nelems - 1;

    return temp.getElement();
  }
  
  /** Clear the linked list */
  public void clear()
  {
    head.setNext(tail);
    tail.setPrev(head);
    nelems = 0;
  }
  
  /** Determine if the list empty 
    *  @return true if empty, false otherwise */
  public boolean isEmpty()
  {
    return nelems == 0;  // CHANGE
  }
  
  public Iterator<E> QQQiterator()
  {
    return new MyListIterator();
  }
  public ListIterator<E> QQQlistIterator()
  {
    return new MyListIterator();
  }
  
  // Helper method to get the Node at the Nth index
  private Node getNth(int index) 
  {
    Node temp = head;
    for (int i = 0; i <= index; i++)
    {
      temp = temp.getNext();
    }
    return temp;
  }
  
  
  /*  UNCOMMENT the following when you believe your MyListIterator class is
   functioning correctly */
   public Iterator<E>  iterator()
   {
     return new MyListIterator();
   }
   public ListIterator<E>  listIterator()
   {
     return new MyListIterator();
   } 
   
}



