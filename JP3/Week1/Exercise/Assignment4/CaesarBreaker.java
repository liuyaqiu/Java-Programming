
/**
 * This is the assignment4 of Week1.
 * 
 * @liuyaqiu
 * @2016-08-09
 */

import edu.duke.*;
import java.io.*;

public class CaesarBreaker {
    public void countLetters(String message, int[] counts) {
        String msg = message.toUpperCase();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < msg.length(); i+=1) {
            int index = alphabet.indexOf(msg.charAt(i));
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
    
    public int getKey(String enc) {
        int[] counts = new int[26];
        countLetters(enc, counts);
        int enc_pos = maxIndex(counts);
        return enc_pos - 4;
    }
    
    public String halfOfString(String msg, int pos) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < msg.length(); i += 1) {
            if(i % 2 == pos % 2)
                builder.append(msg.charAt(i));
        }
        return builder.toString();
    }
    
    public String decrypt(String enc, int key) {
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(enc, (26 - key) % 26);
    }
    
    public String decryptTwoKeys(String message) {
        String enc1 = halfOfString(message, 0);
        String enc2 = halfOfString(message, 1);
        int key1 = getKey(enc1);
        int key2 = getKey(enc2);
        String dec1 = decrypt(enc1, key1);
        String dec2 = decrypt(enc2, key2);
        StringBuilder res = new StringBuilder();
        int pos = 0;
        while(pos < dec1.length() + dec2.length()) {
            if(pos / 2 < dec1.length())
                res.append(dec1.charAt(pos / 2));
            if(pos / 2 < dec2.length())
                res.append(dec2.charAt(pos / 2));
            pos += 2;
        }
        return res.toString();
    }
    
    public void testDecrypt() {
        FileResource file = new FileResource();
        String msg = file.asString();
        System.out.println(decryptTwoKeys(msg));
    }
}
