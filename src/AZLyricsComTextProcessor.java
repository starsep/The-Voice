import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static java.lang.Thread.sleep;

public class AZLyricsComTextProcessor extends TextProcessor {
    private static final String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/41.0.2272.76 Chrome/41.0.2272.76 Safari/537.36";

    protected Song processSong(String songSource) {
        Document document;
        System.out.println(songSource);
        try {
            document = Jsoup.connect(songSource).userAgent(userAgent).get();
            sleep(2000);
        } catch (Exception e) {
            System.err.println("Nie udało się pobrać strony " + songSource);
            return new Song();
        }
        boolean ringtone = false;
        for (Element e : document.select("div")) {
            if (ringtone)
                return new Song().addWord(e.text());
            if (e.hasClass("ringtone"))
                ringtone = true;
        }
        return new Song();
    }

    @Override
    protected String parseArtistName(String artistName) {
        StringBuilder stringBuilder = new StringBuilder();
        String lower = artistName.toLowerCase();
        String[] splited = lower.split(" ");
        for (String word : splited)
            stringBuilder.append(word);
        return stringBuilder.toString();
    }

    @Override
    protected void processArtist(Artist artist, String artistName) {
        String url = source + "/" + artistName.charAt(0) + "/" + artistName + ".html";
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.err.println("Nie udało się pobrać strony " + url);
            return;
        }
        for (Element e : document.select("a[href^=\"../lyrics\"")) {
            String songUrl = source + e.attr("href").substring(2);
            artist.addSong(processSong(songUrl));
        }
    }
}
