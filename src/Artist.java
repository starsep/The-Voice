import java.util.ArrayList;
import java.util.Collection;

public class Artist {
    private Collection<Song> songs;

    public Artist() {
        songs = new ArrayList<>();
    }

    public Artist addSong(Song song) {
        songs.add(song);
        return this;
    }
}
