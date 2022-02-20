// Sheng Yu
// Section: BO
// TA: Ning Hong
// 02/25/2018
// Assignment #7
// class QuestionNode is used to contrust a question node used in binary 
// search tree in the QuestionTree class

public class QuestionNode {
   public String data;
   public QuestionNode left;
   public QuestionNode right;
                
   // post: constructs a leaf node with given data
   public QuestionNode(String data) {
      this(data, null, null);
   }
                        
   // post: constructs a branch node with given data, 
   //       left subtree, right subtree
   public QuestionNode(String data, QuestionNode left, QuestionNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
   }
}