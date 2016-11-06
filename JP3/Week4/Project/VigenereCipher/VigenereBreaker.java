import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder build = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices) {
            build.append(message.charAt(i));
        }
        return build.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker caesar = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i += 1) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = caesar.getKey(slice);
            //System.out.println(key[i]);
        }
        return key;
    }
    
    public HashSet<String> readDictionary() {
        FileResource file = new FileResource();
        HashSet<String> dict = new HashSet<String>();
        for(String line: file.lines()) {
            line = line.toLowerCase();
            dict.add(line);
        }
        return dict;
    }
    
    public int countWords(String msg, HashSet<String> dict) {
        String[] words = msg.split("\\W+");
        int cnt = 0;
        for(String word: words) {
            if(dict.contains(word.toLowerCase()))
                cnt += 1;
        }
        return cnt;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict, char common) {
        int start = 1;
        int end = 101;
        int maxCount = 0;
        int klength = 1;
        String res = "";
        for(int i = start; i < end; i += 1) {
            int[] key = tryKeyLength(encrypted, i, common);
            VigenereCipher vigenere = new VigenereCipher(key);
            String dec = vigenere.decrypt(encrypted);
            int cnt = countWords(dec, dict);
            if(cnt > maxCount) {
                maxCount = cnt;
                res = dec;
                klength = i;
            }
        }
        return res;
    }
    
    public char mostCommonCharIn(HashSet<String> dict) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for(String word: dict) {
            for(char c: word.toCharArray()) {
                int index = alphabet.indexOf(c);
                if(index != -1)
                    count[index] += 1;
            }
        }
        int max = 0;
        int commonIndex = 0;
        for(int i = 0; i < count.length; i += 1) {
            if(count[i] > max) {
                max = count[i];
                commonIndex = i;
            }
        }
        return alphabet.charAt(commonIndex);
    }
    
    public String breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxWordsCount = 0;
        String res = "";
        String select = "";
        for(String lang: languages.keySet()) {
            HashSet<String> dict = languages.get(lang);
            char common = mostCommonCharIn(languages.get(lang));
            String dec = breakForLanguage(encrypted, dict, common);
            int cnt = countWords(dec, dict);
            if(cnt > maxWordsCount) {
                maxWordsCount = cnt;
                res = dec;
                select = lang;
            }
        }
        System.out.println("Select lang: " + select + "\n");
        System.out.println("Decrypted message: \n" + res);
        return res;
    }
    
    public HashMap<String, HashSet<String>> getAllDicts() {
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> dicts = new HashMap<String, HashSet<String>>();
        for(File file: dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            HashSet<String> dict = new HashSet<String>();
            for(String line: fr.lines()) {
                line = line.toLowerCase();
                dict.add(line);
            }
            dicts.put(file.getName(), dict);
        }
        return dicts;
    }

    public void breakVigenere () {
        FileResource file = new FileResource();
        String msg = file.asString();
        HashMap<String, HashSet<String>> dicts = getAllDicts();
        //HashSet<String> dict = readDictionary();
        //String dec = breakForLanguage(msg, dict);
        //System.out.println("Decrypted: \n" + dec); 
        //int[] key = tryKeyLength(msg, 38, 'e');
        //VigenereCipher vigenere = new VigenereCipher(key);
        //String dec = vigenere.decrypt(msg);
        //System.out.println(countWords(dec, dict));
        //System.out.println("Decrypted: \n" + dec);
        breakForAllLanguages(msg, dicts);
    }
    
}
