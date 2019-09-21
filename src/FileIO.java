import javafx.concurrent.Worker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    public static void main(String[] args) {

        ArrayList<Word> words = readSyllablesFile("data/syllables.txt");

        for (Word w : words) {
            System.out.println(w.getWord() + " : " + w.getSyllables());
        }

    }

    public static String readFileAsString(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileReader(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim());
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
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
