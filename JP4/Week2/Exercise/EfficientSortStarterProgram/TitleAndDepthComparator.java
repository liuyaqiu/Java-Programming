
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int res1 = qe1.getInfo().compareTo(qe2.getInfo());
        if(res1 != 0)
            return res1;
        else
            return Double.compare(qe1.getDepth(), qe2.getDepth());
    }
}
