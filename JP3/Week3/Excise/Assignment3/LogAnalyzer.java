
/**
 * This is assignment1 of Week3 JP3.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         records.clear();
         FileResource file = new FileResource(filename);
         for(String line: file.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> visits = new HashMap<String, Integer>();
         for(LogEntry log: records) {
             String ip = log.getIpAddress();
             if(visits.containsKey(ip)) {
                 visits.replace(ip, visits.get(ip) + 1);
             }
             else
                visits.put(ip, 1);
         }
         return visits;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> visits) {
         int max = 0;
         for(int count: visits.values()) {
             if(count > max)
                max = count;
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visits) {
         ArrayList<String> ips = new ArrayList<String>();
         int max = mostNumberVisitsByIP(visits);
         for(String ip: visits.keySet()) {
             int count = visits.get(ip);
             if(count == max)
                ips.add(ip);
         }
         return ips;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> ip_for_days = new HashMap<String, ArrayList<String>>();
         for(LogEntry le: records) {
             String day = le.getAccessTime().toString().substring(4, 10);
             if(ip_for_days.containsKey(day)) {
                 ArrayList<String> ips = ip_for_days.get(day);
                 ips.add(le.getIpAddress());
                 ip_for_days.replace(day, ips);
             }
             else {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(le.getIpAddress());
                 ip_for_days.put(day, ips);
             }
         }
         return ip_for_days;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ip_for_days) {
         String maxDay = "";
         int max = 0;
         for(String day: ip_for_days.keySet()) {
             if(ip_for_days.get(day).size() > max) {
                 max = ip_for_days.get(day).size();
                 maxDay = day;
             }
         }
         return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ips_for_days, String someday) {
         ArrayList<String> ips_on_day = ips_for_days.get(someday);
         HashMap<String, Integer> visits = new HashMap<String, Integer>();
         for(String ip: ips_on_day) {
             if(visits.containsKey(ip)) {
                 visits.replace(ip, visits.get(ip) + 1);
             }
             else {
                 visits.put(ip, 1);
             }
         }
         return iPsMostVisits(visits);
     }
}
