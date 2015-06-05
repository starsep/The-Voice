public abstract class TextProcessorFactory {
    public static TextProcessor getTextSource(String source, boolean multithreaded) throws Exception {
        switch (source) {
            case "file":
                return multithreaded ? new FileTextMultiProcessor() : new FileTextProcessor();
            case "teksty.org":
                return new TekstyOrgTextProcessor();
            case "azlyrics.com":
                return new AZLyricsComTextProcessor();
            default:
                throw new Exception("Nieznane źródło piosenek");
        }
    }
}
