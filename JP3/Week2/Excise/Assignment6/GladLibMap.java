import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCate;
    private int replaced;
    
    private Random myRandom;
    
    private static String dataSourceURL = "/home/liuyaqiu/course/JP3/data";
    private static String dataSourceDirectory = "data/";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        replaced = 0;
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        replaced = 0;
    }
    
    private void initializeFromSource(String source) {
        myMap = new HashMap<String, ArrayList<String>>();
        String[] categroies = {"adjective", "noun", "color", "country",
            "name", "animal", "timeframe", "verb", "fruit"};
        for(String cate: categroies) {
            String name = source + cate + ".txt";
            myMap.put(cate, readIt(name));
        }
        usedList = new ArrayList<String>();
        usedCate = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        for(String cate: myMap.keySet()) {
            if(label.equals(cate))
                return randomFrom(myMap.get(cate));
        }
        if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		else
            return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String label = w.substring(first + 1, last);
        if(myMap.containsKey(label)) {
            if(!usedCate.contains(label))
                usedCate.add(label);
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedList.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedList.add(sub);
        replaced += 1;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int sum = 0;
        for(String cate: myMap.keySet()) {
            sum += myMap.get(cate).size();
        }
        return sum;
    }
    
    public int totalWordsConsidered() {
        int sum = 0;
        for(String cate: usedCate) {
            sum += myMap.get(cate).size();
        }
        return sum;
    }
    
    public void makeStory(){
        usedList.clear();
        usedCate.clear();
        replaced = 0;
        String story = fromTemplate("data/madtemplate2.txt");
        System.out.println("The number of all words picked from: " + totalWordsInMap());
        System.out.println("The number of concidered words: " + totalWordsConsidered());
        printOut(story, 60);
        System.out.println("\nNumber of replaced words: " + replaced + "\n");
    }
    


}
