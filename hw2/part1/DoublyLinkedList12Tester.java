package hw2;

/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ListIterator;


/**
 *  Title: class DoublyLinkedListTester12
 *  Description: JUnit test class for DoublyLinkedList12 class
 *  @author Kuangyi Yang
 *  @version 2.0
 *  @since 2015-10-08 
 * */

/*
 * You should modify the information above to add your name and CSE12 account
 * 
 * 
 */
public class DoublyLinkedList12Tester
{
  private DoublyLinkedList12  sizeZero ;
  private DoublyLinkedList12  sizeOne ;
  private DoublyLinkedList12  sizeTwo ;
  private DoublyLinkedList12  sizeThree;
  private DoublyLinkedList12 stringList ;

  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (1),
   * a list with two entries (1,2), a list with (1,2,3)
   * and a list with string("head", "tail")
   */ 
  @Before
  public void setUp()
  {
    sizeZero = new DoublyLinkedList12(); 
    sizeOne = new DoublyLinkedList12();  
    sizeOne.add (0,1);  //0 is an index, 1 is an element to add.
    sizeTwo = new DoublyLinkedList12();

    for (int i = 2; i > 0; i--)
    {
      sizeTwo.add(0, i);
    }
       
    stringList = new DoublyLinkedList12 () ;
    stringList.add(0, "head") ;
    stringList.add(1, "tail") ;
    
    sizeThree = new DoublyLinkedList12();
    for (int i = 3; i > 0; i--)
    {
      sizeThree.add(0, i);
    }

  }
  
