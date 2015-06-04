import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileFilter extends Filter {
    public FileFilter(String filename) throws Exception {
        super(filename);
        List<String> words = Files.readAllLines(new File(filename).toPath(), StandardCharsets.UTF_8);
        for (String word : words)
            addFilterWord(word);
    }
}
