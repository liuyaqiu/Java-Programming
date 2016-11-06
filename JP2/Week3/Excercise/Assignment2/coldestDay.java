
/**
 * This is assignment2 of Week3: coldest day of a year.
 * 
 * @liuyaqiu
 * @2016-08-04
 */

import edu.duke.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import org.apache.commons.csv.*;

public class coldestDay {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        for(CSVRecord record : parser) {
            if(coldest == null)
                coldest = record;
            else {
                double temp = Double.parseDouble(record.get("TemperatureF"));
                double coldest_temp = Double.parseDouble(coldest.get("TemperatureF"));
                if(temp > -999 && temp < coldest_temp)
                    coldest = record;
            }
        }
        return coldest;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        String temp = coldest.get("TemperatureF");
        String time = coldest.get("DateUTC");
        System.out.println("The coldest temperature is " + temp + " at " + time);
    }
    
    public double coldestTemp(File file) {
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        return Double.parseDouble(coldest.get("TemperatureF"));
    }
    
    public File fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        double coldest = 100000;
        File res = null;
        for(File file : dr.selectedFiles()) {
            double cur = coldestTemp(file);
            if(cur < coldest) {
                coldest = cur;
                res = file;
            }
        }
        return res;
    }
    
    public void printTemp(File file) {
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser) {
            String date = record.get("DateUTC");
            String temp = record.get("TemperatureF");
            System.out.println(date + ": " + temp);
        }
    }
    
    public void testFileWithColdestTemperature() {
        File file = fileWithColdestTemperature();
        String file_name = file.getName();
        double temp = coldestTemp(file);
        System.out.println("Coldest day was in file " + file_name);
        System.out.println("Coldest temperature on that day was " + temp);
        System.out.println("All the Temperatures on the coldest day were:");
        printTemp(file);
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord res = null;
        for(CSVRecord record : parser) {
            String humi = record.get("Humidity");
            if(!humi.equals("N/A")) {
                if(res == null)
                    res = record;
                else {
                    double value = Double.parseDouble(humi);
                    double res_value = Double.parseDouble(res.get("Humidity"));
                    if(value < res_value)
                        res = record;
                }
            }
        }
        return res;
    }
    
    public void testLowestHumidityInFile() {
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        String date = record.get("DateUTC");
        String humi = record.get("Humidity");
        System.out.println("Lowest Humidity was " + humi + " at " + date);
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord res = null;
        for(File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord cur = lowestHumidityInFile(parser);
            if(cur != null) {
                if(res != null) {
                    double cur_value = Double.parseDouble(cur.get("Humidity"));
                    double res_value = Double.parseDouble(res.get("Humidity"));
                    if(cur_value < res_value)
                        res = cur;
                }
                else
                    res = cur;
            }
        }
        return res;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        String date = record.get("DateUTC");
        String humi = record.get("Humidity");
        System.out.println("Lowest Humidity was " + humi + " at " + date);
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        double cnt = 0;
        for(CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            sum += temp;
            cnt += 1;
        }
        return sum / cnt;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int high) {
        double sum = 0;
        double cnt = 0;
        for(CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            String humi = record.get("Humidity");
            if(!humi.equals("N/A")) {
                double humi_val = Double.parseDouble(humi);
                if(humi_val >= high) {
                    sum += temp;
                    cnt += 1;
                }
            }
        }
        if(cnt == 0)
            return -1;
        else
            return sum / cnt;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(average < 0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average temperature when high humidity in file is " + average);
    }
}
