import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawler {
	public static void parseSeed(String Url) throws IOException {
		Document doc = Jsoup.connect(Url).timeout(30000)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
				.get();
		Elements Divs = doc.getElementsByClass("bxc-grid__content");
		System.out.println(doc.body());
		// System.out.println(doc.body());
		Elements links = null;
		String linkLast = null;
		for (Element Div : Divs) {
			links = Div.getElementsByTag("a");
			for (Element link : links) {
				String linkHref = link.attr("href");
				if (!linkHref.equals(linkLast)) {
					linkLast = linkHref;
					getAllProduct(linkHref);
				}
			}
		}
	}

	public static void getAllProduct(String Url)throws IOException {
		Document doc = Jsoup.connect(Url).timeout(30000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
		int count = 0;
		while(count<100){
			getProduct(Url);
			Element next = doc.getElementById("pagnNextLink");
			if(next!=null){
			String next_Url = next.attr("href");
			if (next_Url.startsWith("/"))
			    next_Url = "http://www.amazon.com" + next_Url;
			if (next_Url.startsWith("www"))
			    next_Url = "http://" + next_Url;
			Url = next_Url;
			}
		}
	}

	public static void getProduct(String Url) throws IOException {
		Document docu = Jsoup.connect(Url).timeout(30000)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
				.get();
		// System.out.println(docu.body());
		Elements links = docu.getElementsByTag("a");
		String last = null;
		for (Element link : links) {
			String linkHref = link.attr("href");

			if (linkHref.contains("www.amazon.com/") && linkHref.contains("dp") && !linkHref.contains("#")) {
				if (!linkHref.equals(last)) {
					last = linkHref;
					Document doc = Jsoup.connect(linkHref).timeout(30000)
							.userAgent(
									"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
							.get();
					try (PrintWriter out = new PrintWriter(linkHref + ".html")) {
						out.println(doc.body());
					}
				} // reduce duplicate
			}
		}
	}
}
