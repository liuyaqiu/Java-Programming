
/**
 * This is assignment6 of Week1.
 * 
 * @liuyaqiu
 * @2016-08-10
 */

import edu.duke.*;
import java.io.*;

public class TestCaesarCipherTwo {
    public String halfOfString(String msg, int pos) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < msg.length(); i += 1) {
            if(i % 2 == pos % 2)
                builder.append(msg.charAt(i));
        }
        return builder.toString();
    }
    
    public void countLetters(String msg, int[] counts) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String up_msg = msg.toUpperCase();
        for(int i = 0; i < up_msg.length(); i += 1) {
            int index = alphabet.indexOf(up_msg.charAt(i));
            if(index != -1)
                counts[index] += 1;
        }
    }
    
    public int maxIndex(int[] counts) {
        int max = counts[0];
        int index = 0;
        for(int i = 0; i < counts.length; i += 1) {
            if(max < counts[i]) {
                max = counts[i];
                index = i;
            }
        }
        return index;
    }
    
    public String breakCaesarCipherTwo(String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);
        int counts1[] = new int[26];
        int counts2[] = new int[26];
        countLetters(half1, counts1);
        countLetters(half2, counts2);
        int key1 = (maxIndex(counts1) - 4 + 26) % 26;
        int key2 = (maxIndex(counts2) - 4 + 26) % 26;
        System.out.println("The pair keys: " + key1 + ", " + key2);
        CaesarCipherTwo caesar = new CaesarCipherTwo(key1, key2);
        return caesar.decrypt(input);
    }
    
    public void simpleTests() {
        FileResource file = new FileResource();
        String msg = file.asString();
        int key1 = 8, key2 = 21;
        CaesarCipherTwo caesar = new CaesarCipherTwo(key1, key2);
        String enc = caesar.encrypt(msg);
        String dec = caesar.decrypt(enc);
        String crack = breakCaesarCipherTwo(msg);
        System.out.println("Message: \n" + msg);
        System.out.println("Encrypted: \n" + enc);
        System.out.println("Decrypted: \n" + dec);
        System.out.println("Cracked: \n" + dec);
    }
    
    public void test() {
        FileResource file = new FileResource();
        String msg = file.asString();
        int key1 = 2, key2 = 20;
        System.out.println("Encrypted: \n" + msg);
        CaesarCipherTwo caesar = new CaesarCipherTwo(key1, key2);
        String dec = caesar.decrypt(msg);
        System.out.println("Decrypted: \n" + dec);
        String crack = breakCaesarCipherTwo(msg);
        System.out.println("Crackded: \n" + crack);
    }
}
