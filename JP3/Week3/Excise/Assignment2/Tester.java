
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testUniqueIP() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("short-test_log");
        System.out.println("Num of unique IPs is " + log.countUniqueIPs());
    }
    
    public void testUniqueIPVisitsOnDay() {
        String someday = "Mar 24";
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog1_log");
        System.out.println(log.uniqueIPVisitsOnDay(someday).size());
    }
    
    public void testCountUniqueIPsInRange() {
        int low = 300, high = 399;
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog1_log");
        System.out.println("Num of status in range " + low + " and " + high +
        " is " + log.countUniqueIPsInRange(low, high));
    }
}
