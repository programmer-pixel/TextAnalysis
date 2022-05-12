import java.util.ArrayList;
public class WordBucket {
    //done with WordBucket Object!!!
    private ArrayList<String> words = new ArrayList<String>();
    public WordBucket(){
        words = new ArrayList<String>();
    }

    public void add(String item) {
        words.add(item);
    }

    public void add(String item, long count) {
        for (long i = 0; i < count; i++) {
            add(item);
        }
    }

    public long getCountOf(String countWord) {
        long count = 0;
        for (String word : words) {
            if(word.equals(countWord)){
                count += 1;
            }
        }
        return count;
    }

    public long size() {
        return words.size();
    }

    public long getNumUnique() {
        long counter = 0;
        for (int i = 0; i < words.size() - 1; i++) {
            String word = words.get(i);
            String word2 = words.get(i + 1);
            if (!(word).equals(word2)) {
                counter++;
            }
        }
        counter++;
        return counter;
    }

    public String getMostFreq() {
        if (size() == 0) { return "";}
        String mostFreq = words.get(0);
        long largecounter = getCountOf(mostFreq);
        for (String word : words) {
            long counter = getCountOf(word);
            if(counter > largecounter){
                largecounter = counter;
                mostFreq = word;
            }
        }
        return mostFreq;
    }
}