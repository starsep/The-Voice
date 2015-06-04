import java.util.*;

public class Top5Processor extends Processor {
    Top5Processor(String processorName) {
        super(processorName);
    }

    private void top5(Artist artist) {
        Map<String, Integer> map = new HashMap<>();
        for (Song song : artist.getSongs())
            for (String word : song.getLyrics()) {
                int number = map.get(word) == null ? 0 : map.get(word);
                map.put(word, number + 1);
            }
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>();
        entries.addAll(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a1, Map.Entry<String, Integer> a2) {
                return a1.getValue().compareTo(a2.getValue());
            }
        });
        Collections.reverse(entries);
        while (entries.size() > 5)
            entries.remove(entries.remove(entries.size() - 1));
        System.out.print("[");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.print(entry.getKey() + "=" + entry.getValue());
            if (!entry.equals(entries.get(entries.size() - 1)))
                System.out.print(", ");
        }
        System.out.println("]");
    }

    @Override
    public void process(Collection<Artist> artists) {
        System.out.println(processorName + ":");
        for (Artist artist : artists) {
            System.out.println(artist.getName());
            top5(artist);
        }
        System.out.println("***");
    }
}
