package hw8;

import java.io.*;

public class SpellChecker implements ISpellChecker {
	private HashTable words = new HashTable();
	private static int r = 0;
	private static int c = 0;
	private static int n = 0;
	private static float alpha = 0;

	@Override
	public void ReadDictionary(Reader reader) {
		String word;
		BufferedReader read = new BufferedReader(reader);
		try {
			while((word = read.readLine()) != null)
			{
				words.Insert(word);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		r = words.countResize();
		c = words.countCollision();
		n = words.longestChain();
		alpha = words.loadFactor();
		
	}

	@Override
	public String[] CheckWord(String word) {
		// TODO Auto-generated method stub
		word = word.toLowerCase();
		if (words.Lookup(word) == true)
			return null;
		else
		{
			String [] variants = new String[words.size()];
			int count = 0;
			for (int i = 0; i < words.size(); i++)
			{
				if (words.getNode(i).size() > 0)
				{
					for(int j = 0; j < words.getNode(i).size(); j++)
					{
						String check = (String) words.getNode(i).get(j);
						if (check.length() == word.length())
						{
							int diff = 0;
							for(int k = 0; k < word.length(); k++)
							{
								if (check.charAt(k) != word.charAt(k))
								{
									diff = diff + 1;
								}
							}
							
							if (diff == 1)
							{
								variants[count] = check;
								count = count + 1;
							}
						}
					}
				}
			}
			
			String[] results = new String[count];
			for(int i = 0; i < count; i++)
			{
				results[i] = variants[i];
			}
			return results;
		}
	}
	
	public static void main(String[] args)  // This is used for Part 2
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
		
		ISpellChecker spellChecker = new SpellChecker();
		try {
			spellChecker.ReadDictionary(new FileReader(args[0]));
			System.out.println("r = " + r);
			System.out.println("c = " + c);
			System.out.println("n = " + n);
			System.out.println("alpha = " + alpha);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader inputStream;
		String word;
		
		try
		{
			inputStream = new BufferedReader(new FileReader(args[1]));
			while((word = inputStream.readLine()) != null)
			{
				String[] variants = spellChecker.CheckWord(word);
				if (variants != null)
				{
					System.out.print(word + ":");
					if (variants.length > 0)
					{
						for(int i = 0; i < variants.length - 1; i++)
						System.out.print(variants[i]+ " ");
						System.out.println(variants[variants.length-1]);
					}
				}
			}
		}
		catch (IOException e)
		{
			System.out.println (e.toString());
	        System.out.println("Could not find file " + args[1]);
	    }
	}
}