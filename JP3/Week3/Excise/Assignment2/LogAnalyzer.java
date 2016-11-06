
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
     
     public int countUniqueIPs() {
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry log: records) {
             String ip = log.getIpAddress();
             if(!unique.contains(ip))
                unique.add(ip);
         }
         return unique.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> res = new ArrayList<String>();
         for(LogEntry log: records) {
             if(log.getAccessTime().toString().indexOf(someday) != -1){
                String ip = log.getIpAddress();
                if(!res.contains(ip))
                    res.add(ip);
             }
         }
         return res;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> res = new ArrayList<String>();
         for(LogEntry log: records) {
             int status = log.getStatusCode();
             if(status >= low && status <= high) {
                 String ip = log.getIpAddress();
                 if(!res.contains(ip))
                    res.add(ip);
             }
         }
         return res.size();
     }
}
