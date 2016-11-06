
/**
 * This is assignment6 of Week1.
 * 
 * @liuyaqiu
 * @2016-08-10
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder builder = new StringBuilder();
        String up_input = input.toUpperCase();
        for(int i = 0; i < up_input.length(); i += 1) {
            char cur = up_input.charAt(i);
            int index1 = alphabet.indexOf(cur);
            int index2 = alphabet.indexOf(cur);
            int index;
            if(i % 2 == 0)
                index = index1;
            else
                index = index2;
            char tmp;
            if(index == -1) {
                tmp = input.charAt(i);
            }
            else {
                if(i % 2 == 0)
                    tmp = shiftedAlphabet1.charAt(index);
                else
                    tmp = shiftedAlphabet2.charAt(index);
            }
            if(Character.isLowerCase(input.charAt(i)))
                builder.append(Character.toLowerCase(tmp));
            else
                builder.append(tmp);
        }
        return builder.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
}
