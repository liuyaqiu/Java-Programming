
/**
 * This is the assginment2 of Week1.
 * 
 * @liuyaqiu
 * @2016-08-09
 */

import java.io.*;
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder builder = new StringBuilder(input.length());
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String en_alphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for(int i = 0; i < input.length(); i+= 1) {
            char cur = input.charAt(i);
            char ch = Character.toUpperCase(cur);
            int index = alphabet.indexOf(ch);
            if(index != -1) {
                char enc = en_alphabet.charAt(index);
                if(Character.isLowerCase(cur))
                    builder.append(Character.toLowerCase(enc));
                else
                    builder.append(enc);
            }
            else {
                builder.append(cur);
            }
        }
        return builder.toString();
    }
    
    public void testEncrpt() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder builder = new StringBuilder(input.length());
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String en1_alphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String en2_alphabet = alphabet.substring(key2) + alphabet.substring(0, key2);
        for(int i = 0; i < input.length(); i+=1) {
            char cur = input.charAt(i);
            char ch = Character.toUpperCase(cur);
            int index = alphabet.indexOf(ch);
            if(index != -1) {
                char enc1 = en1_alphabet.charAt(index);
                char enc2 = en2_alphabet.charAt(index);
                char enc;
                if(i % 2 == 0)
                    enc = enc1;
                else
                    enc = enc2;
                if(Character.isLowerCase(cur))
                    builder.append(Character.toLowerCase(enc));
                else
                    builder.append(enc);
            }
            else
                builder.append(cur);
        }
        return builder.toString();
    }
}
