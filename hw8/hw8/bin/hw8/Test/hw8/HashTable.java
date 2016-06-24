package hw8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

public class HashTable implements IHashTable {
	private int arraySize = 15;
	@SuppressWarnings("rawtypes")
	private LinkedList [] myTable;
	private int countSize = 0;
	private int countResize = 0;
	
	 
	 @SuppressWarnings("rawtypes")
	public HashTable ()
	 {
		myTable = new LinkedList [arraySize];
		for (int i = 0; i < arraySize; i++)
		{
			myTable[i] = new LinkedList();
		}
	 }

	@SuppressWarnings("unchecked")
	@Override
	public boolean Insert(String value) {
		int hashVal = hashFunction(value);
		if (myTable[hashVal].indexOf(value) != -1)
		{	System.out.println("item " + value + " already present");
			return false;
		}
		
		else
		{
			countSize = countSize + 1;
			//int hashVal = hashFunction(value);
			myTable[hashVal].add(value);
			System.out.println("item " + value + " successfully inserted");
			if(countSize > arraySize)
			{
				rehash();
			}
			return true;
		}
	}

	@Override
	public boolean Delete(String value) {
		int hashVal = hashFunction(value);
		if (myTable[hashVal].indexOf(value) == -1)
		{
			System.out.println("item " + value +" not found");
			return false;
		}
		else
		{
			myTable[hashVal].remove(value);
			//if(myTable[hashVal].size() == 0)
			//{
			//	myTable[hashVal].add("(bridge)");
			//}
			System.out.println("item " + value + " successfully deleted");
			return true;
		}
	}

	@Override
	public boolean Lookup(String value) {
		int hashVal = hashFunction(value);
		if (myTable[hashVal].indexOf(value) == -1)
		{
			System.out.println("item " + value + " not found");
			return false;
		}
		else
		{
			System.out.println("item " + value + " found");
			return true;
		}		
	}

	@Override
	public void Print(PrintStream out) {
		for (int i = 0; i < arraySize; i++)
		{
			System.out.print(i+":");
			if (myTable[i].size() > 0)
			{
				for (int j = 0; j < myTable[i].size() - 1; j++)
				{
					System.out.print((String) myTable[i].get(j) + ",");
				}
				System.out.println(myTable[i].get(myTable[i].size() - 1));
			}
			else
				System.out.print("\n");
		}	
	}

	public static void main(String[] args) // This is used only for Part 1
	{
		File file = new File("HW8.txt");
		PrintStream ps;
		try {
			ps = new PrintStream(file);
			System.setOut(ps);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		IHashTable hash = new HashTable();
		String line;
		BufferedReader inputStream;

		try 
		{
			inputStream = new BufferedReader(new FileReader(args[0]));
			while ((line = inputStream.readLine()) != null) 
			{
				String[] result = line.split("\"");
		    	String operation = result[0];
		    	String word = result[1];
		    	if(operation.equals("insert"))
		    	{
		    		hash.Insert(word);
		    	}
		    	else if(operation.equals("lookup"))
		    	{
		    		hash.Lookup(word);
		    	}
		    	else if(operation.equals("delete"))
		    	{
		    		hash.Delete(word);
		    	}
		    	else
		    	{
		    		System.out.println("Invalid operation:" + operation + ".");
		    	}
			}
		}
		catch (IOException e)
		{
			System.out.println (e.toString());
	        System.out.println("Could not find file " + args[0]);
	    }
		
		hash.Print(System.out);
	}
	
	public int hashFunction(String value)
	{
		int hashVal = 0;
		for (int j = 0; j < value.length(); j++)
		{
			int letter = value.charAt(j);
			hashVal = (hashVal * 32 + letter) % arraySize;
		}
		return hashVal;
	}

	@SuppressWarnings("rawtypes")
	private void rehash()
	{
		LinkedList[] oldTable = myTable;
		myTable = new LinkedList[2*arraySize];
		arraySize = 2*arraySize;
		countSize = 0;
		for (int i = 0; i < myTable.length; i++)
		{
			myTable[i] = new LinkedList();
		}
		for (int j = 0; j < oldTable.length; j++)
		{
			if(oldTable[j].size() > 0)
			{
				for (int k = 0; k < oldTable[j].size(); k++)
				{
					Insert((String) oldTable[j].get(k));
				}
			}	
		}
		countResize = countResize + 1;	
	}
	
	public int countCollision()
	{
		int collision = 0;
		for (int i = 0; i < arraySize; i++)
		{
			if(myTable[i].size() > 1)
			{
				collision = collision + myTable[i].size();
			}
		}
		return collision;
	}
	
	public int countResize()
	{
		return countResize;
	}
	
	public float loadFactor()
	{
		return (float) ((countSize*1.0)/(arraySize*1.0));
	}
	
	public int longestChain()
	{
		int longest = 0;
		for (int i = 0; i < arraySize; i++)
		{
			if(myTable[i].size() > longest)
			{
				longest = myTable[i].size();
			}
		}
		return longest;
	}
	
	public int size()
	{
		return myTable.length;
	}
	
	@SuppressWarnings("rawtypes")
	public LinkedList getNode(int i)
	{
		return myTable[i];
	}
}
