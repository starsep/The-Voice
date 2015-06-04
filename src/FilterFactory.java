public abstract class FilterFactory {
    public static Filter getFilter(String filterName) throws Exception {
        return new FileFilter(filterName);
    }
}
