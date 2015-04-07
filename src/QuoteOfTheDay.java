import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Provides methods to fetch the quote of the day from the Internet including
 * the quote and author name, scrapes content from the web using the Jsoup
 * library
 * @author	Jack Evans
 */
public class QuoteOfTheDay {
    // Initialise some variables
    public static String url = "http://www.eduro.com/";
    public static Document document;

    /**
     * @return The Quote of the Day
     */
    public String getQuoteOfTheDay() {
        try {
            document = Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.select(".article dailyquote p").get(0).text();
    }

    /**
     * @return The Author of today's quote
     */
    public String getAuthor() {
        try {
            document = Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.select(".article dailyquote p").get(1).text();
    }
}
