
/**
 * This is assignment4 of week2: store all genes.
 * 
 * @liuyaqiu
 * @v2016-07-31
 */

import edu.duke.*;
import java.io.*;

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

    public StorageResource storeAll(String dna) {
        StorageResource store = new StorageResource();
        int start = dna.toUpperCase().indexOf("ATG");
        while(start != -1) {
            int end = findStopIndex(dna, start + 3);
            if(end != -1) {
                store.add(dna.substring(start, end + 3));
                start = dna.toUpperCase().indexOf("ATG", end + 3);
            }
            else
                start = dna.toUpperCase().indexOf("ATG", start + 3);
        }
        return store;
    }
    
    public void testStorageFinder() {
        FileResource file = new FileResource();
        StorageResource store = storeAll(file.asString());
        System.out.println("Number of genes: " + store.size());
    }
    
    public float cgRatio(String dna) {
        dna = dna.toUpperCase();
        int c_num = 0;
        int pos = dna.indexOf('C');
        while(pos != -1) {
            c_num += 1;
            pos = dna.indexOf('C', pos + 1);
        }
        int g_num = 0;
        pos = dna.indexOf('G');
        while(pos != -1) {
            g_num += 1;
            pos = dna.indexOf('G', pos + 1);
        }
        return (c_num + g_num) / (float) dna.length();
    }
    
    public void printGenes(StorageResource sr) {
        int longGene = 0;
        System.out.println("These is the gene whose length is greater than 60:");
        for(String gene : sr.data()) {
            if(gene.length() > 60) {
                longGene += 1;
                System.out.println(gene);
            }
        }
        System.out.println("The number of these genes is " + longGene);
        int cgGene = 0;
        System.out.println("These is the gene whose cgRatio is greater than 0.35:");
        for(String gene : sr.data()) {
            if(cgRatio(gene) > 0.35) {
                cgGene += 1;
                System.out.println(gene);
            }
        }
        System.out.println("The number of these genes is " + cgGene);
    }

    public void testFinder() {
        String dna1 = "ATGAAATGAAAA";
        String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        printAll(dna1);
        printAll(dna2);
        printAll(dna3);
    }
    
    public void testingStorage() {
        FileResource file = new FileResource();
        StorageResource sr = storeAll(file.asString());
        printGenes(sr);
    }
}

