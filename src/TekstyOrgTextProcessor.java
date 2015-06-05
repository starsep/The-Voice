import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class TekstyOrgTextProcessor extends TextProcessor {
    @Override
    protected Song processSong(String songSource) {
        System.err.println(songSource);
        Document document;
        try {
            document = Jsoup.connect(songSource).get();
        } catch (Exception e) {
            System.err.println("Nie udało się pobrać strony" + songSource);
            return new Song();
        }
        return new Song().addWord(document.select("div.originalText").text());
    }

    @Override
    protected String parseArtistName(String artistName) {
        return artistName.toLowerCase().replace(' ', '-');
    }

    private void processPage(Artist artist, String url) {
        //System.err.println(url);
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.err.println("Nie udało się pobrać strony" + url);
            return;
        }
        if (document.select("a.next-site").size() > 0) {
            String nextUrl = source + document.select("a.next-site").get(0).attr("href");
            processPage(artist, nextUrl);
        }
        for (Element element : document.select("a.artist")) {
            String songUrl = element.attr("href");
            if (songUrl.endsWith(",tekst-piosenki"))
                artist.addSong(processSong(songUrl));
        }
    }

    @Override
    protected void processArtist(Artist artist, String artistName) {
        String url = source + "/" + artistName + ",teksty-piosenek";
        processPage(artist, url);
    }
}
