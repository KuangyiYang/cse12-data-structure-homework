package hw6;

/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
import org.junit.*;
import static org.junit.Assert.*;


/**
 *  Title: class dHeap
 *  Description: JUnit test class for dHeap class
 *  @author Kuangyi Yang
 *  @version 4.0
 *  @since 2015-10-24 
 * */

public class dHeapTester
{
  private dHeap<Integer>  sizeZero ;
  private dHeap<Integer>  sizeOne ;
  private dHeap<Integer>  sizeTwo ;
  private dHeap<Integer>  dZero ;
  private dHeap<Integer>  dOne ;
  private dHeap<Integer>  dFive ;

  
  /**
   * Standard Test Fixture. An empty stack, a stack with one entry (0),
   * a stack with two entries (1,2)
   */ 
  @Before
  public void setUp()
  {
    sizeZero = new dHeap<Integer>(10); 
    sizeOne = new dHeap<Integer>(10);  
    sizeOne.add(new Integer(1));
    sizeTwo = new dHeap<Integer>(10);

    sizeTwo.add(new Integer(1));
    sizeTwo.add(new Integer(2));
    
    dZero = new dHeap<Integer>(3, 10); 
    dOne = new dHeap<Integer>(4, 10);  
    dOne.add(new Integer(1));
    dFive = new dHeap<Integer>(3, 10);

    dFive.add(new Integer(1));
    dFive.add(new Integer(2));
    dFive.add(new Integer(3));
    dFive.add(new Integer(4));
    dFive.add(new Integer(5));    
   
  }
  

  /** Test if size of heap are correct */
  @Test
  public void testdHeapSize()
  {
    assertEquals("Check Empty Size", 0, sizeZero.size()) ;
    assertEquals("Check One Size", 1, sizeOne.size()) ;
    assertEquals("Check Two Size", 2, sizeTwo.size()) ;
    assertEquals("Check Empty Size", 0, dZero.size()) ;
    assertEquals("Check One Size", 1, dOne.size()) ;
    assertEquals("Check Two Size", 5, dFive.size()) ;
  }
  
  /** Test if size of add method are correct */
  @Test
  public void testAddSize()
  {
	sizeZero.add(4);
	assertEquals("Check sizeZero", 1, sizeZero.size()) ;
	sizeTwo.add(0);
    assertEquals("Check sizeTwo", 3, sizeTwo.size()) ;
    sizeTwo.add(6);
    assertEquals("Check sizeTwo", 4, sizeTwo.size()) ;
    
    dZero.add(4);
	assertEquals("Check sizeZero", 1, dZero.size()) ;
	dOne.add(0);
    assertEquals("Check sizeOne", 2, dOne.size()) ;
    dFive.add(6);
    assertEquals("Check sizeFive", 6, dFive.size()) ;
  }
  
  /** Test remove method */
  @Test
  public void testRemoveSmallest()
  {
	  assertEquals("Check sizeOne", new Integer(1), sizeOne.removeSmallest()) ;
	  assertEquals("Check sizeOne size", 0, sizeOne.size()) ;
	  assertEquals("Check sizeTwo", new Integer(1), sizeTwo.removeSmallest()) ;
	  
	  assertEquals("Check sizeOne size", 1, sizeTwo.size()) ;
	  sizeTwo.add(3);
	  assertEquals("Check sizeTwo", new Integer(2), sizeTwo.removeSmallest()) ;
	  sizeTwo.add(0);
	  assertEquals("Check sizeTwo", new Integer(0), sizeTwo.removeSmallest()) ;
	  
	  assertEquals("Check sizeOne", new Integer(1), dOne.removeSmallest()) ;
	  assertEquals("Check sizeOne size", 0, dOne.size()) ;
	  assertEquals("Check sizeFive", new Integer(1), dFive.removeSmallest()) ;
	  
	  assertEquals("Check sizeOne size", 4, dFive.size()) ;
	  dFive.add(10);
	  assertEquals("Check sizeTwo", new Integer(2), dFive.removeSmallest()) ;
	  dFive.add(0);
	  assertEquals("Check sizeTwo", new Integer(0), dFive.removeSmallest()) ;
  }

 
}

