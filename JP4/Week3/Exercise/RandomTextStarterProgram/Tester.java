
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        String test = "this is a test yes this is a test.";
        MarkovOne markov_one = new MarkovOne();
        markov_one.setTraining(test);
        ArrayList<String> follows = markov_one.getFollows("es");
        System.out.println("The size of follows is: " + follows.size());
        for(String fo: follows) {
            System.out.print(fo + " ");
        }
        System.out.println();
    }

    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String test = fr.asString();
        MarkovOne markov_one = new MarkovOne();
        markov_one.setTraining(test);
        ArrayList<String> follows = markov_one.getFollows("th");
        System.out.println("The size of follows is: " + follows.size());
        for(String fo: follows) {
            System.out.print(fo + " ");
        }
        System.out.println();
    }
}
