
/**
 * This is the mini project of this course: count baby names.
 * 
 * @liuyaqiu
 * @2016-08-05
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
import java.util.*;

public class babyNames {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int boyBirths = 0;
        int girlBirths = 0;
        int uniqueBoyNames = 0;
        int uniqueGirlNames = 0;
        for(CSVRecord record : fr.getCSVParser(false)) {
            String gender = record.get(1);
            int num = Integer.parseInt(record.get(2));
            totalBirths += num;
            if(gender.equals("M")) {
                boyBirths += num;
                uniqueBoyNames += 1;
            }
            else {
                girlBirths += num;
                uniqueGirlNames += 1;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Boy births = " + boyBirths);
        System.out.println("Unique boy names = " + uniqueBoyNames);
        System.out.println("Girl births = " + girlBirths);
        System.out.println("Unique girl names = " + uniqueGirlNames);
    }
    
    public void testTotalBirths() {
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles()) {
            System.out.println("The info of " + file.getName() + ":");
            FileResource fr = new FileResource(file);
            totalBirths(fr);
            System.out.println();
        }
    }
    
    public int getRank(Iterable<File> files, int year, String name, String gender) {
        boolean isBoy = false;
        for(File file : files) {
            FileResource fr = new FileResource(file);
            String str_year = Integer.toString(year);
            if(file.getName().contains(str_year)) {
                int rank = 1;
                for(CSVRecord record : fr.getCSVParser(false)) {
                    String _name = record.get(0);
                    String _gender = record.get(1);
                    if(_gender.equals("M") && !isBoy){
                        isBoy = true;
                        rank = 1;
                    }
                    if(name.equals(_name) && gender.equals(_gender))
                        return rank;
                    else
                        rank += 1;
                }
                return -1;
            }
        }
        return -1;
    }
    
    public void testGetRank() {
        String name = "Sophia";
        String gender = "F";
        int year = 2012;
        System.out.println("Please select input data files!");
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> files = dr.selectedFiles();
        System.out.println("The rank of " + name + " in " + year + " is gender " + gender + " : " + 
        + getRank(files, year, name, gender));
    }
    
    public String getName(Iterable<File> files, int year, int rank, String gender) {
        boolean changeGender = false;
        for(File file : files) {
            FileResource fr = new FileResource(file);
            String str_year = Integer.toString(year);
            List<CSVRecord> records = null;
            if(file.getName().contains(str_year)) {
                try {
                     records = fr.getCSVParser(false).getRecords();
                }
                catch(IOException e) {
                    System.out.println(e);
                }
                int boyIndex = 0;
                while(records.get(boyIndex).get(1).equals("F")) {
                    boyIndex += 1;
                }
                int index;
                if(gender.equals("M"))
                    index = rank - 1 + boyIndex;
                else
                    index = rank - 1;
                if(index >= records.size())
                    return "NO NAME1";
                else {
                    CSVRecord rec = records.get(index);
                    return rec.get(0);
                }
            }
        }
        return "NO NAME2";
    }
    
    public void testGetName() {
        String gender = "M";
        int rank = 2;
        int year = 2012;
        System.out.println("Please select input data files!");
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> files = dr.selectedFiles();
        System.out.println("The rank at " + rank + " in " + year + " is gender " + gender
        + " : " + getName(files, year, rank, gender));
    }
    
    public String whatIsNameInYear(int year, int newYear, String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> files = dr.selectedFiles();
        int rank = getRank(files, year, name, gender);
        return getName(files, newYear, rank, gender);
    }
    
    public void testWhatIsNameInYear() {
        String name = "Isabella";
        String gender = "F";
        int year = 2012;
        int newYear = 2014;
        String newName = whatIsNameInYear(year, newYear, name, gender);
        System.out.println(name + " born in " + year + " would be " + 
        newName + " if she was born in " + newYear);
    }
    
    public int getYearOfFile(File file) {
        String fname = file.getName();
        return Integer.parseInt(fname.substring(3, 7));
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> files = dr.selectedFiles();
        int maxRank = -1;
        int year = 0;
        String res = "NONE";
        for(File file : files) {
            int curYear = getYearOfFile(file);
            int curRank = getRank(files, year, name, gender);
            if(maxRank < 0) {
                maxRank = curRank;
                year = curYear;
            }
            else {
                if(curRank > 0 && curRank < maxRank) {
                    maxRank = curRank;
                    year = curYear;
                }
            }
        }
        return year;
    }
    
    public void testYearOfHighestRank() {
        String name = "Mason";
        String gender = "M";
        System.out.println("The highest rank year of " + name + " is " + 
        yearOfHighestRank(name, gender));
    }
    
    public double averageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        Iterable<File> files = dr.selectedFiles();
        int sum = 0;
        int cnt = 0;
        for(File file : files) {
            int curYear = getYearOfFile(file);
            int curRank = getRank(files, curYear, name, gender);
            if(curRank > 0) {
                sum += curRank;
                cnt += 1;
            }
        }
        if(cnt == 0)
            return -1.0;
        else
            return (float) sum / cnt;
    }
    
    public void testAverageRank() {
        String name = "Jacob";
        String gender = "M";
        System.out.println("The average rank of " + name + " is " + averageRank(name, gender));
    }
    
    public int totalBirthsRankedHigher(int year, String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles()) {
            int sum = 0;
            if(file.getName().contains(Integer.toString(year))) {
                FileResource fr = new FileResource(file);
                try {
                    for(CSVRecord record : fr.getCSVParser(false).getRecords()) {
                        String _name = record.get(0);
                        String _gender = record.get(1);
                        if(!name.equals(_name)) {
                            if(gender.equals(_gender))
                                sum += Integer.parseInt(record.get(2));
                        }
                        else {
                            if(gender.equals(_gender))
                                return sum;
                        }
                    }
                }
                catch(IOException e) {
                    System.out.println(e);
                }
            }
        }
        return 0;
    }
    
    public void testTotalBirthsRankedHigher() {
        String name = "Ethan";
        String gender = "M";
        int year = 2012;
        System.out.println("The num of births ranked higher than " + name + " is " + 
        totalBirthsRankedHigher(year, name, gender));
    }
}
