
/**
 * This is assignment5 of Week1.
 * 
 * @liuyaqiu
 * @2016-08-10
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder builder = new StringBuilder();
        String up_input = input.toUpperCase();
        for(int i = 0; i < up_input.length(); i += 1) {
            char cur = up_input.charAt(i);
            int index = alphabet.indexOf(cur);
            if(index != -1) {
                char tmp = shiftedAlphabet.charAt(index);
                if(Character.isLowerCase(input.charAt(i)))
                    builder.append(Character.toLowerCase(tmp));
                else
                    builder.append(tmp);
            }
            else
                builder.append(input.charAt(i));
        }
        return builder.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
