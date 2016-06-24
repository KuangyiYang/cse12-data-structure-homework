/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
package hw6;
import java.io.*;
//import java.util.NoSuchElementException;
import java.util.Scanner;

public class EDF {

	public static void main(String[] args) {
		if(args.length != 1)
		{
			 System.err.println("Incorrect number of arguments: "+args.length);
			 System.err.println("java hw6.EDF <input-file>");
			 System.exit(1); 
		}
		
		File file = new File(args[0]);
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(20);
		long current_time = 0;
		long run_time = 0;
		
		try{
			Scanner scnr = new Scanner(file);
			MyPriorityQueue<Record> tempQueue = new MyPriorityQueue<Record>(20);
			
			while (scnr.hasNext())
			{
				String preset = scnr.next();
				
				if (preset.equals("run"))
				{
					run_time = scnr.nextLong();
					while (tempQueue.size() > 0)
					{
						queue.add(tempQueue.poll());
					}
					
					while (queue.size() > 0)
					{
						System.out.println(current_time + ": " + "busy with " + queue.get(0).toString());
						current_time = current_time + queue.get(0).GetDuration();
						if (current_time > run_time)
						{
							Record item = new Record(queue.poll(), current_time - run_time);
							tempQueue.add(item);
							current_time = run_time;
							System.out.println(current_time + ": adding " + item.toString());
							break;
						}
						else
						{
							System.out.println(current_time + ": done with " + queue.poll().toString(current_time));
						}
					}
				}
				
				else if (preset.equals("schedule"))
				{
					Record record = new Record(scnr.next(),scnr.nextLong(),scnr.nextLong());
					tempQueue.add(record);
					System.out.println(current_time + ": adding " + record.toString());
				}
			}
			scnr.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		System.exit(0);
		
	}

}

