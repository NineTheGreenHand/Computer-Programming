// Sheng Yu
// Section: BO
// TA: Ning Hong
// 01/30/2018
// Assignment #4
// class HangmanManager is used to keep track of the state of the rigged
// version of the game called Hangman, instead of Hangman game, this
// class picks the words based on the user choices of letters during the
// game instead of picking the words in the beginning

import java.util.*;

public class HangmanManager {
   private Set<String> currentWords;            
   private SortedSet<Character> letterGuessed;  
   private int remainGuess;  
   private String currentPattern; 
   
   // pre : given length is not less than one 
   //       (throws an IllegalArgumentException otherwise) && 
   //       given max is not less than zero
   //       (throws an IllegalArgumentException otherwise)
   // post: constructs the remain guesses with the given max value, the initial
   //       pattern of words with dashes, a list to store letters guessed  
   //       through the game, and a list of words with all given length from  
   //       the given list dictionary
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      remainGuess = max;
      currentWords = new TreeSet<String>();
      letterGuessed = new TreeSet<Character>();
      currentPattern = "-";
      for (int i = 1; i < length; i++) {
         currentPattern += " -";
      }
      for (String words : dictionary) {
         if (words.length() == length) {
            currentWords.add(words);
         }
      }
   }
   
   // post: returns current set of words
   public Set<String> words() {
      return currentWords;
   }
   
   // post: returns the guesses left for the game 
   public int guessesLeft() {
      return remainGuess;
   }
   
   // post: returns the current set of letters passed
   public SortedSet<Character> guesses() {
      return letterGuessed;
   }
   
   // pre : current set of words is not empty 
   //       (throws an IllegalStateException otherwise)
   // post: returns the current patterns to be shown for
   //       the game with guesses have been made
   public String pattern() {
      if (currentWords.isEmpty()) {
         throw new IllegalStateException();
      }
      return currentPattern;
   }
   
   // pre : remain guesses is at least one 
   //       (throws an IllegalStatementException if not) &&
   //       the current set of words is not empty 
   //       (throws and IllegalStatementException otherwise) &&
   //       the current set of words is not empty and the given guess
   //       is not guessed before 
   //       (throws an IllegalArgumentException otherwise)
   // post: returns the number of occurences of the given guess letter in the 
   //       updated pattern, update the number of guess left, and decides the 
   //       set of words to use going forward
   public int record(char guess) {
      if (currentWords.isEmpty() || remainGuess < 1) {
         throw new IllegalStateException();
      }
      if (!currentWords.isEmpty() && letterGuessed.contains(guess)) {
         throw new IllegalArgumentException();
      }
      Map<String, Set<String>> wordsManager = new TreeMap<String, 
            Set<String>>();
      letterGuessed.add(guess);      
      for (String words : currentWords) {
         setPattern(words, guess);
         if (!wordsManager.containsKey(currentPattern)) {
            wordsManager.put(currentPattern, new TreeSet<String>());
         }
         wordsManager.get(currentPattern).add(words);
      } 
      updatePattern(wordsManager);
      currentWords = wordsManager.get(currentPattern);
      return occurence(guess);
   }
   
   // post: sets the current pattern based on the information from
   //       the given words and guess
   private void setPattern(String words, char guess) {
      currentPattern = "";
      for (int i = 0; i < words.length(); i++) {
         if (letterGuessed.contains(words.charAt(i))) {
            currentPattern += words.charAt(i) + " ";
         } else {
            currentPattern += "- ";
         }
      }
      currentPattern = currentPattern.substring(0, 
            currentPattern.length() - 1);
   }
   
   // post: update the current pattern to the one corresponds to the
   //       most words set in the given wordsManager
   private void updatePattern(Map<String, Set<String>> wordsManager) {
      currentPattern = "";
      int biggestSize = 0;
      for (String wordsPattern : wordsManager.keySet()) {
         if (wordsManager.get(wordsPattern).size() > biggestSize) {
            biggestSize = wordsManager.get(wordsPattern).size();
            currentPattern = wordsPattern;
         }
      }
   }
   
   // post: returns the number of occurences of the given guess in
   //       the current pattern   
   private int occurence(char guess) {
      int occurences = 0;
      for (int i = 0; i < currentPattern.length(); i++) {
         if (currentPattern.charAt(i) == guess) {
            occurences++;
         }
      }
      if (occurences == 0) {
         remainGuess--;
      }
      return occurences;
   }
}
      
      
            
                     
         
            
      
      
      
   
   
   
   
   
   
   

      