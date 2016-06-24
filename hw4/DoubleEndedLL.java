package hw4;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */

/**
 *  Title: class DoubleEndedLL
 *  Description: a class to create double linkedlist and do some operation
 *  @author Kuangyi Yang
 *  @version 4.0
 *  @since 2015-10-24 
 * */
public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T>
{
  private int nelems;
  private Node head;
  private Node tail;

  protected class Node {
      T data;
      Node next;
      Node prev;
      
      /** Constructor to create singleton Node
        * @param element Element to add
        */
      public Node(T element)
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
      public Node(T element, Node prevNode, Node nextNode)
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
      public void setElement(T e)
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
      public T getElement()
      {
        return data;
      } 
  }  

  /** Constructor to create double ended linkedlist
    */
  public DoubleEndedLL()
  {
    head = new Node(null);
    tail = new Node(null);

    head.setNext(tail);
    tail.setPrev(head);

    nelems = 0;
  }

  /** check if linkedlist is empty
    *  @return true if linkedlist is empty
    */
  @Override
  public boolean isEmpty()
  {
    return nelems == 0;
  }

  /** returns the number of elements in the list
    *  @return size of linkedlist
    */
  @Override
  public int size()
  {
    return nelems;
  }

  /**  adds a new node to the front of the list
    *  @param newItem item to add to the front of linkedlist
    */
  @Override
  public void addFirst(T newItem) throws NullPointerException
  {
    if (newItem == null)
      throw new NullPointerException();
    Node where = getNth(0);
    new Node(newItem, where.getPrev(), where);
    nelems = nelems + 1;
  }

  /**  adds a new node to the end of the list
    *  @param newItem item to add to the end of the list 
    */
  @Override
  public void addLast(T newItem) throws NullPointerException
  {
    if (newItem == null)
      throw new NullPointerException();
    Node where = getNth(size());
    new Node(newItem, where.getPrev(), where);
    nelems = nelems + 1;
  }

  /**  removes a node from the front of the list
    *  @return returns the first node of the list
    */
  @Override
  public T removeFirst()
  {
    Node where = getNth(0);
    where.remove();
    nelems = nelems - 1;

    return where.getElement();
  }

  /**  removes a node from the end of the list
    *  @return returns the last node of the list 
    */
  @Override
  public T removeLast()
  {
    Node where = getNth(size());
    where.remove();
    nelems = nelems - 1;

    return where.getElement();
  }

  /**  get the nth node of list
    *  @param index index of the nth node
    *  @return returns the nth node of the list 
    */
  private Node getNth(int index)
    {
      Node temp = head;
      for (int i = 0; i <= index; i++)
      {
        temp = temp.getNext();
      }
      return temp;
    } 
}
