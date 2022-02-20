// Sheng Yu
// Section: BO
// TA: Ning Hong
// 03/02/2018
// Assignment #8
// class HuffmanNode is used to construct a huffman node for
// huffman tree

public class HuffmanNode implements Comparable<HuffmanNode> {
   public HuffmanNode left;
   public HuffmanNode right;
   public int frequency;
   public int letterNumber; // the ascii number represents the letter 
   
   // post: constructs a branch node with the given information: 
   //       the number that represents the given letter; number of  
   //       of the appearance of that letter; left subtree and
   //       right subtree
   public HuffmanNode(int letterNumber, int frequency, HuffmanNode left, 
         HuffmanNode right) {
      this.letterNumber = letterNumber;
      this.frequency = frequency;
      this.left = left;
      this.right = right;
   }
      
   // post: compares with the occurrence of a letter with the given other
   //       node, and put the node in an increasing order, returns negative 
   //       value if the given other node has more occurrences of the letter; 
   //       returns zero if two occurrences equal to each other; and returns  
   //       positive if give other node has less occurrences of the letter
   public int compareTo(HuffmanNode other) {
      return this.frequency - other.frequency;
   }
}