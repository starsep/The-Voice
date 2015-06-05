public abstract class TextProcessorFactory {
    public static TextProcessor getTextSource(String source, boolean multithread) throws Exception {
        switch (source) {
            case "file":
                return multithread ? new FileTextMultiProcessor() : new FileTextProcessor();
            case "teksty.org":
                return new TekstyOrgTextProcessor();
            case "azlyrics.com":
                return new AZLyricsComTextProcessor();
            default:
                throw new Exception("Nieznane źródło piosenek");
        }
    }
}
