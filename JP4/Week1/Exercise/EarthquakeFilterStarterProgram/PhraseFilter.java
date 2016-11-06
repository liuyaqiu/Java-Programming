
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String indicate;
    private String phrase;
    public PhraseFilter(String indi, String ph) {
        indicate = indi;
        phrase = ph;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        int index;
        switch(indicate) {
            case "start":
                index = title.indexOf(phrase);
                return index == 0;
            case "end":
                index = title.lastIndexOf(phrase);
                return index == title.length() - phrase.length();
            case "any":
                index = title.indexOf(phrase);
                return index != -1;
            default:
                return false;
        }
    }
    
    public String getName() {
        return "Phrase";
    }
}
