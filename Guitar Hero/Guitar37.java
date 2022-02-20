// Sheng Yu
// Section: BO
// TA: Ning Hong
// 01/18/2018
// Assignment #2
// class Guitar37 track a musical instrument with multiple strings
public class Guitar37 implements Guitar {
   private GuitarString[] guitarString;   // list of GuitarString
   private int ticCount;                  // count of times tic is called
   
   public static final String KEYBOARD =
         "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   
   // post: constructs a list of GuitarString       
   public Guitar37() {
      guitarString = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         guitarString[i] = new GuitarString(440.0 * 
               Math.pow(2, (i - 24) / 12.0));
      } 
   }
   
   // pre : pitch >= -24 && pitch <= 12
   // post: plays the corresponding note with the given pitch
   public void playNote(int pitch) {
      if (pitch >= -24 && pitch <= 12) {
         guitarString[pitch + 24].pluck();
      }
   }
   
   // post: returns true if the given string is in the KEYBOARD; 
   //       false otherwise
   public boolean hasString(char string) {
      return KEYBOARD.indexOf(string) != -1; 
   }
   
   // pre : given string is in the KEYBOARD 
   //       (throw an IllegalArgumentException if not)
   // post: plays the note of the pluck with the given string
   public void pluck(char string) {
      if (KEYBOARD.indexOf(string) == -1) {
         throw new IllegalArgumentException();
      }
      guitarString[KEYBOARD.indexOf(string)].pluck();
   }
   
   // post: counts the total sample sum of all the notes 
   public double sample() {
      double sampleSum = 0.0;
      for (int i = 0; i < KEYBOARD.length(); i++) {
         sampleSum += guitarString[i].sample();
      }
      return sampleSum;
   }
   
   // post: applies the Karplus-Strong update once for all the notes
   public void tic() {
      for (int i = 0; i < KEYBOARD.length(); i++) {
         guitarString[i].tic();
      }
      ticCount++;
   }
   
   // post: returns the total count of times tic is called
   public int time() {
      return ticCount;
   }
}

