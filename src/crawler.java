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
		Document doc = Jsoup.connect(Url).timeout(30000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
	Elements Divs =  doc.getElementsByClass("bxc-grid__content");
	Elements links=null;
	String linkLast = null;
	for(Element Div :Divs){
		links = Div.getElementsByTag("a");
		for(Element link : links){
			String linkHref = link.attr("href");
			if(!linkHref.equals(linkLast)){
				linkLast = linkHref;
				getProduct(linkHref);
			}
		}
	}
	}
	public static void getProduct(String Url) throws IOException {
		Document docu = Jsoup.connect(Url).timeout(30000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
		//System.out.println(docu.body());
		Elements links =docu.getElementsByTag("a");
		String last=null;
		for (Element link : links) {
		  String linkHref = link.attr("href");

		  if(linkHref.contains("www.amazon.com/")&&linkHref.contains("dp")&&!linkHref.contains("#")){
			  if(!linkHref.equals(last)){	
				  last=linkHref;
				  System.out.println(linkHref);
			  }		//reduce duplicate
		  }
		}
	}
}
