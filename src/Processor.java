import java.util.ArrayList;
import java.util.Collection;

public abstract class Processor implements Runnable {
    protected String processorName;
    protected Collection<Artist> artists;
    protected StringBuilder output;

    public Processor(String processorName) {
        this.processorName = processorName;
        this.artists = new ArrayList<>();
        this.output = new StringBuilder();
    }

    public abstract void run();

    public void addArtists(Collection<Artist> artists) {
        this.artists.addAll(artists);
    }

    public String output() {
        String result = output.toString();
        output.setLength(0);
        return result;
    }
}
