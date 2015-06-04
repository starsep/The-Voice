import java.util.ArrayList;
import java.util.Collection;

public class Artist {
    private Collection<Song> songs;

    private String name;

    public Artist(String name) {
        songs = new ArrayList<>();
        this.name = name;
    }

    public Artist addSong(Song song) {
        songs.add(song);
        return this;
    }

    public Collection<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }
}
