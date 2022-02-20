// Sheng Yu
// Section: BO
// TA: Ning Hong
// 01/09/2018
// Assignment #1
// Class LetterInventory is used to keep track of an inventory of alphabetic letters; it ignores 
// the case of the letters and anything that is not an alphabetic character. 
public class LetterInventory {
   private int[] elementData;     // list of integers  
   private int letterTotalCounts;   
   
   public static final int DEFAULT_CAPACITY = 26;
   
   // post: constructs an inventory of counts of the alphabetic letters with
   //       the given data
   public LetterInventory(String data) {
      elementData = new int[DEFAULT_CAPACITY];
      data = data.toLowerCase();
      for (int i = 0; i < data.length(); i++) {
         if (Character.isLetter(data.charAt(i))) {
            elementData[data.charAt(i) - 'a']++;
            letterTotalCounts++;
         } 
      }
   }
   
   // post: returns the sum of all of the counts of alphabetic letters in this inventory
   public int size() {
      return letterTotalCounts;
   } 
   
   // post: returns true if size is zero means inventory is empty; false otherwise
   public boolean isEmpty() {
      return letterTotalCounts == 0;
   }
   
   // pre : given letter is alphabetic letter (throws IllegalArgumentException if not)
   // post: returns a count of the given alphabetic letter in inventory
   public int get(char letter) {
      checkLetter(letter);
      return elementData[Character.toLowerCase(letter) - 'a'];
   }
   
   // post: returns a String representation of the inventory in lowercase,  
   //       sorted order, and surrounded by square brackets
   public String toString() {
      String representation = "";
      for (int i = 0; i < DEFAULT_CAPACITY; i++) {
         for (int j = 0; j < elementData[i]; j++) {
            representation += (char) ('a' + i);
         }
      }
      return "[" + representation + "]";
   }
   
   // pre : given letter is alphabetic letter (throws IllegalArgumentException if not) &&  
   //       value >= 0 (throws IllegalArgumentException if not)
   // post: sets the letter count for the given letter to the given value.
   public void set(char letter, int value) {
      checkLetter(letter);
      if (value < 0) {
         throw new IllegalArgumentException();
      } 
      letterTotalCounts = letterTotalCounts - 
            elementData[Character.toLowerCase(letter) - 'a'] + value;
      elementData[Character.toLowerCase(letter) - 'a'] = value;
   } 
   
   // post: constructs and returns a new LetterInventory object represents the adding of  
   //       given other LetterInventory and this LetterInventory
   public LetterInventory add(LetterInventory other) {
      LetterInventory addInventory = new LetterInventory("");
      for (int i = 0; i < DEFAULT_CAPACITY; i++) {
         addInventory.elementData[i] = this.elementData[i] + other.elementData[i];
      }
      addInventory.letterTotalCounts = this.letterTotalCounts + other.letterTotalCounts;
      return addInventory;
   }
   
   // post: constructs and returns a new LetterInventory object that represents the 
   //       subtracted inventory of the given other inventory from this inventory. 
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory subtractInventory = new LetterInventory("");
      for (int i = 0; i < DEFAULT_CAPACITY; i++) {
         subtractInventory.elementData[i] = this.elementData[i] - other.elementData[i];
         if (subtractInventory.elementData[i] < 0) {
            return null;
         }
      }
      subtractInventory.letterTotalCounts = this.letterTotalCounts - other.letterTotalCounts;
      return subtractInventory; 
   }
   
   // post: throws an IllegalArgumentException if the given letter is not an alphabetic letter
   private void checkLetter (char letter) {
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException();
      }
   }         
}  



