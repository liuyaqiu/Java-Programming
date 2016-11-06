import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f1 = new MagnitudeFilter(4.0, 5.0);
        //Filter f2 = new DepthFilter(-35000, -12000);
        //ArrayList<QuakeEntry> m1 = filter(list, f1);
        //ArrayList<QuakeEntry> m2 = filter(m1, f2);
        Location japan = new Location(35.42, 139.43);
        double distance = 10000000;
        Filter f1 = new DistanceFilter(distance, japan);
        Filter f2 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m1 = filter(list, f1);
        ArrayList<QuakeEntry> m2 = filter(list, f2);
        for (QuakeEntry qe: m2) { 
            System.out.println(qe);
        } 
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Filter f1 = new MagnitudeFilter(0.0, 2.0);
        Filter f2 = new DepthFilter(-100000, -10000);
        Filter f3 = new PhraseFilter("any", "a");
        MatchAllFilter f4 = new MatchAllFilter();
        f4.addFilter(f1);
        f4.addFilter(f2);
        MatchAllFilter f5 = new MatchAllFilter();
        f5.addFilter(f3);
        f5.addFilter(f4);
        ArrayList<QuakeEntry> m = filter(list, f5);
        System.out.println("Filters used are: " + f5.getName());
        for (QuakeEntry qe: m) { 
            System.out.println(qe);
        } 
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        Location loc = new Location(35.42, 139.43);
        double dis = 10000000;
        Filter f1 = new DistanceFilter(dis, loc);
        Filter f2 = new MagnitudeFilter(0.0, 3.0);
        Filter f3 = new PhraseFilter("end", "Japan");
        Filter f1 = new MagnitudeFilter(0.0, 2.0);
        Filter f2 = new DepthFilter(-100000, -10000);
        Filter f3 = new PhraseFilter("any", "a");
        */
        Location loc = new Location(36.1314, -95.9372);
        double dis = 10000000;
        Filter f1 = new DistanceFilter(dis, loc);
        Filter f2 = new MagnitudeFilter(0.0, 3.0);
        Filter f3 = new PhraseFilter("any", "Ca");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(f1);
        //maf.addFilter(f2);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> m = filter(list, maf);
        System.out.println("Filters used are: " + maf.getName());
        for (QuakeEntry qe: m) { 
            System.out.println(qe);
        } 
        System.out.println("Matched quakes: " + m.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
