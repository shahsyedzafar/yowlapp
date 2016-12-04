package scraper.src.twitterapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class PopulatTweets {
	
	public void readFile(String path) {
		
		
		
		ExtractTweets et = new ExtractTweets();
		
		// URL url = PopulatTweets.class.getClassLoader().getResource("../Files/spot_names.txt");
		 File f = new File("src/Files/spot_names.txt");
		 
		
		try {
			InputStream fis = null;
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			while((line=br.readLine()) != null) {
				//et.getData(line, csf);
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args)  {

		
		
		
ExtractTweets et = new ExtractTweets();
		
		// URL url = PopulatTweets.class.getClassLoader().getResource("../Files/spot_names.txt");
		 File f = new File("src/Files/spot_names.txt");
		 GenerateCSVFile gcf = new GenerateCSVFile("src/Files/touristSpots_tweets.csv");
		 

		try {
			InputStream fis = null;
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			while((line=br.readLine()) != null) {
				et.getData(line, gcf);
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	

		
	}
}
