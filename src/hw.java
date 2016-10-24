
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class hw {

	public static void main(String[] args)  throws IOException {
//		String URLs="https://www.amazon.com/s/ref=s9_acss_bw_cg_lgopc_3c1?node=13896617011&brand=Lenovo&lo=computers&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=unified-hybrid-12&pf_rd_r=ZAP8V9G9C9P58M9G4V44&pf_rd_t=101&pf_rd_p=2598218402&pf_rd_i=13896617011";
////		String URL_com_and_tab = "https://www.amazon.com/Computers-Tablets/b/ref=nav_shopall_basedevices?ie=UTF8&node=13896617011";
//		Connection.Response html = Jsoup.connect(URLs).execute();
//		//Document doc = Jsoup.connect("http://www.amazon.com/").get();
//		 System.out.print(html.body());
//		 try(  PrintWriter out = new PrintWriter( "TEST.html" )  ){
//			    out.println( html.body() );
//			}

		//parse an exist file
		File input = new File("TEST.html");
		Document doc = Jsoup.parse(input, "UTF-8");		
		Elements links =doc.getElementsByTag("a");
		String last=null;
		for (Element link : links) {
			
		  String linkHref = link.attr("href");
		  if(linkHref.contains("www.amazon.com/")){
			  if(!linkHref.equals(last)){	
				  last=linkHref;
				  System.out.println(linkHref);
			  }		//reduce duplicate
		  }
		}
		
	}

}
