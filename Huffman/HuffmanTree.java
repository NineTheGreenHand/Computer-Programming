// Sheng Yu
// Section: BO
// TA: Ning Hong
// 03/05/2018
// Assignment #8
// class HuffmanTree is used to allow us encode the
// original file into a compressed version and decode
// the compressed file back to its original file

import java.util.*;
import java.io.*;

public class HuffmanTree {
   private HuffmanNode root;
   
   // post: constructs a Huffman tree with the information of values greater
   //       than zero in the given list count
   public HuffmanTree(int[] count) {
      Queue<HuffmanNode> huffmanTree = new PriorityQueue<HuffmanNode>();
      for (int i = 0; i < count.length; i++) {
         if (count[i] > 0) {
            huffmanTree.add(new HuffmanNode(i, count[i], null, null));
         }
      }
      huffmanTree.add(new HuffmanNode(count.length, 1, null, null));
      while (huffmanTree.size() > 1) {
         HuffmanNode leftNode = huffmanTree.remove();
         HuffmanNode rightNode = huffmanTree.remove();
         huffmanTree.add(new HuffmanNode(-1, leftNode.frequency + 
               rightNode.frequency, leftNode, rightNode));
      }
      root = huffmanTree.remove();     
   }
   
   // pre : given input file contains a tree stored in standard format of
   //       even lines
   // post: constructs a Huffman tree with the information from
   //       the given input file
   public HuffmanTree(Scanner input) {
      while (input.hasNextLine()) {
         int theLetter = Integer.parseInt(input.nextLine());
         String serialNumber = input.nextLine();
         root = constructorHelper(theLetter, serialNumber, root);
      }
   }
   
   // post: helps to construct the Huffman tree with the given information:
   //       theLetter to represent the number corresponding to that
   //       letter or character in Ascii; the string serialNumber to
   //       represent the right/left direction of the subtree; and the
   //       HuffmanNode tempRoot stands for a temporary subtree position,
   //       then returns the updated Huffman node
   private HuffmanNode constructorHelper(int theLetter, String serialNumber, 
         HuffmanNode tempRoot) {
      if (serialNumber.length() == 0) {
         tempRoot = new HuffmanNode(theLetter, -1, null, null);
      } else {
         if (tempRoot == null) {
            tempRoot = new HuffmanNode(-1, -1, null, null);
         }
         if (serialNumber.charAt(0) == '0') {
            tempRoot.left = constructorHelper(theLetter, 
                  serialNumber.substring(1), tempRoot.left);
         } else {
            tempRoot.right = constructorHelper(theLetter, 
                  serialNumber.substring(1), tempRoot.right);
         }
      }
      return tempRoot;
   }
   
   // post: writes the Huffman tree in the given output file in
   //       standard format and in-order
   public void write(PrintStream output) {
      writeAssistant("", output, root);
   }
   
   // post: helps the write method to write the Huffman tree with the
   //       given string serialNumber and the Huffman tree inside the 
   //       given output file
   private void writeAssistant(String serialNumber, PrintStream output, 
         HuffmanNode root) {
      if (root != null) {
         if (root.left == null && root.right == null) {
            output.println(root.letterNumber);
            output.println(serialNumber);
         } else {
            writeAssistant(serialNumber + 0, output, root.left);
            writeAssistant(serialNumber + 1, output, root.right);
         }
      }
   }
   
   // pre : the given input stream contains a legal encoding of
   //       characters for this Huffman tree code
   // post: decodes the given input stream back to its original version
   //       and prints out in the given output file, and this method should
   //       be stopped working when it encounters a character with value of
   //       the given eof parameter        
   public void decode(BitInputStream input, PrintStream output, int eof) {
      int letter = -1;
      while (letter != eof) {
         if (letter != -1) {
            output.write(letter);
         }
         letter = decodeHelper(input, root);
      }
   } 
   
   // pre : the given input stream contains a legal encoding of
   //       characters for this Huffman tree code
   // post: helps to find the leaf node in the Huffman tree with
   //       the information of the given input stream and huffman
   //       node tracker, then return the letter number corresponds
   //       to that leaf node
   private int decodeHelper(BitInputStream input, 
         HuffmanNode tracker) {
      if (tracker.left == null && tracker.right == null) {
         return tracker.letterNumber;
      } else {
         if (input.readBit() == 0) {
            return decodeHelper(input, tracker.left);
         } else {
            return decodeHelper(input, tracker.right);
         }
      }
   }        
}
  