
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double minimum;
    private double maximum;
    public MagnitudeFilter(double min, double max) {
        minimum = min;
        maximum = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        double mag = qe.getMagnitude();
        return (mag >= minimum && mag <= maximum);
    }
    
    public String getName() {
        return "Magnitude";
    }
}
