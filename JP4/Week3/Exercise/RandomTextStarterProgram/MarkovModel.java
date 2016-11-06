
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int key_len;
    
    public MarkovModel(int n) {
        key_len = n;
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - key_len);
        String key = myText.substring(index, index + key_len);
        sb.append(key);
        for(int k = 0; k < numChars - key_len; ++k) {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0)
                break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            if(key_len > 1)
                key = key.substring(1) + next;
            else if(key_len == 1)
                key = next;
            else
                break;
        }
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
       ArrayList<String> follows = new ArrayList<String>();
       int len = key.length();
       int index = myText.indexOf(key);
       while(index + len < myText.length() && index != -1) {
           follows.add(myText.substring(index + len, index + len + 1));
           index = myText.indexOf(key, index + len);
       }
       return follows;
    }
}