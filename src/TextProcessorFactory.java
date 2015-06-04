public abstract class TextProcessorFactory {
    public static TextProcessor getTextSource(String source) throws Exception {
        switch (source) {
            case "file":
                return new FileTextProcessor();
            case "teksty.org":
                return new TekstyOrgTextProcessor();
            case "azlyrics.com":
                return new AZLyricsComTextProcessor();
            default:
                throw new Exception("Nieznane źródło piosenek");
        }
    }
}
