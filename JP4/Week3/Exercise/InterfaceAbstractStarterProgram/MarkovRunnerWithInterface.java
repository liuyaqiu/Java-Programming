
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.lang.System; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 48;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
        
        EfficientMarkovModel emFour = new EfficientMarkovModel(4);
        runModel(emFour, st, size, seed);

    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        int size = 500;
        int seed = 615;
        //String st = "yes­this­is­a­thin­pretty­pink­thistle";
        runModel(markov, st, size, seed);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        long start = System.nanoTime();
        EfficientMarkovModel emarkov = new EfficientMarkovModel(2);
        runModel(emarkov, st, size, seed);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("Time consumed by efficient: " + time);
        start = System.nanoTime();
        MarkovModel markov = new MarkovModel(2);
        runModel(markov, st, size, seed);
        end = System.nanoTime();
        time = end - start;
        System.out.println("Time consumed by nonefficinet: " + time);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
