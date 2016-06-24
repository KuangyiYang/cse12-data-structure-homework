package hw4;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */

/**
 *  Title: class Location
 *  Description: a class to create a location object
 *  @author Kuangyi Yang
 *  @version 4.0
 *  @since 2015-10-24 
 * */
public class Location {

	// define the row index of location
	protected int row;
	// define the column index of location
	protected int column;

	   /** Constructor to create a location object
        * @param currRow row of current location
        * @param currCol column of current location
        */
	   public Location(int currRow, int currCol) {
	   	  // initialize the index of row of location
	      row = currRow;
	      // initialize the index of column of location
	      column = currCol;
	   }

	   /** get the first index(row) of location
        *  @return row index of row of current location
        */
	   public int getRow() {
	   	  // return index of row 
	      return row;
	   }
	  
	   /** get the second index(column) of location
        *  @return row index of column of current location
        */
	   public int getColumn() {
	   	  // return index of column
	      return column;
	   }

	   
	   /* LEFT, UP, RIGHT, DOWN */
	   
	   /** get the index of left position of current location
        *  @return index of position to the left
        */
	   public Location left() {
	   	     // return index of position to the left
		     return new Location(row,column-1);
	   }
	   
	   /** get the index of up position of current location
        *  @return index of position to the up
        */
	   public Location up() {
	   	  // return index of position to the up
	      return new Location(row-1,column);
	   } 
	   
	   /** get the index of right position of current location
        *  @return index of position to the right
        */
	   public Location right() {
	   	      // return index of position to the right
		      return new Location(row,column+1);   
	      
	   }

	   /** get the index of down position of current location
        *  @return index of position to the down
        */
	   public Location down() {
	   	  // return index of position to the down
	      return new Location(row+1,column);
	   }


	
}
