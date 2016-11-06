
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int key_len;
    private HashMap<String, ArrayList<String>> follows;
    public void buildMap() {
        for(int i = 0; i <= myText.length() - key_len; ++i) {
            String cur = myText.substring(i, i + key_len);
            if(i == myText.length() - key_len) {
                if(follows.get(cur) == null) {
                    ArrayList<String> tmp = new ArrayList<String>();
                    follows.put(cur, tmp);
                }
            }
            else {
                if(follows.get(cur) == null) {
                    ArrayList<String> tmp = getFollows(cur);
                    follows.put(cur, tmp);
                }
            }
        }
    }
    
    public void printHashMapInfo() {
        int max_size = 0;
        int num = 0;
        ArrayList<String> max_key = new ArrayList<String>();
        for(String key: follows.keySet()) {
            num += 1;
            ArrayList<String> val = follows.get(key);
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
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public EfficientMarkovModel(int n) {
        key_len = n;
        myRandom = new Random();
        follows = new HashMap<String, ArrayList<String>>();
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
            ArrayList<String> tmp = follows.get(key);
            if(tmp.size() == 0)
                break;
            index = myRandom.nextInt(tmp.size());
            String next = tmp.get(index);
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
        return "This is a EfficientMarkovModel of order " + key_len + ".";
    }
}
