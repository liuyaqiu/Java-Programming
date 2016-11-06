
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    private String lastTitleWord(QuakeEntry qe) {
        String[] words = qe.getInfo().split(" ");
        return words[words.length - 1];
    }
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String word1 = lastTitleWord(qe1);
        String word2 = lastTitleWord(qe2);
        int res1 = word1.compareTo(word2);
        if(res1 == 0)
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        else
            return res1;
    }
}
