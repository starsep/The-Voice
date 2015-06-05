import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Voice {
    private TextProcessor textProcessor;

    private List<Processor> processors;

    private Collection<Filter> filters;

    private Collection<Artist> artists;

    private List<Thread> processorThreads;

    public Voice() {
        processors = new ArrayList<>();
        filters = new ArrayList<>();
        artists = new ArrayList<>();
        processorThreads = new ArrayList<>();
    }

    public Voice setTextProcessor(TextProcessor textProcessor) {
        this.textProcessor = textProcessor;
        return this;
    }

    public Voice addProcessor(Processor processor) {
        processors.add(processor);
        return this;
    }

    public Voice addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public Voice addArtist(Artist artist) {
        artists.add(artist);
        return this;
    }

    public Voice process() throws Exception {
        for (Artist artist : artists)
            textProcessor.giveArtistSongs(artist);
        for (Filter filter : filters)
            for (Artist artist : artists)
                for (Song song : artist.getSongs())
                    filter.filter(song.getLyrics());
        for (Processor processor : processors) {
            processor.addArtists(artists);
            Thread processorThread = new Thread(processor);
            processorThreads.add(processorThread);
            processorThread.start();
        }
        for (int i = 0; i < processors.size(); i++) {
            processorThreads.get(i).join();
            System.out.print(processors.get(i).output());
        }
        return this;
    }
}
