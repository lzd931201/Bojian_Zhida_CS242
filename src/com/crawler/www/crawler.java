package com.crawler.www;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;;

public class crawler {
	
	static Queue<String> URLS = new LinkedList<String>();	//contain the URLs for crawl
	
	
	
	public static void Crawl(String fileName,Integer fileCount)throws IOException{
		BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		try {
		    String URL;
		    while ((URL = buff.readLine()) != null) {
		       AddQueue(URL);
		    }	//add seeds into the queue
		} finally {
		    buff.close();
		}
		ParsePage(fileCount);	//start parse
	}	// crawl using the seeds in a file and get a certain amount of file
	
	private static void AddQueue(String URL){
		URLS.add(URL);
	}	//add a single URL in to the Queue
	
	private static String GetNextPage(){
		return URLS.poll();
	}	//get the head of the Queue
	
	private static Integer StorageCount =0;	// indicate how many files have been stored
	
	 private static void ParsePage(Integer fileCount)throws IOException{
		while(!URLS.isEmpty()&&StorageCount<fileCount){
			String URL = GetNextPage();
			Parse(URL,parseType.NEXTLINK);
			Parse(URL,parseType.PRODUCT);
		}
	}	//put the nextpage of the current page in to the queue and then save products
	
	public enum parseType{
		PRODUCT("s-item-container"),
		NEXTLINK("pagnNext");
		private String classID;
		private parseType(String id){
			this.classID=id;
		}
		public String getclassID(){
			return this.classID;
		}
	}
	
	private static String Domain="http://www.amazon.com";
	
	private static void Parse(String URL, parseType t)throws IOException{
		Document doc = Jsoup.connect(URL).timeout(30000)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64)")
				.get();
		Elements buttons = doc.getElementsByClass(t.getclassID());
		String lastLink = null;
		switch(t){
		case PRODUCT:
			for(Element button:buttons){
				Elements links = button.getElementsByTag("a");
				SaveProduct(links.get(0).attr("href"));
				}
			break;
		case NEXTLINK:
			for(Element button :buttons){
				String link = button.attr("href");
				if(link==null){
					
				}
				else if(link.startsWith("www")){
					link = "http://"+link;
					AddQueue(link);
					
				}
				else if(link.startsWith("/")){
					link = Domain+link;
					AddQueue(link);
				}
			}
			break;
			default:
		}
	}
	
	
	
	private static void SaveProduct(String URL)throws IOException{
		Document doc = Jsoup.connect(URL).timeout(30000)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64)")
				.get();
		try (PrintWriter out = new PrintWriter(StorageCount.toString()+".html")) {
			out.println(doc.body());
		}
		StorageCount++;
	}
}
