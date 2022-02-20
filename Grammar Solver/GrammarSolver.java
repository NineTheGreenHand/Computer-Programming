// Sheng Yu
// Section: BO
// TA: Ning Hong
// 02/08/2018
// Assignment #5
// class GrammarSolver is used to manipulate the grammer by generates the
// given file of strings, and to arrange the strings in certain ways

import java.util.*;

public class GrammarSolver {
   private SortedMap<String, List<String>> terminalMap;  
   
   // pre : given grammar is not empty 
   //       (throws an IllegalArgumentException otherwise) &&
   //       only one entries in the grammar for the same nonterminal
   //       (throws an IllegalArgumentException otherwise)
   // post: constructs a grammar solver to manipulate the given grammar 
   public GrammarSolver(List<String> grammar) {
      if (grammar.isEmpty()) {
         throw new IllegalArgumentException();
      }
      terminalMap = new TreeMap<String, List<String>>();
      for (String words : grammar) {
         takeOutRules(words);
      }
   }
   
   // pre : only one entries in the grammar for the same nonterminal
   //       (throws an IllegalArgumentException otherwise)
   // post: seperates the given words, and put them into forms of key strings 
   //       correspond to value strings
   private void takeOutRules(String words) {
      String[] grammarExpression = words.split("::=");
      if (terminalMap.containsKey(grammarExpression[0])) {
         throw new IllegalArgumentException();
      }
      List<String> wordList = new ArrayList<String>();
      String[] rules = grammarExpression[1].split("[|]");
      for (String token : rules) {
         wordList.add(token.trim());
      }
      terminalMap.put(grammarExpression[0], wordList);
   }
   
   // post: returns true if the given symbol is found in the terminalMap;
   //       false otherwise
   public boolean grammarContains(String symbol) {
      return terminalMap.containsKey(symbol);
   }
   
   // post: returns a string representation of all the nonterminal symbols
   //       as a sorted, comma-separated list enclosed in square brackets
   public String getSymbols() {
      return terminalMap.keySet().toString(); 
   }
   
   // pre : given nonterminal symbol is found in terminalMap or given times is 
   //       not less than zero (throws an IllegalArgumentException otherwise)
   // post: generates a list of strings by using the grammar with the given
   //       nonterminal symbol appears given times, and returns this list
   public String[] generate(String symbol, int times) {
      if (!terminalMap.containsKey(symbol) || times < 0) {
         throw new IllegalArgumentException();
      }
      String[] outcome = new String[times];
      for (int i = 0; i < times; i++) {
         outcome[i] = generateSymbols(symbol);
      }
      return outcome;
   }
   
   // post: generates the given symbol by using the grammar to
   //       make a string, and returns this string        
   private String generateSymbols(String symbol) {
      if (terminalMap.containsKey(symbol)) {
         Random random = new Random();
         int randomNumber = random.nextInt(terminalMap.get(symbol).size());
         String[] newTerminals = terminalMap.get(symbol).get(randomNumber)
               .trim().split("[ \t]+");
         String sentence = "";
         for (int i = 0; i < newTerminals.length; i++) {
            sentence += generateSymbols(newTerminals[i]) + " ";
         }
         return sentence.trim();
      } else {
         return symbol;
      }
   }  
}

   
   