  /** Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check head of sizeOne", 1, sizeOne.get(0)) ;
    assertEquals("Check head of sizeTwo", 1, sizeTwo.get(0)) ;
  }
  
  /** Test if tail of the lists are correct */
  @Test
  public void testGetTail()
  {
    assertEquals("Check Tail", 2, sizeTwo.get(1));
  }

  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size", 0, sizeZero.size()) ;
    assertEquals("Check One Size", 1, sizeOne.size()) ;
    assertEquals("Check Two Size", 2, sizeTwo.size()) ;
  }
  
  /** Test set */
  @Test
  public void testSet()
  {
    stringList.set(1, "new") ;
    assertEquals("Check set", "new", stringList.get(1));
  }

  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("sizeZero is empty",sizeZero.isEmpty()) ;
    assertTrue("sizeOne is not empty",!sizeOne.isEmpty()) ;
    assertTrue("sizeTwo is not empty",!sizeTwo.isEmpty()) ;
  }

  /** Test out of bounds exception on get */
  @Test
  public void testGetException()
  {
    try 
    {
      sizeZero.get(0);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }

  /** Test null pointer exception on add */
  @Test
  public void testAddNullException()
  {
    try 
    {
      sizeOne.add((Object) null);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(NullPointerException e)
    {
      //  normal
    }
  }

  /** Test out of bounds exception on get */
  @Test
  public void testAddOutOfBoundException()
  {
    try 
    {
      sizeZero.add(1, 1);
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }
   
  /** Test clear method */
  @Test
  public void testClear()
  {
    sizeTwo.clear() ;
    assertTrue("sizeTwo is empty", sizeTwo.isEmpty()) ;
  }

  /** Test size of remove method */
  @Test
  public void testSizeRemove()
  {
    int size = sizeTwo.size() ;
    sizeTwo.remove(1) ;
    int newSize = sizeTwo.size() ;
    assertEquals("Test Size of Remove", (size - 1), newSize) ;
  } 

  /** Test add method */
  @Test
  public void testAdd()
  {
    int size = sizeTwo.size() ;
    sizeTwo.add(2) ;
    int newSize = sizeTwo.size() ;
    assertTrue("Test Add", newSize == (size + 1)) ;
  }

  /** Test getNth method */
  @Test
  public void testGetNth()
  {
    assertEquals("Test get0th", 1, sizeTwo.get(0)) ;
    assertEquals("Test get1st", 2, sizeTwo.get(1)) ;
  }

  /** Test return of remove method */
  @Test
  public void testReturnRemove()
  {
    assertEquals("Test return of remove sizeOne", 1, sizeOne.remove(0)) ;
    assertEquals("Test return of remove sizeTwo", 2, sizeTwo.remove(1)) ;
  }

  /** Test return of set method */
  public void testReturnSet()
  {
          Object test = sizeTwo.set(2, 6);
          assertEquals("Test return of Set", test, new Integer(6));
  }
  
  /** Test next Method in iterator */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator iter;
    for (iter = sizeZero.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = sizeTwo.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterating several list", counter, 2) ;

  }
  
  /** Test previous Method in iterator */
  @Test
  public void testIteratorPrevious()
  {
    ListIterator iter;
    iter = sizeTwo.listIterator();
    
    while (iter.hasNext())
    {
      iter.next();
    }
    
    assertTrue(iter.hasPrevious());
    assertEquals("Check last element", new Integer(2), iter.previous());
    
    assertTrue(iter.hasPrevious());
    assertEquals("Check the one before last element", new Integer(1), iter.previous());
    
    assertFalse(iter.hasPrevious());
  }
  
  /** Test Add Method in iterator */
  @Test
  public void testIteratorAdd()
  {
    ListIterator iter = sizeZero.listIterator();
    iter.add(1);
    iter.add(2);
    iter.add(3);
    iter.previous();
    assertEquals("Check add", 2, iter.previous());
    assertEquals("sizeZero increments to 3", 3, sizeZero.size());
  }
  
  /** Test hasNext method in iterator */
  @Test
  public void testIteratorHasNext() {
  ListIterator iter = sizeThree.listIterator();
    assertTrue("hasNext", iter.hasNext());
  }

  /** Test hasPrevious method in iterator */
  @Test
  public void testIteratorHasPrevious() {
  ListIterator iter = sizeThree.listIterator();
    assertFalse("Check hasPrevious", iter.hasPrevious());    
    iter.next();
    assertTrue("Has previous", iter.hasPrevious());
  }
  
  /** Test next method in iterator */
  @Test
  public void testIteratorNext() {
    ListIterator iter = sizeTwo.listIterator();
    assertEquals("next is 1", 1, iter.next());
  }
  
  /** Test nextIndex method in iterator */
  @Test
  public void testIteratorNextIndex() {
  ListIterator iter = sizeTwo.listIterator();
    iter.next();
    assertEquals("nextIndex is 2", 1, iter.nextIndex());
  }

  /** Test previousIndex method in iterator */
  @Test
  public void testIteratorPreviousIndex() {
  ListIterator iter = sizeThree.listIterator();
    iter.next();
    assertEquals("previousIndex is 0", 0, iter.previousIndex());
    iter.next();
    assertEquals("previousIndex is 1", 1, iter.previousIndex());
  }

  /** Test remove method in iterator */
  @Test
  public void testIteratorRemove() {
    ListIterator iter = sizeThree.listIterator();
    try {
      iter.remove();
      fail ("should have generated an exception");
    } catch (IllegalStateException e) {
      // normal
    }
    iter.next();
    iter.remove();
    assertEquals("element at index 0 of sizeThree is 2", 2, sizeThree.get(0));
    assertEquals("size of sizeThree is 2", 2, sizeThree.size());
  }

  /** Test set method in iterator */
  @Test
  public void testIteratorSet() {
  ListIterator iter = sizeTwo.listIterator();
    try {
      iter.set(5);
      fail ("should have generated an exception");
    } catch (IllegalStateException e) {
      // normal
    }
    iter.next();
    try {
      iter.set(null);
      fail ("should have generated an exception");
    } catch (NullPointerException e) {
      // normal
    }
    iter.set(5);
    assertEquals("first element of sizeTwo is 5", 5, sizeTwo.get(0));
    iter.next();
    iter.previous();
    iter.set(10);
    assertEquals("second element of sizeTwo is 10", 10, sizeTwo.get(1));
  }
  
}
