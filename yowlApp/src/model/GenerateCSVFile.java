package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GenerateCSVFile {
	
	File f;
	OutputStream os = null;
	

	public GenerateCSVFile(String fileName) {	
		
		String filePath = "D:"+File.separator+fileName;
		String heading = null;
		System.out.println(filePath);
		//this.f = new File(fileName);
		this.f = new File(filePath);	
		if(!f.exists()) {
			try {
				f.createNewFile();
				os = new FileOutputStream(f,true);
				if(fileName.equals("university_tweets.csv")) {
					heading = "Timestamp,Hashtag,TweetText,Media,locatedIn\n";
					os.write(heading.getBytes());
				} else if(fileName.equals("touristSpots_tweets.csv")) {
					heading = "Timestamp,Hashtag,TweetText,Media,locatedIn\n";
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
			System.out.println(f);
			System.out.println(os);
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
