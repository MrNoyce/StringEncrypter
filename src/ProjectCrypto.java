import java.lang.*;
import java.util.*;
public class ProjectCrypto {

    private static Scanner userInput = new Scanner(System.in);

    public static void main (String [] args) {

        String encryptMe = getUserInput();
        String phaseOne = normaliseText(encryptMe);
        String phaseTwo = obify(phaseOne);
        String phaseThree = caesarify(phaseTwo, 3);
        String phaseFour = groupify(phaseThree, 3);

        System.out.println(phaseOne);
        System.out.println(phaseTwo);
        System.out.println(phaseThree);
        System.out.println(phaseFour);

    }

    public static String getUserInput() {
        System.out.print("Enter a String to be encrypted: ");
        String userVal = userInput.nextLine();
        return userVal;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
    
    public static String normaliseText(String text ) {
       text = text.replaceAll("\\s+",""); // Remove whitespace
       text = text.replaceAll("\\p{Punct}",""); // Remove punctuation marks
       return text.toUpperCase();
    }

    public static String obify (String text) {
        String obiText = text;
        obiText= obiText.replaceAll("([AEIOUY])", "OB$1");
        return obiText;
    }

    public static String caesarify (String text, int change) {
        String newString = text;
        String result = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet2 = shiftAlphabet(change);
        for (int i = 0; i < newString.length(); i++) {
            for (int y = 0; y < alphabet.length(); y++) {
                if (Character.toString(newString.charAt(i)).equals(Character.toString(alphabet.charAt(y)))) {
                    result += alphabet2.charAt(y);
                }
            }
        }
        return result;
    }

    public static String groupify (String text, int groupSize) {

        String newText = text;
        String space = " ";
        StringBuffer textModified = new StringBuffer(newText);
        String textToParse = "";

        Boolean checker = false;

        while (!checker)
            if (newText.length() % groupSize != 0) {
             newText += "x";
            }else {
                checker = true;
            }

        for (int i = groupSize; i < textModified.length(); i += groupSize + 1) {
            textModified.insert(i, space);
        }

        textToParse = textModified.toString();
        return textToParse;
    }

}
