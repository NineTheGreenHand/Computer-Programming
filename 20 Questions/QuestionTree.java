// Sheng Yu
// Section: BO
// TA: Ning Hong
// 02/28/2018
// Assignment #7
// class QuestionTree is used to constructs a binary tree from a
// text file, and with the user answers for the question based on the
// file, it becomes smarter by add new question or answers in a text file,
// and use this file for the next round of game

import java.io.*;
import java.util.*;

public class QuestionTree {
   private QuestionNode root;
   private Scanner console;
   
   // post: constructs a new question tree, initial 
   //       the tree with one leaf node representing
   //       "computer"
   public QuestionTree() {
      root = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   // pre : given file input is legal and in standard format
   // post: reads the given input file and replace the current binary
   //       tree to a new tree with the information in this text file
   public void read(Scanner input) {
      root = readAssistant(input);
   }
   
   // pre : given file input is legal and in standard format
   // post: uses recursive approach to help replace the current tree into
   //       a new tree with the information from the given input file; and 
   //       returns this new question node
   private QuestionNode readAssistant(Scanner input) {
      String type = input.nextLine();
      String text = input.nextLine();
      if (type.equals("Q:")) {
         return new QuestionNode(text, readAssistant(input), 
               readAssistant(input));
      } else {
         return new QuestionNode(text);
      }
   }
   
   // post: prints out the binary tree with a preorder into
   //       the given output file
   public void write(PrintStream output) {
      writeAssistant(output, root);
   }
   
   // post: helps to print out the binary tree in a preorder 
   //       into the given output file with the information 
   //       from the given question node root      
   private void writeAssistant(PrintStream output, QuestionNode root) {
      if (root.left == null && root.right == null) {
         output.println("A:");
         output.println(root.data);
      } else {
         output.println("Q:");
         output.println(root.data);
         writeAssistant(output, root.left);
         writeAssistant(output, root.right);
      }  
   }
   
   // post: asks the user with questions needs an answer of yes or no, 
   //       until the computer gets the correct answer or the user wants
   //       to end the game, the tree expands its branches during this 
   //       question and answer process
   public void askQuestions() {
      root = askQuestionsAssistant(root);
   }
   
   // post: helps the method askQuestions with the given root question node,
   //       returns the updated question node everytime it recurs
   private QuestionNode askQuestionsAssistant(QuestionNode root) {
      if (root.left == null && root.right == null) {
         if (yesTo("Would your object happen to be " + root.data + "?")) {
            System.out.println("Great, I got it right!");
         } else {
            return wrongAnswerSolver(root);
         }
      } else {
         if (yesTo(root.data)) {
            root.left = askQuestionsAssistant(root.left);
         } else {
            root.right = askQuestionsAssistant(root.right);
         }
      }
      return root;
   }
   
   // post: takes the QuestionNode root as paramater, deals when
   //       the computer fails to guess the right answer by expanding
   //       the question tree with the user interaction, updates 
   //       and returns the updated question node
   private QuestionNode wrongAnswerSolver(QuestionNode root) {
      System.out.print("What is the name of your object? ");
      String name = console.nextLine();
      QuestionNode answerNode = new QuestionNode(name);
      System.out.println("Please give me a yes/no question that");
      System.out.println("distinguishes between your object");
      System.out.print("and mine--> ");
      String question = console.nextLine();
      if (yesTo("And what is the answer for your object?")) {
         return new QuestionNode(question, answerNode, root);
      } else {
         return new QuestionNode(question, root, answerNode);
      }
   }

   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }   
}