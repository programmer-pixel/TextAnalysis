import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextLib {
    public static void main(String[] args) {
        
    }

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
                String word = getFile(line);
                double syllables = getScore(line);

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

    private static double getScore(String line) {
        double score = 0;
        String[] words = line.split(",");
        return Double.parseDouble(words[91]);
    }

    private static String getFile(String line) {
        String[] words = line.split(",");
        return words[0];
    }

    public static double totalSyllables(ArrayList wordlist){
        double counter = 0;
        int syllables = 0;
        String vowels = "aeiouy";
        for (int i = 0; i < wordlist.size()-1; i++) {
            String word = (String) wordlist.get(i);
            if(i == 0 && (vowels.contains(word.substring(i,i+1)))){
                counter++;
            }
            else if(vowels.contains(word.substring(i,i+1))){
                for (int j = 0; j < wordlist.indexOf(i)-1; j++) {
                    if(word.substring(j, j+1).contains(vowels)){
                        counter++;
                    }
                }
            }
            else if(!(word.substring(i, i+1).contains(vowels))){
                if(vowels.contains(word.substring(i+1,i+2))){
                    counter++;
                }
            }
            syllables += counter;
        }
        return counter;
    }
    public static ArrayList<DocumentInfo> readDocInfo(String filepath){
        Scanner scanner;
        ArrayList<DocumentInfo> words = new ArrayList<DocumentInfo>();

        try {
            scanner = new Scanner(new FileReader(filepath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String filename = getFile(line);
                double score = getScore(line);

                DocumentInfo d = new DocumentInfo(filename, score);
                words.add(d);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filepath);
        }

        return words;
    }
    private static ArrayList<String> getAllWordsFrom(ArrayList<String> sentences) {
        ArrayList<String> wordlist = new ArrayList<String>();
        for (int i = 0; i < sentences.size(); i++) {
            String words = sentences.get(i);

            String[] word = words.split(",");
            for (int j = 0; j < word.length; j++) {
                words = word[i];
                wordlist.add(words);
            }
        }
        return wordlist;
    }

    private static double FKReadability(ArrayList <String> sentences){
        ArrayList <String> words = getAllWordsFrom(sentences);
        System.out.println(words.size());
        return 206.835 - 1.015 * (double)(words.size()/sentences.size()) - 84.6 * (double)(totalSyllables(words)/words.size());
    }

    public static void testReadability(){
        ArrayList<DocumentInfo> docs = TextLib.readDocInfo("data/Texts/allfeatures-ose-final.csv");
        double totalError = 0;

        for (DocumentInfo file : docs) {
            String filename = file.getFilename();
            String text = TextLib.readFileAsString("data/Texts/AllTexts/" + filename);

            ArrayList<String> sentences = splitIntoSentences(text);

            double prediction = FKReadability(sentences);

            double error = (prediction - file.getReadabilityScore());
            totalError += Math.abs(error);
        }
        System.out.println("Average error is: " + totalError/docs.size());
    }


}



