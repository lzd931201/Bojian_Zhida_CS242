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
	Document doc = Jsoup.connect(Url).get();
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
		Document doc = Jsoup.connect(Url).get();
		Elements links =doc.getElementsByTag("a");
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
