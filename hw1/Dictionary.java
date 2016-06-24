/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;
/** 
 * A class that implements a "dictionary" and a few functions associated with it.
 * @author Kuangyi Yang
 * @version 1.0
 * @since 2015-09-24
 */


public class Dictionary{
  LinkedList<String> Misses = new LinkedList<String>();
  Random rand = new Random();  

  public String[] phrase;
  public String[] words={"cat", "dog", "rod", "bird", "look", "rate", "grade", "chair", "paper", "puzzle", "aboard", "avenue"};
  
  /** 
   * Initializes the phrase to guess with dashes 
   * @param size of the phrase to guess
   */
  public Dictionary(int size)
  {
    phrase = new String[size];
    for (int i = 0; i < size; i++)
    {
      phrase[i] = "-";
    }
  }
  
  /** Adds a string to the List of missed letters 
   * @param miss Input from the user, the letter does not belong to the phrase
   */
  public void addNewMiss(String miss)
  {
    Misses.addFirst(miss);
  }
  
  /** Prints an array
   * @param arr
   */
  public void print(String[] arr)
  {
    for(int i = 0; i < arr.length; i++)
    {
      System.out.print(arr[i] + " ");
    }  
    System.out.println("");
  }
  
  /** Picks a random word from the dictionary, given the length of the word 
   * @param size Input from the user, the word size.
   * @return String a random word from the dictionary
   */
  public String pickWord(int size)
  {
    ArrayList<Integer> index = new ArrayList<Integer> ();
    for (int i = 0; i < words.length; i++)
    {
      if (words[i].length() == size)
      {
        index.add(i);
      }
    }

    int temp_index = rand.nextInt(index.size());
    String pickedWord = words[index.get(temp_index)];

    return pickedWord; 
  }
  
}