public abstract class TextProcessor {
    protected String source;

    public TextProcessor setSource(String source) {
        this.source = source;
        return this;
    }
}
