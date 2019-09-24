import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextLib {

    public static String readFileAsString(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim()+"\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }

    public static ArrayList<String> splitIntoSentences(String text) {
        ArrayList<String> output = new ArrayList<>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while(boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length()>0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length()>0)
            output.add(sentence);

        return output;
    }

    public static ArrayList<Word> readSyllablesFile(String filename) {
        Scanner scanner;
        ArrayList<Word> words = new ArrayList<Word>();

        try {
            scanner = new Scanner(new FileReader(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // ------- process the line here -------
                String word = getWordFromLine(line);
                int syllables = getSyllablesFromLine(line);

                // -------------------------------------
                Word w = new Word(word, syllables);
                words.add(w);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return words;
    }

    private static int getSyllablesFromLine(String line) {
        int start = line.indexOf("=") + 1;
        int count = 0;

        for (; start < line.length(); start++) {
            if (line.substring(start, start+1).equals("*")) count++;
        }

        return count+1;
    }

    private static String getWordFromLine(String line) {
        return line.substring(0, line.indexOf("="));
    }


}
