package hw4;

/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
import org.junit.*;
import static org.junit.Assert.*;


/**
 *  Title: class MyStackTester
 *  Description: JUnit test class for MyStack class
 *  @author Kuangyi Yang
 *  @version 4.0
 *  @since 2015-10-24 
 * */

public class MyStackTester
{
  private MyStack<Integer>  sizeZero ;
  private MyStack<Integer>  sizeOne ;
  private MyStack<Integer>  sizeTwo ;

  
  /**
   * Standard Test Fixture. An empty stack, a stack with one entry (0),
   * a stack with two entries (1,2)
   */ 
  @Before
  public void setUp()
  {
    sizeZero = new MyStack<Integer>(); 
    sizeOne = new MyStack<Integer>();  
    sizeOne.add(0);
    sizeTwo = new MyStack<Integer>();

    for (int i = 2; i > 0; i--)
    {
      sizeTwo.add(i);
    }
  }
  

  /** Test if size of stack are correct */
  @Test
  public void testStackSize()
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

  /** Test size of get method */
  @Test
  public void testSizeGet()
  {
    int size = sizeTwo.size() ;
    sizeTwo.get() ;
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

  /** Test return of remove method */
  @Test
  public void testReturnGet()
  {
    assertEquals("Test return of remove sizeOne", new Integer(0), sizeOne.get()) ;
    assertEquals("Test return of remove sizeTwo", new Integer(1), sizeTwo.get()) ;
  }
}

