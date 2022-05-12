import java.util.ArrayList;
public class WordBucket2 {
    private ArrayList<WordFreq> data;
    public WordBucket2(){
        data = new ArrayList<WordFreq>();
    }

    public ArrayList<WordFreq> getData() {
        return data;
    }

    public void setData(ArrayList<WordFreq> data) {
        this.data = data;
    }

    public void add(WordFreq item) {
        int freqOfItem = item.getCount() + 1;
        item.setCount(freqOfItem);

        for (int i = data.size(); i > 0; i--) {
            int freq = (data.get(i)).getCount();
            if (freq > freqOfItem) {
                data.add(i, item);
            }
        }
    }

    public void add(WordFreq item, long count) {
        for (int i = 0; i < count; i++) {
            data.add(item);
        }
    }

    public long getCountOf(WordFreq word) {
        int count = word.getCount();
        return count;
    }

    public long size() {
        long count = 0;
        for (int i = 0; i < data.size(); i++) {
            count += data.get(i).getCount();
        }
        return count;
    }
}
