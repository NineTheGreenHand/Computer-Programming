This project is about a program that explores how text files can be compressed by using a coding scheme based on the frequency of characters.  
We will use a coding scheme called Huffman coding. The basic idea is to abandon the way that text files are usually stored. Instead of using the 
usual seven or eight bits per character, Huffman's method uses only a few bits for characters that are used often, more bits for those that are 
rarely used. We solve this problem using a structure known as a priority queue. In a priority queue each value inserted into the queue has a priority 
that determines when it will be removed.  There are many ways to specify the priorities. For this program you will construct objects that implement the 
Comparable interface, with objects that are “less” being given a higher priority (to be removed first). The first step is to compute the frequency of 
each character in the file you wish to encode.  This allows you to determine which characters should have the fewest bits, etc. The next step is to build 
a “coding tree” from the bottom up according to the frequencies.  An example will help make this clear.  To make the example easier, suppose we only want 
to encode the five letters (a, b, c, x, y) and they have frequencies 3, 3, 1, 1, and 2, respectively.
