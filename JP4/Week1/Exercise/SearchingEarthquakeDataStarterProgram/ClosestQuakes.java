
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        for(QuakeEntry qe: quakeData) {
            if(ret.isEmpty())
                ret.add(qe);
            else {
                for(int i = 0; i < ret.size(); i += 1) {
                    double cur = qe.getLocation().distanceTo(current);
                    double tmp = ret.get(i).getLocation().distanceTo(current);
                    if( cur <= tmp) {
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

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,30);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
