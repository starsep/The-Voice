import java.util.Collection;

public abstract class Processor {
    protected String processorName;

    public Processor(String processorName) {
        this.processorName = processorName;
    }

    public abstract void process(Collection<Artist> artists);
}
