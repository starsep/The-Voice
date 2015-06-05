public abstract class TextProcessor {
    protected String source;

    public TextProcessor setSource(String source) {
        this.source = source;
        return this;
    }

    protected abstract String parseArtistName(String artistName);

    protected abstract void processArtist(Artist artist, String artistName) throws Exception;

    public void giveArtistSongs(Artist artist) throws Exception {
        processArtist(artist, parseArtistName(artist.getName()));
    }
}
