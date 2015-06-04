import java.util.ArrayList;
import java.util.Collection;

public class Voice {
    private TextProcessor textProcessor;

    private Collection<Processor> processors;

    private Collection<Filter> filters;

    private Collection<Artist> artists;

    public Voice() {
        processors = new ArrayList<>();
        filters = new ArrayList<>();
        artists = new ArrayList<>();
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
        for(Filter filter : filters)
            for(Artist artist : artists)
                for(Song song : artist.getSongs())
                    filter.filter(song.getLyrics());
        for(Processor processor : processors)
            processor.process(artists);
        return this;
    }
}
