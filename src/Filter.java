import java.util.ArrayList;
import java.util.Collection;

public abstract class Filter {
    protected Collection<String> filterWords;

    public Filter(String name) throws Exception {
        filterWords = new ArrayList<>();
    }

    public Filter filter(Collection<String> words) {
        words.removeAll(filterWords);
        return this;
    }

    protected void addFilterWord(String word) {
        for (String filterWord : word.toLowerCase().split("[\\s,.:;?!()-]+"))
            filterWords.add(filterWord);
    }
}
