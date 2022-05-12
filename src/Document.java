import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    public String text;

    public Document(String text){
        this.text = text;
    }
    public String getText(){
        return text;
    }
    public void setText(Document other){
        this.text = other.text;
    }
    public static int syllablesPerWord(String word){
        //calculates the amount of syllables in a single specific word
        String newWord = word.toLowerCase();
        int counter = 0;
        String vowels = "aeiouy";
        for (int i = 0; i < newWord.length()-1; i++) {
            if(i == 0 && (vowels.contains(newWord.substring(i,i+1)))){
                counter++;
            }
            else if(vowels.contains(newWord.substring(i,i+1))){
                for (int j = 0; j < newWord.indexOf(i)-1; j++) {
                    if(newWord.substring(j, j+1).contains(vowels)){
                        counter++;
                    }
                }
            }
            else if(!(newWord.substring(i, i+1).contains(vowels))){
                if(vowels.contains(newWord.substring(i+1,i+2))){
                    counter++;
                }
            }else if(i == newWord.length()-2 && !(newWord.substring(i, i+1).equals("e"))){
                counter--;
            }
        }
        if(counter == 0){
            counter = 1;
        }
        return counter;
    }
    public ArrayList<String> wordsInSentence(){
        //returns the ArrayList that contains every sentence in each index
        ArrayList<String> output = new ArrayList<String>();

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
    public int wordCount(){
        //counts the number of words in the text
        ArrayList<String> sentences = wordsInSentence();
        ArrayList<String> wordlist = new ArrayList<String>();
        for (int i = 0; i < sentences.size(); i++) {
            String words = sentences.get(i);

            String[] word = words.split(" ");
            for (int j = 0; j < word.length; j++) {
                words = word[i];
                wordlist.add(words);
            }
        }
        return wordlist.size();
    }
    public int totalChar(){
        //returns the length of the text excluding spaces
        int counter = 0;
        ArrayList<String> sentences = wordsInSentence();
        ArrayList<String> wordlist = new ArrayList<String>();
        for (int i = 0; i < sentences.size(); i++) {
            String wordsNew = sentences.get(i);
            String[] word = wordsNew.split(" ");
            for (int j = 0; j < word.length; j++) {
                wordsNew = word[j];
                wordlist.add(wordsNew);
            }
        }
        for (int i = 0; i < wordlist.size(); i++) {
            counter += (wordlist.get(i)).length();
        }
        return counter;
    }
    public ArrayList<String> words(){
        //returns an ArrayList that contains each of the words in every index
        ArrayList<String> sentences = wordsInSentence();
        ArrayList<String> wordlist = new ArrayList<String>();
        for (int i = 0; i < sentences.size(); i++) {
            String wordsNew = sentences.get(i);
            String[] word = wordsNew.split(" ");
            for (int j = 0; j < word.length; j++) {
                wordsNew = word[j];
                wordsNew = wordsNew.replace(".", "");
                wordlist.add(wordsNew);
            }
        }
        return wordlist;
    }
    public double totalSentences(){
        //gets the total amount of sentences in the text
        ArrayList<String> output = new ArrayList<String>();

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

        return output.size();
    }

    public double totalSyllables(){
        //gets the total amount of syllables in the text
        ArrayList<String> wordlist = words();
        double syllables = 0;
        String vowels = "aeiouy";

        for (int i = 0; i < wordlist.size(); i++) {
            String wordNew = wordlist.get(i);
            syllables += syllablesPerWord(wordNew);
        }
        return syllables;
    }
    public double averageWords(){
        //gets the average amount of words per sentence
        int count = 0;
        double average = 0;
        ArrayList<String> paragraph = wordsInSentence();
        for (int i = 0; i < paragraph.size(); i++) {
            String sentence = paragraph.get(i);
            ArrayList<String> words = wordsInSentence();
            count += words.size();
        }
        average = (double) count/paragraph.size();
        return average;
    }
    public double readabilityScore(){
        //calculates the readability score of the text
        ArrayList<String> sentences = wordsInSentence();
        ArrayList <String> words = words();
        double calculation1 = (words.size()/sentences.size());
        double calculation2 = (totalSyllables()/words.size());
        double score = (206.835 - (1.015 * calculation1) - (84.6 * calculation2));
        return score;
    }
    public int countOccurences(String word){
        //counts the number of occurrences of a word in the text
        //need this method
        int count = 0;
        ArrayList<String> sentences = wordsInSentence();
        for (int i = 0; i < sentences.size(); i++) {
            String singleSentence = sentences.get(i);
            if(singleSentence.contains(word)){
                count++;
            }
        }
        return count;
    }
    public double AverageChar(){
        //calculates the average length of words in the text
        double average = 0;
        ArrayList word = words();
        int characters = totalChar();
        average = (double) characters/word.size();
        return average;
    }
    public int noneRepeat(){
        //gets the total amount of words without repeats
        ArrayList<String> words = words();
        int counter = 0;
        for (int i = 0; i < words.size()-1; i++) {
            String word = words.get(i);
            String word2 = words.get(i+1);
            if(!(word).equals(word2)){
                counter++;
            }
        }
        counter++;
        return counter;
    }
    public boolean occurWords(String w1, String w2){
        //checks whether w1 and w2 are in the same sentence
        //need this method
        ArrayList<String> sentences = wordsInSentence();
        for (int i = 0; i < sentences.size(); i++) {
            String word = sentences.get(i);
            if(word.contains(w1) && word.contains(w2)){
                return true;
            }
        }
       return false;
    }
}
