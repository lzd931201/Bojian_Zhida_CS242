
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class hw {

	public static void main(String[] args)  throws IOException {
		String Url = "https://www.amazon.com/Computers-Tablets/b/ref=nav_shopall_basedevices?ie=UTF8&node=13896617011";
		crawler.parseSeed(Url);
//		try(  PrintWriter out = new PrintWriter( "TEST.html" )  ){
//		    out.println( html.body() );
//		}
		//parse an exist file
//		File input = new File("TEST.html");
//		Document doc = Jsoup.parse(input, "UTF-8");		
//		Elements links =doc.getElementsByTag("a");
//		String last=null;
//		for (Element link : links) {
//		  String linkHref = link.attr("href");
//
//		  if(linkHref.contains("www.amazon.com/")&&linkHref.contains("dp")){
//			  if(!linkHref.equals(last)){	
//				  last=linkHref;
//				  System.out.println(linkHref);
//			  }		//reduce duplicate
//		  }
//		}
		
	}

}
