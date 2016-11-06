
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
   public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)) {
                System.out.println("Num of passes: " + i);
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> in, int start) {
        double largest = in.get(start).getDepth();
        int index = start;
        for(int i = start + 1; i < in.size(); i += 1) {
            double cur = in.get(i).getDepth();
            if(cur > largest) {
                largest = cur;
                index = i;
            }
        }
        return index;
    }
    
    public void sortByDepth(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size(); i += 1) {
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIndex);
            in.set(i, qmax);
            in.set(maxIndex, qi);
            if(i == 49) {
                System.out.println("The 50 passes last item: " + in.get(in.size() - 1).getDepth());
            }
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> in, int num) {
        for(int i = 0; i < in.size() - 1 - num; i += 1) {
            QuakeEntry curQuake = in.get(i);
            QuakeEntry nextQuake = in.get(i + 1);
            double cur = curQuake.getMagnitude();
            double next = nextQuake.getMagnitude();
            if(cur > next) {
                in.set(i, nextQuake);
                in.set(i + 1, curQuake);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for(int i = 0; i < quakes.size() - 1; i += 1) {
            if(quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude())
                return false;
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubble(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size() - 2; i += 1) {
            onePassBubbleSort(in, i);
        }
    }
    
    public void sortByMagnitudeWithBubbleWithCheck(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size() - 2; i += 1) {
            if(checkInSortedOrder(in)) {
                System.out.println("Num of passes: " + i);
                break;
            }
            onePassBubbleSort(in, i);
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitudeWithBubbleWithCheck(list);
        //sortByDepth(list);
        //sortByDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
