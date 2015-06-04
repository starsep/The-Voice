public abstract class FilterFactory {
    public static Filter getFilter(String filterName){
        return new FileFilter(filterName);
    }
}
