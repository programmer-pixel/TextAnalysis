public class Word {
    private String word;
    private double syllables;

    public Word(String word, double syllables) {
        this.word = word;
        this.syllables = syllables;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public double getSyllables() {
        return syllables;
    }

    public void setSyllables(int syllables) {
        this.syllables = syllables;
    }
}
