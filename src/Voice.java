import java.util.ArrayList;
import java.util.Collection;

public class Voice {
    private TextProcessor textProcessor;

    private Collection<Processor> processors;

    private Collection<Filter> filters;

    public Voice() {
        processors = new ArrayList<>();
        filters = new ArrayList<>();
    }

    public Voice setTextProcessor(TextProcessor textProcessor) {
        this.textProcessor = textProcessor;
        return this;
    }

    public Voice addProcessor(Processor processor) {
        processors.add(processor);
        return this;
    }

    public Voice addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }
}
