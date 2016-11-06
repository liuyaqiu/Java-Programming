
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public void test() {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("weblog1_log");
        String day = "Mar 17";
        HashMap<String, Integer> visits = test.countVisitsPerIP();
        HashMap<String, ArrayList<String>> ips_for_days = test.iPsForDays();
        System.out.println(test.iPsWithMostVisitsOnDay(ips_for_days, day));
        System.out.println(test.dayWithMostIPVisits(ips_for_days));
        System.out.println(test.iPsMostVisits(visits));
        System.out.println(test.mostNumberVisitsByIP(visits));
    }
}
