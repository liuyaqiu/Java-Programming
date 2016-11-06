
/**
 * This is the assignment1 of Week3: count exports.
 * 
 * @liuyaqiu
 * @2016-08-04
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.lang.*;

public class exports {
    public CSVParser tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }
    
    public void countryInfo(CSVParser parser, String country) {
        boolean hasRes = false;
        for(CSVRecord record : parser) {
            String _country = record.get("Country");
            if(_country.equals(country)) {
                hasRes = true;
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(country + ": " + exports + ": " + value);
            }
        }
        if(!hasRes)
            System.out.println("NOT FOUND");
    }
    
    public void countryInfoTest() {
        CSVParser parser = tester();
        countryInfo(parser, "Nauru");
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public void listTest() {
        CSVParser parser = tester();
        listExportersTwoProducts(parser, "gold", "diamonds");
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int cnt = 0;
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem))
                cnt += 1;
        }
        return cnt;
    }
    
    public void numberTest() {
        CSVParser parser = tester();
        int res = numberOfExporters(parser, "sugar");
        System.out.println(res);
    }
    
    public String toNum(String value) {
        List<Character> num = new ArrayList<Character>();
        for(char digit : value.toCharArray()) {
            if(Character.isDigit(digit))
                num.add(digit);
        }
        char[] res = new char[num.size()];
        for(int i = 0; i < num.size(); i++) {
            res[i] = num.get(i);
        }
        return String.valueOf(res);
    }
    
    public void bigExporters(CSVParser parser, String valueStr) {
        double value = Double.parseDouble(toNum(valueStr));
        for(CSVRecord record : parser) {
            String _valueStr = record.get("Value (dollars)");
            double _value = Double.parseDouble(toNum(_valueStr));
            if(_value > value) {
                String country = record.get("Country");
                System.out.println(country + " " + _valueStr);
            }
        }
    }
    public void bigTest() {
        CSVParser parser = tester();
        bigExporters(parser, "$999,999,999,999");
    }
}
