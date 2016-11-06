/**
 * This is assignment5 of Week1.
 * 
 * @liuyaqiu
 * @2016-08-10
 */

import edu.duke.*;
import java.io.*;

public class TestCaesarCipher {
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
    
    public String breakCaesarCipher(String input) {
        int[] counts = new int[26];
        countLetters(input, counts);
        int key = (maxIndex(counts) - 4 + 26) % 26;
        System.out.println(key);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }
    
    public void simpleTest() {
        FileResource file = new FileResource();
        String msg = file.asString();
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        String enc = cc.encrypt(msg);
        String dec1 = cc.decrypt(enc);
        String dec2 = breakCaesarCipher(enc);
        System.out.println("Message: \n" + msg);
        System.out.println("Encrypted: \n" + enc);
        System.out.println("Decrypted: \n" + dec1);
        System.out.println("Cracked: \n" + dec2);
    }
}
