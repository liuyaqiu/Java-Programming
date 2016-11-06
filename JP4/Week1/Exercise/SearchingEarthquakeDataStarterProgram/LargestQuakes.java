
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*for(QuakeEntry qe: list) {
            System.out.println(qe);
        }*/
        int maxIndex = indexOfLargest(list);
        System.out.println("index of largest magnitude is: " + maxIndex);
        System.out.printf("largest magnitude is: %.2f\n", list.get(maxIndex).getMagnitude());
        int howMany = 5;
        ArrayList<QuakeEntry> largest = getLargest(list, howMany);
        System.out.printf("the %d largest magnitude entry:\n", howMany);
        for(QuakeEntry qe: largest) {
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int maxIndex = 0;
        double maxMag = 0;
        for(QuakeEntry qe: data) {
            if(qe.getMagnitude() > maxMag) {
                maxIndex = data.indexOf(qe);
                maxMag = qe.getMagnitude();
            }
        }
        return maxIndex;
    }
    
        public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,
        int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        for(QuakeEntry qe: quakeData) {
            if(ret.isEmpty())
                ret.add(qe);
            else {
                for(int i = 0; i < ret.size(); i += 1) {
                    double cur = qe.getMagnitude();
                    double tmp = ret.get(i).getMagnitude();
                    if( cur > tmp) {
                        ret.add(i, qe);
                        break;
                    }
                }
                if(ret.indexOf(qe) == -1)
                    ret.add(qe);
            }
        }
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>(
        ret.subList(0, howMany < ret.size() ? howMany : ret.size()));
        return res;
    }
}
