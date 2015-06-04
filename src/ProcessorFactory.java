public abstract class ProcessorFactory {
    public static Processor getProcessor(String processorName) throws Exception {
        switch (processorName) {
            case "count":
                return new CountProcessor();
            case "top5":
                return new Top5Processor();
            default:
                throw new Exception("Nieznany kompontent przetwarzający");
        }
    }
}
