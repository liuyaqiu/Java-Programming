
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel{
    private int myOrder;
    private Random myRandom;
    private String[] myText;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        //System.out.println(myText.length);
    }
    
    public int indexOf(String[] words, WordGram target, int start) {
        int len = target.length();
        for(int i = start; i < words.length - len; ++i) {
            WordGram tmp = new WordGram(words, i, len);
            if(tmp.equals(target))
                return i;
        }
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
        int len = kGram.length();
        ArrayList<String> follows = new ArrayList<String>();
        int index = indexOf(myText, kGram, 0);
        while(index + len < myText.length && index != -1) {
            follows.add(myText[index + len]);
            index = indexOf(myText, kGram, index + 1);
        }
        return follows;
    }
    
    public String getRandomText(int numWords) {
        if(myText == null || myText.length == 0)
            return "";
        String res = "";
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram kgram = new WordGram(myText, index, myOrder);
        res += kgram.toString() + " ";
        for(int i = 0; i < numWords - myOrder; ++i) {
            ArrayList<String> follows = getFollows(kgram);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            res += next + " ";
            kgram = kgram.shiftAdd(next);
        }
        return res.trim();
    }
}
