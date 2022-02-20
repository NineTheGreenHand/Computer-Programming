// Sheng Yu
// Section: BO
// TA: Ning Hong
// 01/18/2018
// Assignment #2
// class GuitarString is used to model a vibrating guitar string of a given 
// frequency, and keep track of a ring buffer

import java.util.*;
 
public class GuitarString {
   public Queue<Double> ringBuffer;  
   public int ringBufferCapacity;
   
   public static final double ENERGY_DECAY = 0.996;    
   
   // pre : ringBufferCapacity >= 2 (throw IllegalArgumentException if not) &&
   //       frequency > 0 (throw IllegalArgumentException if not)
   // post: constructs a guitar string with the given frequency, and 
   //       initialize the ring buffer
   public GuitarString(double frequency) {
      ringBufferCapacity = (int)Math.round(StdAudio.SAMPLE_RATE / frequency);
      checkCapacity(ringBufferCapacity);
      if (frequency <= 0) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < ringBufferCapacity; i++) {
         ringBuffer.add(0.0);
      } 
   }
   
   // pre : init.length >= 2 (throw IllegalArgumentException if not)
   // post: constructs a guitar string and initialize the ring buffer with
   //       values in the given list 
   public GuitarString(double[] init) {
      checkCapacity(init.length);
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // post: modifies the ring buffer with random values
   public void pluck() {
      for (int i = 0; i < ringBufferCapacity; i++) {
         double randomValue = Math.random() - 0.5;
         ringBuffer.remove();
         ringBuffer.add(randomValue);
      } 
   }
   
   // post: perform one update of Karplus-Strong with ring buffer
   public void tic() {
      double pastPeek = ringBuffer.remove();
      double currentPeek = ringBuffer.peek();
      ringBuffer.add(0.5 * (pastPeek + currentPeek) * ENERGY_DECAY);
   }
   
   // post: returns the current sample of the ring buffer
   public double sample() {
      return this.ringBuffer.peek();
   }
   
   // post: throw an IllegalArgumentException if ringBufferCapacity 
   //       is less than two
   private void checkCapacity(int ringBufferCapacity) {
      if (ringBufferCapacity < 2) {
         throw new IllegalArgumentException();
      }
   }  
}
      
            