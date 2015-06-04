public abstract class ProcessorFactory {
    public static Processor getProcessor(String processorName) throws Exception {
        switch (processorName) {
            case "count":
                return new CountProcessor(processorName);
            case "top5":
                return new Top5Processor(processorName);
            default:
                throw new Exception("Nieznany kompontent przetwarzający");
        }
    }
}
