package hw4;

/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
import org.junit.*;
import static org.junit.Assert.*;


/**
 *  Title: class DoubleEndedLLTester
 *  Description: JUnit test class for DoubleEndedLL class
 *  @author Kuangyi Yang
 *  @version 4.0
 *  @since 2015-10-24 
 * */

public class DoubleEndedLLTester
{
  private DoubleEndedLL<Integer>  sizeZero ;
  private DoubleEndedLL<Integer>  sizeOne ;
  private DoubleEndedLL<Integer>  sizeTwo ;
  private DoubleEndedLL<Integer>  sizeThree;

  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (1),
   * a list with two entries (1,2), a list with (1,2,3)
   * and a list with string("head", "tail")
   */ 
  @Before
  public void setUp()
  {
    sizeZero = new DoubleEndedLL<Integer>(); 
    sizeOne = new DoubleEndedLL<Integer>();  
    sizeOne.addFirst(0);
    sizeTwo = new DoubleEndedLL<Integer>();

    for (int i = 2; i > 0; i--)
    {
      sizeTwo.addFirst(i);
    }
    
    sizeThree = new DoubleEndedLL<Integer>();
    for (int i = 3; i > 0; i--)
    {
      sizeThree.addFirst(i);
    }

  }
  

  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size", 0, sizeZero.size()) ;
    assertEquals("Check One Size", 1, sizeOne.size()) ;
    assertEquals("Check Two Size", 2, sizeTwo.size()) ;
  }

  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("sizeZero is empty",sizeZero.isEmpty()) ;
    assertTrue("sizeOne is not empty",!sizeOne.isEmpty()) ;
    assertTrue("sizeTwo is not empty",!sizeTwo.isEmpty()) ;
  }

  /** Test null pointer exception on add */
  @Test
  public void testAddNullException()
  {
    try 
    {
      sizeOne.addFirst(null);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(NullPointerException e)
    {
      //  normal
    }
  }

  /** Test size of remove method */
  @Test
  public void testSizeRemove()
  {
    int size = sizeTwo.size() ;
    sizeTwo.removeFirst() ;
    int newSize = sizeTwo.size() ;
    assertEquals("Test Size of Remove", (size - 1), newSize) ;
  } 

  /** Test add method */
  @Test
  public void testAdd()
  {
    int size = sizeTwo.size() ;
    sizeTwo.addFirst(2) ;
    int newSize = sizeTwo.size() ;
    assertTrue("Test Add", newSize == (size + 1)) ;
  }

  /** Test return of remove method */
  @Test
  public void testReturnRemove()
  {
    assertEquals("Test return of remove sizeOne", new Integer(0), sizeOne.removeFirst()) ;
    assertEquals("Test return of remove sizeTwo", new Integer(1), sizeTwo.removeFirst()) ;
  }
  
}
