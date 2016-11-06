
/**
 * This is assignment1 of Week2 JP3.
 * 
 * @liuyaqiu 
 * @2016-08-21
 */

import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource file = new FileResource();
        for(String word: file.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int i = 0; i < myFreqs.size(); i += 1) {
            if(myFreqs.get(i) > max) {
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for(int i = 0; i < myWords.size(); i += 1) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "
        + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
}
