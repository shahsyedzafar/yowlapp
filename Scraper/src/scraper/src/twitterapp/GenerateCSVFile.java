package scraper.src.twitterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GenerateCSVFile {
	
	File f;
	OutputStream os = null;
	

	public GenerateCSVFile(String fileName) {	
		
		//String filePath = "D:"+File.separator+fileName;
		String heading = null;
		//this.f = new File(fileName);
		this.f = new File(fileName);	
		if(!f.exists()) {
			try {
				f.createNewFile();
				os = new FileOutputStream(f,true);
				if(fileName.equals("university_tweets.csv")) {
					heading = "Timestamp,UniversityName,Hashtag,TweetText,Media,locatedIn\n";
					os.write(heading.getBytes());
				} else if(fileName.equals("touristSpots_tweets.csv")) {
					heading = "Timestamp,TouristSpotName,Hashtag,TweetText,Media,locatedIn\n";
					os.write(heading.getBytes());
				}
				//write for other csv files
				
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(os!=null) {
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	
	public void writeCSVFile(StringBuffer data) {
			
		
		try {
			os = new FileOutputStream(f, true);
			byte[] contentInBytes = data.toString().getBytes();
			os.write(contentInBytes);
	
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	
	

}
