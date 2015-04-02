import java.io.IOException;

// Using Jsoup library to scrape the web-pages for HTML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class QuoteOfTheDay {
	
	// Initialise some public variables
	public static String quoteOfTheDay;
	public static String author;
	
	public static void main (String args[]) throws IOException {
        String url = "http://www.eduro.com/";
        // Had to use a  userAgent otherwise jsoup would return errors
        Document document = Jsoup.connect(url).userAgent("Mozilla").get();
        // Parse the quote of the day along with the author
        quoteOfTheDay = document.select(".article dailyquote p").get(0).text();
        author = document.select(".article dailyquote p").get(1).text();    
	}
	
	public String getQuoteOfTheDay() {
		return quoteOfTheDay;
	}
	
	public String getAuthor() {
		return author;
	}
}


