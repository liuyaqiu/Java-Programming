
/**
 * This is the assignment3 of Week1.
 * 
 * @liuyaqiu
 * @2016-08-09
 */

import edu.duke.*;
import java.io.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for(String word: resource.words()) {
            int len = word.length();
            int cnt = len;
            if(!Character.isLetter(word.charAt(0)))
                cnt -= 1;
            if(!Character.isLetter(word.charAt(len - 1)))
                cnt -= 1;
            if(cnt < counts.length)
                counts[cnt] += 1;
            else
                counts[counts.length - 1] += 1;
        }
    }
    
    public int indexOfMax(int[] values) {
        int max = values[0];
        int index = 0;
        for(int i = 0; i < values.length; i+= 1) {
            if(max < values[i]) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public void testCountWordLengths() {
        FileResource file = new FileResource();
        int[] counts = new int[31];
        countWordLengths(file, counts);
        for(int i = 0; i < counts.length; i+=1) {
            System.out.println("Number of length of " + i + ": " + counts[i]);
        }
        System.out.println("index of max is " + indexOfMax(counts));
    }
}
