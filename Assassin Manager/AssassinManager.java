// Sheng Yu
// Section: BO
// TA: Ning Hong
// 01/24/2018
// Assignment #3
// class AssassinManager is used to manage the game of assassins 
// and records the game process

import java.util.*;

public class AssassinManager {
   private AssassinNode frontKiller;   // list of names in the killing ring
   private AssassinNode frontGrave;    // list of names in the grave yard
   
   // pre : the name list is not empty 
   //       (throw  an IllegalArgumentException if not)
   // post: constructs an assassin manager list with names in the given list
   public AssassinManager(List<String> names) {
      if (names.size() == 0) {
         throw new IllegalArgumentException();
      }
      frontKiller = null;
      frontGrave = null;
      for (int i = names.size() - 1; i >= 0; i--) {
         frontKiller = new AssassinNode(names.get(i), frontKiller);
      }
   }
   
   // post: prints out the names of people follows the order in kill ring 
   public void printKillRing() {
      AssassinNode currentFront = frontKiller;
      while (currentFront != null) {
         System.out.print("    " + currentFront.name + " is stalking ");
         if (currentFront.next == null) {
            System.out.println(frontKiller.name);
         } else {
            System.out.println(currentFront.next.name);
         }
         currentFront = currentFront.next;
      }
   }
   
   // post: prints out the names of people follows the order in grave yard
   public void printGraveyard() {
      AssassinNode currentFront = frontGrave;
      while (currentFront != null) {
         System.out.println("    " + currentFront.name + " was killed by " 
               + currentFront.killer);
         currentFront = currentFront.next;
      }
   }
   
   // post: returns true if given name is inside of the killing
   //       ring list; false otherwise
   public boolean killRingContains(String name) {
      return testContains(frontKiller, name);
   }
   
   // post: returns true if given name is inside of the grave 
   //       yard list; false otherwise
   public boolean graveyardContains(String name) {
      return testContains(frontGrave, name);
   }
   
   // post: returns true if game is over; false otherwise
   public boolean gameOver() {
      return frontKiller.next == null;
   }
   
   // post: returns the name of the winner of the game if game over; 
   //       returns null otherwise
   public String winner() {
      if (gameOver()) {
         return frontKiller.name;
      }
      return null;
   }
   
   // pre : game is not over (throw an IllegalStateException if not) &&
   //       given name is inside of the killing ring 
   //       (throw an IllegalArgumentException if not)
   // post: records the killing of the person with the given name in the 
   //       killing ring, and transfers the person to the grave yard from
   //       the killing ring
   public void kill(String name) {
      if (gameOver()) {
         throw new IllegalStateException();
      }
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      }
      AssassinNode prevGrave = frontGrave;      // previous front of grave
      AssassinNode prevTarget = frontKiller;    // person kills the target
      if (frontKiller.name.equalsIgnoreCase(name)) {
         frontGrave = frontKiller;
         frontKiller = frontKiller.next;
         while (prevTarget.next != null) {
            prevTarget = prevTarget.next;
         }
      } else {
         while (prevTarget.next != null && 
               !prevTarget.next.name.equalsIgnoreCase(name)) {
            prevTarget = prevTarget.next;
         }
         frontGrave = prevTarget.next;
         prevTarget.next = prevTarget.next.next;
      }
      frontGrave.next = prevGrave;
      frontGrave.killer = prevTarget.name;
   }
   
   // post: returns true if the give name is inside of the given 
   //       testNode list; false otherwises
   private boolean testContains(AssassinNode testNode, String name) {
      AssassinNode currentName = testNode;
      while (currentName != null) {
         if (currentName.name.equalsIgnoreCase(name)) {
            return true;
         } 
         currentName = currentName.next;
      }
      return false;
   }
}

      