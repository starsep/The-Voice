import java.io.File;
        import java.nio.charset.StandardCharsets;
        import java.nio.file.Files;

public class FileTextProcessor extends TextProcessor {
    protected Song processSong(String songSource) throws Exception {
        byte bytes[] = Files.readAllBytes(new File(songSource).toPath());
        String text = new String(bytes, StandardCharsets.UTF_8);
        return new Song().addWord(text);
    }

    @Override
    protected String parseArtistName(String artistName) {
        return artistName;
    }

    @Override
    protected void processArtist(Artist artist, String artistName) throws Exception {
        File artistDirectory = new File(source + "/" + artistName);
        File[] list = artistDirectory.listFiles();
        for (File file : list)
            if (file.getName().endsWith(".txt") && !file.isDirectory())
                artist.addSong(processSong(source + "/" + artistName + "/" + file.getName()));
    }
}
