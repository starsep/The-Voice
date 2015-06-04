import java.util.ArrayList;
import java.util.Collection;

public class Song {
    private Collection<String> lyrics;

    public Song() {
        lyrics = new ArrayList<>();
    }

    public Song addWord(String word) {
        lyrics.add(word);
        return this;
    }
}
