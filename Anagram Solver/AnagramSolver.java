// Sheng Yu
// Section: BO
// TA: Ning Hong
// 02/20/2018
// Assignment #6
// class AnagramSolver is used to find all combinations of words that
// have same letters with a given phrase from a given dictionary

import java.util.*;

public class AnagramSolver {
   private Map<String, LetterInventory> anagramSolver;
   private List<String> dictionary;
   
   // pre : the given list of words is nonempty and with no duplicates
   // post: constructs a anagram solver which stores words from the 
   //       the given list corresponds to its letter inventory 
   public AnagramSolver(List<String> list) {
      anagramSolver = new HashMap<String, LetterInventory>();
      dictionary = list;
      for (String word : list) {
         anagramSolver.put(word, new LetterInventory(word));
      }
   }
   
   // pre : given max (the number of words need to be printed) is at least zero 
   //       (throws an IllegalArgumentException otherwise)
   // post: prints out a list of words with given length max, and
   //       with the same combination of letters with the given phrase
   //       s, when max is equal to zero, the list kept adding words until
   //       there is nothing left in the phrase s
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory convertWord = new LetterInventory(s);
      List<String> newDictionary = new ArrayList<String>();
      for (String key : dictionary) {
         if (convertWord.subtract(anagramSolver.get(key)) != null) {
            newDictionary.add(key);  
         }
      }
      Stack<String> result = new Stack<String>();
      printExplore(max, newDictionary, convertWord, result);
   }
   
   // post: explores the prints method using resursive backtracking, 
   //       it choose words from the given list newDictionary, and 
   //       add words to the result, then prints out the list of words 
   //       with the given number of max, it updates the convertWord 
   //       everytime when it backtracking
   private void printExplore(int max, List<String> newDictionary, 
         LetterInventory convertWord, Stack<String> result) {
      if ((result.size() <= max || max == 0) && convertWord.isEmpty()) {
         System.out.println(result);
      } else {
         for (String word : newDictionary) {
            LetterInventory leftWord = convertWord.
                  subtract(anagramSolver.get(word));
            if (leftWord != null) {
               result.push(word);
               printExplore(max, newDictionary, leftWord, result);
               result.pop();
            }
         }
      }
   }   
}
   
   
   