import java.util.*;
import edu.duke.*;
import java.text.DecimalFormat;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData) {
            if(qe.getMagnitude() > magMin)
                answer.add(qe);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData) {
            Location lc = qe.getLocation();
            if(lc.distanceTo(from) < distMax)
                answer.add(qe);
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData) {
            double depth = qe.getDepth();
            if(depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String indicate, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData) {
            int index = -1;
            String title = qe.getInfo();
            switch(indicate) {
                case "start":
                    index = title.indexOf(phrase);
                    if(index == 0)
                        answer.add(qe);
                    break;
                case "end":
                    index = title.lastIndexOf(phrase);
                    if(index == title.length() - phrase.length())
                        answer.add(qe);
                    break;
                case "any":
                    index = title.indexOf(phrase);
                    if(index != -1)
                        answer.add(qe);
                    break;
                default:
                    break;
            }
        }
        return answer;
    }   

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double magMin = 5.0;
        ArrayList<QuakeEntry> big = filterByMagnitude(list, magMin);
        for(QuakeEntry qe: big) {
            System.out.println(qe);
        }
        System.out.println("found " + big.size() + " quakes that match tha criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        double distMax = 1000000;
        DecimalFormat df = new DecimalFormat("#.###");
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, distMax, city);
        for(QuakeEntry qe: close) {
            double distance = qe.getLocation().distanceTo(city) / 1000;
            System.out.println(df.format(distance) + " " + qe.getInfo());
        }
        System.out.println("found " + close.size() + " quakes that match that criteria");
        // TODO
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -10000;
        double maxDepth = -8000;
        ArrayList<QuakeEntry> res = filterByDepth(list, minDepth, maxDepth);
        for(QuakeEntry qe: res) {
            System.out.println(qe);
        }
        System.out.println("found " + res.size() + " quakes that match that criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String indicate = "any";
        String phrase = "Creek";
        ArrayList<QuakeEntry> res = filterByPhrase(list, indicate, phrase);
        for(QuakeEntry qe: res) {
            System.out.println(qe);
        }
        System.out.println("found " + res.size() + " quakes that match " + phrase + " at " + indicate);
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
