/**
 * Wprowadziłem dodatkową opcję (--multithreaded), który włącza wielowątkowe wczytywanie plików z piosenkami.
 * Jest ona opcjonalna, bo na moim procesorze 4 rdzeniowym (8 wątków) i dysku ssd zyski są znikome.
 * Być może na niektórych dyskach/procesorach będą nawet straty.
 */

import java.io.File;

public class TheVoice {
    private static Voice voice;

    public static void parseArgs(String[] args) throws Exception {
        TextProcessor textProcessor = null;
        boolean multithreaded = false;
        for (String arg : args)
            if (arg.contains("--multithreaded"))
                multithreaded = true;
        for (String arg : args)
            if (arg.contains("--source-type="))
                textProcessor = TextProcessorFactory.getTextSource(arg.split("--source-type=")[1], multithreaded);
        if (textProcessor == null)
            throw new Exception("Nie podany typ żródła");
        StringBuilder artist_name = new StringBuilder();
        boolean artistWithSpaces = false;

        for (String arg : args) {
            if (arg.contains("--source=")) {
                textProcessor.setSource(arg.split("--source=")[1]);
            } else if (arg.contains("--processors=")) {
                String[] processors = arg.split("--processors=")[1].split(",");
                for (String processor : processors)
                    voice.addProcessor(ProcessorFactory.getProcessor(processor));
            } else if (arg.contains("--filters=")) {
                String[] filters = arg.split("--filters=")[1].split(File.pathSeparator);
                for (String filter : filters)
                    voice.addFilter(FilterFactory.getFilter(filter));
            } else if (!arg.contains("--")) {
                if (arg.charAt(0) == '“') {
                    if (arg.charAt(arg.length() - 1) == '”') {
                        voice.addArtist(new Artist(arg.substring(1, arg.length() - 1)));
                    } else {
                        artistWithSpaces = true;
                        artist_name.append(arg.substring(1));
                        artist_name.append(" ");
                    }
                } else if (arg.charAt(arg.length() - 1) == '”') {
                    artistWithSpaces = false;
                    artist_name.append(arg.substring(0, arg.length() - 1));
                    voice.addArtist(new Artist(artist_name.toString()));
                    artist_name.setLength(0);
                } else if (artistWithSpaces) {
                    artist_name.append(arg);
                    artist_name.append(" ");
                } else if(arg.contains("\""))
                    voice.addArtist(new Artist(arg.split("\"")[1]));
                else
                    voice.addArtist(new Artist(arg));
            }
        }
        voice.setTextProcessor(textProcessor);
    }

    public static void main(String[] args) throws Exception {
        voice = new Voice();
        parseArgs(args);
        voice.process();
    }
}
