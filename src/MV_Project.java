import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MV_Project {

    public static void main(String[] args) {
        readFile1AsString("data/Texts/tweets.txt");
        readFile2AsString("data/Texts/TheRacialSlurDatabase.txt");
        int count = countOccurences(readFile2AsString("data/Texts/TheRacialSlurDatabase.txt"),readFile1AsString("data/Texts/tweets.txt"));
        System.out.println(count);
    }


    public static String readFile1AsString(String tweets) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(tweets), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim() + "\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + tweets);
        }

        return output.toString();
    }

    private static String readFile2AsString(String racialSlurs) {
        Scanner scanner;
        StringBuilder output2 = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(racialSlurs), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output2.append(line.trim() + "\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + racialSlurs);
        }

        return output2.toString();
    }


    public static int countOccurences(String word, String text) {
        //counts the number of occurrences of a word in the text
        int count = 0;
        ArrayList<String> sentences = wordsInSentence(text);
        for (int i = 0; i < sentences.size(); i++) {
            String singleSentence = sentences.get(i);
            String[] words = singleSentence.split(" ");
            for (int j = 0; j < words.length; j++) {
                if(words[j].equals(word)){
                    count++;
                }
            }
        }
        return count;
    }

    public static ArrayList<String> wordsInSentence(String text) {
        //returns the ArrayList that contains every sentence in each index
        ArrayList<String> output = new ArrayList<String>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while (boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length() > 0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length() > 0)
            output.add(sentence);
        return output;
    }


    public boolean testForRacism() {

        if (readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains(readFile2AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt"))){
            return true;
        }else {
            return false;
        }
    }

    public boolean ifColor() {

        if (readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains("color")) {
            return true;
        }else {
            return false;
        }
    }

    public boolean targetedGroups(){
        if (readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains("Muslims African American People Whites blacks Folks")){
            return true;
        }else {
            return false;
        }
    }

    public boolean hatefulIntent(){
        boolean racist = true;
        if(readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains(readFile2AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt")) && readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains("All You are terrorists dirty disgusting trash")){
            racist = true;
        }
        if(readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains(readFile2AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt")) && ifColor() == true){
            racist = false;
        }
        if(!readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains(readFile2AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt"))){
            racist = false;
        }
        return racist;
    }



    public ArrayList<String> presence() {
        ArrayList<String> matchingWords = new ArrayList<String>();
        if (readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains(readFile2AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt"))) {
            matchingWords.add(readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt"));
            matchingWords.add("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt");
        }
        return matchingWords;
    }

    public ArrayList<String> absence() {
        ArrayList<String> matchingWords1 = new ArrayList<String>();
        if (!readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt").contains(readFile2AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt"))) {
            matchingWords1.add(readFile1AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/tweets.txt"));
            matchingWords1.add(readFile2AsString("/Users/taanishpatel/IdeaProjects/Mv project/src/TheRacialSlurDatabase.txt"));
        }
        return matchingWords1;
    }

    public String presenceAndAbsence(String output, String output2) {
        String message = "";
        ArrayList presence = presence();
        ArrayList absence = absence();
        if (presence.equals(output2)) {
            message = "This tweet is discrimitory and racist";
        }
        if (output.contains(output2)) {
            message = "This tweet is discrimitory and racist";
        }
        if (!presence.equals(output2) && !output.contains(output2)) {
            message = "This tweet is not racist";
        }
        return message;
    }

    public String returnIfRacist(){
        String returnStatement = "";
        if(testForRacism() == true){
            returnStatement = "The tweet is discriminatory";
        }
        else if(ifColor() == true){
            returnStatement = "The tweet is discriminatory";
        }
        else if(targetedGroups() == true){
            returnStatement = "The tweet is discriminatory";
        }
        else if(hatefulIntent() == true){
            returnStatement = "The tweet is discriminatory";
        }
        else{
            returnStatement = "The tweet is not discriminatory";
        }
        return returnStatement;
    }



}
