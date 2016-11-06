
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private int myOrder;
    private Random myRandom;
    private String[] myText;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public void buildMap() {
        for(int i = 0; i <= myText.length - myOrder; ++i) {
            WordGram cur = new WordGram(myText, i, myOrder);
            if(i == myText.length - myOrder) {
                if(map.get(cur) == null) {
                    ArrayList<String> tmp = new ArrayList<String>();
                    map.put(cur, tmp);
                }
            }
            else {
                if(map.get(cur) == null) {
                    ArrayList<String> tmp = getFollows(cur);
                    map.put(cur, tmp);
                }
            }
        }
    }
    
    public void printHashMapInfo() {
        int max_size = 0;
        int num = 0;
        ArrayList<WordGram> max_key = new ArrayList<WordGram>();
        for(WordGram key: map.keySet()) {
            num += 1;
            ArrayList<String> val = map.get(key);
            int tmp = val.size();
            if(max_size == tmp) {
                max_key.add(key);
            }
            else if(max_size < tmp) {
                max_size = tmp;
                max_key.clear();
                max_key.add(key);
            }
        }
        System.out.println("****** HashMap Info ******");
        System.out.println("There are " + num + " keys in the HashMap");
        System.out.println("The max size of ArrayList value is: " + max_size);
        System.out.println("The key corresponding to max size is: " + max_key);
        System.out.println("****** HashMap Info ******");
    }
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
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
            ArrayList<String> follows = map.get(kgram);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            res += next + " ";
            kgram = kgram.shiftAdd(next);
        }
        return res.trim();
    }
}
