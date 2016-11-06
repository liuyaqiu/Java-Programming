
/**
 * This is assignment2 of Week2 JP3.
 * 
 * @liuyaqiu
 * @2016-08-21
 */

import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        person = person.trim();
        int index = names.indexOf(person);
        if(index == -1) {
            names.add(person);
            counts.add(1);
        }
        else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }
    
    public void findAllCharacters() {
        names.clear();
        counts.clear();
        FileResource file = new FileResource();
        for(String line: file.lines()) {
            int pos = line.indexOf(".");
            if(pos != -1) {
                String person = line.substring(0, pos);
                update(person);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Counts greater than or equal to " + num1
        + " and less than or equal to " + num2 + ": ");
        for(int i = 0; i < names.size(); i += 1) {
            if(counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(names.get(i) + " " + counts.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        int num1 = 10;
        int num2 = 15;
        charactersWithNumParts(num1, num2);
    }
}
