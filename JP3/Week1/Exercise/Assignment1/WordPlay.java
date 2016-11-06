
/**
 * This is the assignment1 of Week1 of JP2.
 * 
 * @liuyaqiu
 * @2016-08-09
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        char low_ch = Character.toLowerCase(ch);
        for(int i = 0; i < vowels.length; i += 1) {
            if(low_ch == vowels[i])
                return true;
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder builder = new StringBuilder(phrase);
        for(int i = 0; i < builder.length(); i += 1) {
            if(isVowel(builder.charAt(i)))
                builder.setCharAt(i, ch);
        }
        return builder.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder builder = new StringBuilder(phrase);
        for(int i = 0; i < builder.length(); i += 1) {
            char cur = builder.charAt(i);
            if(cur == ch) {
                if(i % 2 == 0)
                    builder.setCharAt(i, '*');
                else
                    builder.setCharAt(i, '+');
            } 
        }
        return builder.toString();
    }
}
