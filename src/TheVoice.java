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
        voice.setTextProcessor(textProcessor);
    }

    public static void main(String[] args) throws Exception {
        voice = new Voice();
        parseArgs(args);
    }
}
