
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class hw {

	public static void main(String[] args)  throws IOException {
//		Connection.Response html = Jsoup.connect("https://www.amazon.com/Computers-Tablets/b/ref=nav_shopall_basedevices?ie=UTF8&node=13896617011").execute();
//		//Document doc = Jsoup.connect("http://www.amazon.com/").get();
//		 System.out.print(html.body());
//		 try(  PrintWriter out = new PrintWriter( "amazon_computer&tablets.html" )  ){
//			    out.println( html.body() );
//			}

		// TODO Auto-generated method stub
		File input = new File("amazon_computer&tablets.html");
		Document doc = Jsoup.parse(input, "UTF-8");
		Elements links =doc.getElementsByTag("a");
		for (Element link : links) {
		  String linkHref = link.attr("href");
		  System.out.println(linkHref);
		}
	}

}
