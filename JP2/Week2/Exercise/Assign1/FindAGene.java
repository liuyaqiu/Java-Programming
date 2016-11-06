
/**
 * This is assignment1 of week2: find a gene in a string.
 * 
 * @liuyaqiu
 * @2016-07-31
 */

import edu.duke.*;

public class FindAGene {
    public String findProtein(String dna) {
        dna = dna.toUpperCase();
        int start = dna.indexOf("ATG");
        if(start == -1)
            return "";
        else {
            int stop = dna.indexOf("TAG", start + 3);
            if(stop == -1 || (stop - start) %3 != 0) {
                stop = dna.indexOf("TGA", start + 3);
                if(stop == -1 || (stop - start) % 3 != 0)
                    stop = dna.indexOf("TAA", start + 3);
            }
            if(stop == -1 || (stop - start) % 3 != 0)
                return "";
            else
                return dna.substring(start, stop + 3);
        }
    }
    
    public String stopCodon(String protein) {
        if(protein.isEmpty())
            return "";
        else
            return protein.substring(protein.length() - 3, protein.length());
    }
    
    public void test(String dna) {
        dna = dna.toUpperCase();
        String protein = findProtein(dna);
        String end = stopCodon(protein);
        if(end.isEmpty()) {
            System.out.println("The string " + dna + " doesn't have a protein!");
        }
        else {
            System.out.println("The protein is " + protein);
            System.out.println("The stop codon is " + end);
        }
    }

}
