
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        MarkovWord markov = new MarkovWord(2);
        runModel(markov, st, 120, 832);
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
    }
    
    public void testHashMap() {
        FileResource fr = new FileResource(); 
        String st = fr.asString();
        //String st = "this is a test yes this is really a test yes a test this is wow";
        st = st.replace('\n', ' ');
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        runModel(markov, st, 50, 65);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 42;
        long start = System.nanoTime();
        EfficientMarkovWord emarkov = new EfficientMarkovWord(2);
        runModel(emarkov, st, size, seed);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("Time consumed by efficient: " + time);
        start = System.nanoTime();
        MarkovWord markov = new MarkovWord(2);
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
