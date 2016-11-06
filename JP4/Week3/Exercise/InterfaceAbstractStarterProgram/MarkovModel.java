
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int key_len;
    
    public MarkovModel(int n) {
        key_len = n;
        myRandom = new Random();
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
	public String toString() {
	    return "MarkovModel of order " + key_len + ".";
	}
}