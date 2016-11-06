
/**
 * This is assignment3 of week2: find all gene of a dna.
 * 
 * @liuyaqiu
 * @2016-07-31
 */
import edu.duke.*;

public class FindAllGene {
    public int findStopIndex(String dna, int index) {
        int stop1 = dna.toUpperCase().indexOf("TAG", index);
        int stop2 = dna.toUpperCase().indexOf("TGA", index);
        int stop3 = dna.toUpperCase().indexOf("TAA", index);
        stop1 = (stop1 == -1 || (stop1 - index) % 3 != 0) ? dna.length() : stop1;
        stop2 = (stop2 == -1 || (stop2 - index) % 3 != 0) ? dna.length() : stop2;
        stop3 = (stop3 == -1 || (stop3 - index) % 3 != 0) ? dna.length() : stop3;
        int stop = Math.min(stop1, Math.min(stop2, stop3));
        if(stop < dna.length())
            return stop;
        else
            return -1;
    }

    public void printAll(String dna) {
        System.out.println("The gene of " + dna + ":");
        int start = dna.toUpperCase().indexOf("ATG");
        while(start != -1) {
            int end = findStopIndex(dna, start + 3);
            if(end != -1) {
                System.out.println(dna.substring(start, end + 3));
                start = dna.toUpperCase().indexOf("ATG", end + 3);
            }
            else
                start = dna.toUpperCase().indexOf("ATG", start + 3);
        }
        System.out.println();
    }

    public void testFinder() {
        String dna1 = "ATGAAATGAAAA";
        String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        printAll(dna1);
        printAll(dna2);
        printAll(dna3);
    }
}
