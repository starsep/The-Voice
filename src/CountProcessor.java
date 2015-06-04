import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CountProcessor extends Processor {
    CountProcessor(String processorName) {
        super(processorName);
    }

    private int count(Artist artist) {
        Set<String> words = new HashSet<>();
        for (Song song : artist.getSongs())
            words.addAll(song.getLyrics());
        return words.size();
    }

    @Override
    public void process(Collection<Artist> artists) {
        System.out.println(processorName + ":");
        for (Artist artist : artists)
            System.out.println(artist.getName() + " " + count(artist));
        System.out.println("***");
    }
}
