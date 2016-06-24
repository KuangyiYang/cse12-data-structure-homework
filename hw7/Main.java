/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
package hw7;
import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		long t1_start = 0;
		long t2_start = 0;
		long t1_end = 0;
		long t2_end = 0;
	
		BST<Integer> tree_1 = new BST<Integer>();
		TBST<Integer> tree_2 = new TBST<Integer>();
		if(args.length != 1)
		{
			System.err.println("Expected only 1 argument");
			return;
		}
		try{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new File(args[0]));
			while(scanner.hasNextInt())
			{
			     int val = scanner.nextInt();
			     tree_1.insert(new Integer(val));
			     tree_2.insert(new Integer(val));
			     //BSTS tree will be here.
			     
			}
		}catch(FileNotFoundException ex)
		{
			System.err.println("File not found: "+args[0]);
			return;
		}
		
		long time_1 = 0;
		long time_2 = 0;
		for (int i = 0; i < 20; i++)
		{
			t1_start = System.currentTimeMillis();
			tree_1.printInOrder(); 
			t1_end = System.currentTimeMillis();
			time_1 = time_1 + t1_end - t1_start;
			System.gc();
		}
		time_1 = time_1/20;
		
		System.out.print("\n");
		for (int i = 0; i < 20; i++)
		{
			t2_start = System.currentTimeMillis();
			tree_2.printInOrder(); 
			t2_end = System.currentTimeMillis();
			time_2 = time_2 + t2_end - t2_start;
			System.gc();
		}
		time_2 = time_2/20;
		System.out.println("\n");
		System.out.println(time_1);
		System.out.println(time_2);

	}
	

}

