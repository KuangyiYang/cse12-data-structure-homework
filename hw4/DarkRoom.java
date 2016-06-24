package hw4;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class DarkRoom implements DarkRoomInterface {
	
	protected char [][] darkRoom;  
    protected int numRows=0;
    protected int numCols;
    
  	// Method to read file and store in char[][] darkRoom
	public void readFromFile(String fname) {
		
		// define a variable for reading each line from file
		String line;
		// define a buffering character-input stream reader
		BufferedReader inputStrem;
		// define a string tokenizer for a string
		StringTokenizer st;

		// if no exception try the following steps
		try {
			// start from first row
		    int currentRow = 0;
		    
		    // create a buffering character-input stream that uses an input buffer of a file
		    inputStrem = new BufferedReader(new FileReader(fname));

		    // read each line until the end of file
		    while ((line = inputStrem.readLine()) != null) {
		    	// first step is to get the number of rows and colums and initialze the darRoom
		    	if (numRows == 0) {
		    		// create a string tokenizer for a specific string  
		    		st = new StringTokenizer(line);
		    		// number of rows is the first number in first line
		    		numRows = Integer.parseInt(st.nextToken());
		    		// number of columns is the second number in the first line
		    		numCols = Integer.parseInt(st.nextToken());
		    		// initialize the darkRoom obeject with specific rows and columns get from first line
		    		darkRoom = new char[numRows][numCols];
		    	} 
		    	// if only one character in some line, stop reading the following lines
		    	else if (line.length() == 1) 
		    		break;
		    	// after knowlng the rows and columns, read the content of darkRoom 
		    	else {
		    		// for each line read each element
		    		for (int c = 0; c < numCols; c++) {
		    			// add each element we get to darRoom
		    			darkRoom[currentRow][c] = line.charAt(c);
		    		}
		    		// update line to read the next line
		    		currentRow ++;
		    	}
		    }
		}
		// catch exceptions
		catch (IOException e) {
			// print out the exceptions
			System.out.println (e.toString());
			// print out the file we fail to read
	        System.out.println("Could not find file " + fname);
		}
	  
	}
	
	
	//Helper methods:
	
	// Method that returns the Location of "start"
	public Location findStart() 
	{
		Location start = null;
		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < numCols; j++)
			{
				if (darkRoom[i][j] == 'S')
					start = new Location(i,j);
			}
		}
		
		return start;
	} 
	
	//Method that checks if the goal was found
	public boolean isDoor (Location loc) 
	{
		int row = loc.getRow();
		int col = loc.getColumn();
		return darkRoom[row][col] == 'D'; //CHANGE
	}
	
	
	//Method that checks if you can move
	public boolean canMove(Location loc) 
	{
		if (darkRoom[loc.getRow()][loc.getColumn()] == ' ') {
			return true;
		}
		return false;
//		boolean move = true;
//		if (loc.getRow() < 0 || loc.getRow() >= numRows || loc.getColumn() < 0 || loc.getColumn() >= numCols)
//			move = false;
//		else
//			if ((darkRoom[loc.getRow()][loc.getColumn()] == '*') || (darkRoom[loc.getRow()][loc.getColumn()] == '@'))
//				move = false;
//		return move;
	}
	
	//Marks explored (visited) positions
	public void markVisited (Location loc) 
	{
		if (darkRoom[loc.getRow()][loc.getColumn()] == ' ')
		darkRoom[loc.getRow()][loc.getColumn()] = '.'; 
	}
	
	//counts the number of visited positions
	public int countVisited()
	{
		int count = 0;
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				if (darkRoom[i][j] == '.')
					count = count + 1;
			}
		}
		return count; //CHANGE
	}
	
	
	// removes marks from visiting (removes '.')
	public void clear() 
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				if (darkRoom[i][j] == '.')
					darkRoom[i][j] = ' ';
			}
		}
	}
	
    //prints your array that represents a room
	public void printRoom()
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				System.out.print(darkRoom[i][j]);
			}
			System.out.print('\n');
		}
	}
   
	// Search for ESCAPE!!! 
	public void escapeDarkRoom(String choice)
	{
		Location startPoint = findStart();

		Stack_QueueInterface <Location> storage;
		if ("Stack".equals(choice))
			storage = new MyStack<Location>();
		else
			storage = new MyQueue<Location>();

		storage.add(startPoint);

		loop:
		while (!storage.isEmpty())
		{
			Location currLoc = storage.get();
			markVisited(currLoc);
			
			for (Location l: new Location[]
					{currLoc.left(), currLoc.up(), currLoc.right(), currLoc.down()}) {
				if (darkRoom[l.getRow()][l.getColumn()] == 'D') {
					if (storage instanceof MyStack) {
					    System.out.print("Goal found (with stack): It took ");
					    System.out.println(countVisited()+" explored positions");
					    System.out.println("There is (are) " + storage.size() + " position(s) left to explore in stack");
					}
					if (storage instanceof MyQueue) {
					    System.out.print("Goal found (with queue): It took ");
					    System.out.println(countVisited()+" explored positions");
					    System.out.println("There is (are) " + storage.size() + " position(s) left to explore in queue");
					}
					break loop;
				}
			}
			
			for (Location l: new Location[]
					{currLoc.left(), currLoc.up(), currLoc.right(), currLoc.down()}) {
				if (canMove(l)) {
					storage.add(l);
				}
					}

			
//			if(canMove(currLoc.left()))
//			{
//				storage.add(currLoc.left());
//			}
//
//			if(canMove(currLoc.right()))
//			{
//				storage.add(currLoc.right());
//			}
//
//			if(canMove(currLoc.up()))
//			{
//				storage.add(currLoc.up());
//			}
//
//			if(canMove(currLoc.down()))
//			{
//				storage.add(currLoc.down());
//			}
		}
	}
		 
}
