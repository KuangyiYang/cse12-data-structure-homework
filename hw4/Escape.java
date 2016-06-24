package hw4;
/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */

public class Escape
{
	// main mehod to call the game
	public static void main(String[] args)
	{
		// calls darkRoom method to read the file
		DarkRoom room = new DarkRoom();
		room.readFromFile(args[0]);
		
		room.escapeDarkRoom("Stack");
		room.printRoom();
		room.clear();

		room.escapeDarkRoom("Queue");
		room.printRoom();
		room.clear();

	}
}