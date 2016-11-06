
/**
 * This is the assignment5 of Week2 JP3.
 * 
 * @liuyaqiu
 * @2016-08-21
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> fileWords;
    
    public WordsInFiles() {
        fileWords = new HashMap<String, ArrayList<String>>();
    }
    
    public void addWordsFromFile(File f) {
        String name = f.getName();
        FileResource file = new FileResource(f);
        for(String word: file.words()) {
            if(fileWords.containsKey(word)) {
                ArrayList<String> fileNames = fileWords.get(word);
                if(!fileNames.contains(name))
                    fileNames.add(name);
            }
            else {
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(name);
                fileWords.put(word, fileNames);
            }
        }
    }
    
    public void buildWordFileMap() {
        DirectoryResource dr = new DirectoryResource();
        fileWords.clear();
        for(File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int max = 0;
        for(String word: fileWords.keySet()) {
            ArrayList<String> fileNames = fileWords.get(word);
            int count = fileNames.size();
            if(count > max)
                max = count;
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFile(int num) {
        ArrayList<String> res = new ArrayList<String>();
        for(String word: fileWords.keySet()) {
            ArrayList<String> fileNames = fileWords.get(word);
            int count = fileNames.size();
            if(count == num)
                res.add(word);
        }
        return res;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> fileNames = fileWords.get(word);
        for(String name: fileNames) {
            System.out.println(name);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        //int max = maxNumber();
        int max = 4;
        String judge = "red";
        printFilesIn(judge);
        System.out.println("The max number of count is " + max);
        ArrayList<String> maxWords = wordsInNumFile(max);
        System.out.println("The num of maxWords is " + maxWords.size());
        for(String word: maxWords) {
            System.out.println("The files contains " + word + ": ");
            printFilesIn(word);
        }
        System.out.println();
    }
}
