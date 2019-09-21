import java.util.ArrayList;
import java.util.SplittableRandom;

public class Readability {
    public static void main(String[] args) {
        ArrayList<Word> words = FileIO.readSyllablesFile("data/syllables.txt");

        double right = 0;
        for (Word w : words) {
            String word = w.getWord();
            int prediction = syllablesFor(word);

            if (prediction == w.getSyllables()) right++;
        }

        System.out.println("You got " + (right/words.size()) + " right");
    }

    private static int syllablesFor(String testWord) {
        boolean inVowelChain = false;
        int boundaries = 0;

        for (int i = 0; i < testWord.length(); i++) {
            String letter = testWord.substring(i, i+1);
            if (isVowel(letter)) {
                if (!inVowelChain) {
                    inVowelChain = true;
                    boundaries++;
                }
            } else {
                inVowelChain = false;
            }
        }

        return boundaries;
    }

    private static boolean isVowel(String letter) {
        return "aeiouy".contains(letter);
    }
}
