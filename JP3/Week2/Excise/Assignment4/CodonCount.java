
/**
 * This is the Assignment4 of Week2 JP3.
 * 
 * @liuyaqiu
 * @2016-08-21
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codons;
    
    public CodonCount() {
        codons = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        codons.clear();
        dna = dna.toUpperCase().trim();
        while(start + 3 <= dna.length()) {
            String codon = dna.substring(start, start + 3);
            if(codons.containsKey(codon)) {
                int value = codons.get(codon);
                codons.replace(codon, value + 1);
            }
            else
                codons.put(codon, 1);
            start += 3;
        }
    }
    
    public String getMostCommonCodon() {
        int common = 0;
        String res = "";
        for(String codon: codons.keySet()) {
            int count = codons.get(codon);
            if(count > common) {
                res = codon;
                common = count;
            }
        }
        return res;
    }
    
    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and "
        + end + " inclusive are: ");
        for(String codon: codons.keySet()) {
            int count = codons.get(codon);
            if(count >= start && count <= end)
                System.out.println(codon + " " + count);
        }
        System.out.println();
    }
    
    public void tester(int start) {
        FileResource file = new FileResource();
        String dna = file.asString();
        buildCodonMap(start, dna);
        System.out.println("Reading frame starting with " + start
        + " result in " + codons.size() + " unique codons");
        String common = getMostCommonCodon();
        System.out.println("and most common codon is " + common
        + " with count " + codons.get(common));
        printCodonCounts(1, 100);
    }
}
