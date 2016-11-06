
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
       ArrayList<String> follows = new ArrayList<String>();
       int len = key.length();
       int index = myText.indexOf(key);
       while(index + len < myText.length() && index != -1) {
           follows.add(myText.substring(index + len, index + len + 1));
           index = myText.indexOf(key, index + 1);
       }
       return follows;
    }
    
}
