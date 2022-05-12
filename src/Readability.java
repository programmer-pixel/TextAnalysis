import java.util.ArrayList;

public class Readability {
    public static void main(String[] args) {
        //testSyllableMethod();

        String test = TextLib.readFileAsString("data/Texts/AllTexts/Amazon-adv.txt");
        //System.out.println(test);

        ArrayList<String> sentences = TextLib.splitIntoSentences(test);

        for (String sentence : sentences) {
            System.out.println(sentence.length() + ": " + sentence);
        }

        // TODO:  Break each sentence into words.
        // TODO:  Force to lower-case and strip out all puctuation for doing syllable counts.
    }

    private static void testSyllableMethod() {
        ArrayList<Word> words = TextLib.readSyllablesFile("data/syllables.txt");

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

