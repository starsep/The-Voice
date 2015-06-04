import java.util.ArrayList;
import java.util.Collection;

public abstract class Filter {
    protected Collection<Filter> filterWords;

    public Filter() {
        filterWords = new ArrayList<>();
    }
}
