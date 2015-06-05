import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileTextMultiProcessor extends TextProcessor {

    private class FileTextSongProcessor implements Runnable {
        private String songSource;
        private Song result;

        public FileTextSongProcessor(String songSource) {
            this.songSource = songSource;
        }

        @Override
        public void run() {
            result = new Song();
            try {
                byte bytes[] = Files.readAllBytes(new File(songSource).toPath());
                String text = new String(bytes, StandardCharsets.UTF_8);
                result.addWord(text);
            } catch (Exception e) {
                System.err.println("Nie udało się załadować pliku" + songSource);
            }
        }

        public Song getResult() {
            return result;
        }
    }

    @Override
    protected String parseArtistName(String artistName) {
        return artistName;
    }

    @Override
    protected void processArtist(Artist artist, String artistName) throws Exception {
        File artistDirectory = new File(source + "/" + artistName);
        File[] list = artistDirectory.listFiles();
        if (list == null)
            return;
        List<Thread> threads = new ArrayList<>();
        List<FileTextSongProcessor> fileTextSongProcessors = new ArrayList<>();
        for (File file : list)
            if (file.getName().endsWith(".txt") && !file.isDirectory()) {
                FileTextSongProcessor songProcessor = new FileTextSongProcessor(source + "/" + artistName + "/" + file.getName());
                fileTextSongProcessors.add(songProcessor);
                threads.add(new Thread(songProcessor));
                threads.get(threads.size() - 1).start();
            }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
            artist.addSong(fileTextSongProcessors.get(i).getResult());
        }
    }
}