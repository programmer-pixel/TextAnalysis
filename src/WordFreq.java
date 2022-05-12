import java.util.ArrayList;
public class WordFreq {
    private static String word;
    private static int count;

    public WordFreq(String word, int count) {
        this.word = word;
    }

    public static void setWord(String word) {
        WordFreq.word = word;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        WordFreq.count = count;
    }

    public static String getWord() {
        return word;
    }

}
