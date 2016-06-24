/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman 
{
    public static void main(String[] args)
    {
        Boolean GameOver = false;
        Scanner scnr = new Scanner (System.in);
        char again = 'n';
        String guess;

        // play the game until player stop it
        while(GameOver == false)
        {
            int tries = 6; // maximum attemps to guess a word

            // choose the length of the word
            System.out.print("How long is the word? 3-6: ");
            int size = scnr.nextInt();
            Dictionary dictionary = new Dictionary(size);
            String word = dictionary.pickWord(size);
            dictionary.print(dictionary.phrase);
            // continue when there are still attemps
            while (tries > 0)
            {
                // print the List of missed letters
                System.out.print("Misses:" + "[");
                for(int i = 0; i < dictionary.Misses.size(); i++) 
                {
                    System.out.print(dictionary.Misses.get(i) + ",");
                }
                System.out.println("]");

                // print how many attemps left
                System.out.println("Attempts Left: " + tries);

                // read the user's next guess
                System.out.print("your guess? ");
                guess = scnr.next();

                // track the full guess history for the player
                int first_index = word.indexOf(guess);
                if (first_index != -1)
                {
                    dictionary.phrase[first_index] = guess;
                    int next_index = word.indexOf(guess, first_index + 1);
                    while(next_index != -1 && next_index > first_index)
                    {
                        dictionary.phrase[next_index] = guess;
                        first_index = next_index;
                        next_index = word.indexOf(guess, first_index);

                    }
                }
                else
                {
                    dictionary.addNewMiss(guess);
                }

                // print and update the phrase with dashes and correctly guessed letters
                dictionary.print(dictionary.phrase);
                tries = tries - 1;
                int index = -1;
                for (int i = 0; i < dictionary.phrase.length; i++)
                {
                    if (dictionary.phrase[i] == "-")
                    {
                        index = i;
                    }
                }

                // check if player win the game
                if (index == -1)
                {
                    System.out.println("You win!");
                    break;
                }
            }

            // the game should repeat until the user enters 'n'
            System.out.println(word);
            System.out.print("again? y/n ");
            again = scnr.next().charAt(0);
            if (again == 'n')
            {
                GameOver = true;
            }
        }

        System.out.println("Thanks for playing!");
    } 
}