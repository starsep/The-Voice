import java.util.ArrayList;
import java.util.Collection;

public class Song {
    private Collection<String> lyrics;

    public Song() {
        lyrics = new ArrayList<>();
    }

    public Song addWord(String word) {
        addWords(word.split("[\\s,.:;?!()-]+"));
        return this;
    }

    private void addGoodWord(String word){
        lyrics.add(word.toLowerCase());
    }

    private Song addWords(String[] words){
        for(String word : words)
            addGoodWord(word.toLowerCase());
        return this;
    }

    public Collection<String> getLyrics(){
        return lyrics;
    }
}
