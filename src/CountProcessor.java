import java.util.HashSet;
import java.util.Set;

public class CountProcessor extends Processor implements Runnable {
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
    public void run() {
        output.append(processorName);
        output.append(":\n");
        for (Artist artist : artists) {
            output.append(artist.getName());
            output.append(" ");
            output.append(count(artist));
            output.append("\n");
        }
        output.append("***\n");
    }
}
