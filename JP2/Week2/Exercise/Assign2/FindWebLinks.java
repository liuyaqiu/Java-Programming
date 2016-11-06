
/**
 * This is assignment2 of Week2: FindWebLinks.
 * 
 * @liuyaqiu
 * @2016-07-31
 */

import edu.duke.*;
import java.io.*;

public class FindWebLinks {
    public int linkStart(String str, String match) {
        int pos = str.indexOf(match);
        int start = str.lastIndexOf("\"", pos);
        return start;
    }
    
    public int linkStop(String str, String match) {
        int pos = str.indexOf(match);
        int stop = str.indexOf("\"", pos);
        return stop;
    }
    
    public void getLinks(String URL) {
        URLResource url = new URLResource(URL);
        for(String word : url.words()) {
            String str = word.toLowerCase();
            String match = "youtube.com";
            int start = linkStart(str, match);
            int stop = linkStop(str, match);
            if(start != -1 && stop != -1)
                System.out.println(word.substring(start + 1, stop));
        }
    }
}
