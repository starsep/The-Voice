import java.io.File;

public class TheVoice {
    private static Voice voice;

    public static void parseArgs(String[] args) throws Exception {
        TextProcessor textProcessor = null;
        for (String arg : args)
            if (arg.contains("--source-type="))
                textProcessor = TextProcessorFactory.getTextSource(arg.split("--source-type=")[1]);
        if (textProcessor == null)
            throw new Exception("Nie podany typ żródła");
        for (String arg : args) {
            if (arg.contains("--source="))
                textProcessor.setSource(arg.split("--source=")[1]);
            else if (arg.contains("--processors=")) {
                String[] processors = arg.split("--processors=")[1].split(",");
                for (String processor : processors)
                    voice.addProcessor(ProcessorFactory.getProcessor(processor));
            } else if (arg.contains("--filters=")) {
                String[] filters = arg.split("--filters=")[1].split(File.pathSeparator);
                for (String filter : filters)
                    voice.addFilter(FilterFactory.getFilter(filter));
            }
        }
        
        //ZAŁOŻENIE: jeżeli nazwa artysty jest jednowyrazowa to nie jest w cudzysłowie
        StringBuilder artist_name = new StringBuilder();
        boolean artistWithSpaces = false;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (!arg.contains("--")) {
                if (arg.charAt(0) == '“') {
                    artistWithSpaces = true;
                    artist_name.append(arg.substring(1) + " ");
                } else if (arg.charAt(arg.length() - 1) == '”') {
                    artistWithSpaces = false;
                    artist_name.append(arg.substring(0, arg.length() - 1));
                    voice.addArtist(new Artist(artist_name.toString()));
                    artist_name.setLength(0);
                } else if (artistWithSpaces)
                    artist_name.append(arg + " ");
                else //if(!artistWithSpaces)
                    voice.addArtist(new Artist(arg));
            }
        }
        voice.setTextProcessor(textProcessor);
    }

    public static void main(String[] args) throws Exception {
        voice = new Voice();
        parseArgs(args);
    }
}
