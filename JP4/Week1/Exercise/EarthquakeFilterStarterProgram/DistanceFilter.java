
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private double distance;
    private Location from;
    
    public DistanceFilter(double dist, Location loc) {
        distance = dist;
        from = loc;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double dis = qe.getLocation().distanceTo(from);
        return dis < distance;
    }
    
    public String getName() {
        return "Distance";
    }
}
