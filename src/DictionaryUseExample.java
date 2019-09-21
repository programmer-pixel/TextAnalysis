
public class DictionaryUseExample {

    public static void main(String[] args) {
        Dictionary dict = Dictionary.buildDictionary("data/words.txt");

        System.out.println(dict.isWord("hello"));   // yes a word!
        System.out.println(dict.isWord("Hello".toLowerCase()));   // NOTE: our dictionary file
                                                    // has only lower-case
                                                    // words!
        System.out.println(dict.isWord("jeifv"));   // this is not a word


        String[] allTheWords = dict.getWordList();
        System.out.println("There are " + allTheWords.length + " words");
    }

}
