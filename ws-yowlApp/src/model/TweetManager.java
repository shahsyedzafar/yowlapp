package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileManager;

public class TweetManager {
	public TweetManager(){}
	private static final TweetManager tweetManager = new TweetManager();
	
	public static TweetManager getInstance(){
		return tweetManager;
	}
	
	static public String defaultNameSpace = "http://www.semanticweb.org/group5/ontologies/semanticProject/";
	
	public Model yowlObj = null;
	public Model schema = null;
	public InfModel inferredObj = null;
	
	public void populateTweets()
	{
		yowlObj = ModelFactory.createOntologyModel();
		InputStream inFoafInstance = 
             FileManager.get().open("model/Ontologies/Twitter.rdf");
		yowlObj.read(inFoafInstance,defaultNameSpace);
		try {
			inFoafInstance.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> extractUrls(String text)
	{
	    List<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
	    Matcher urlMatcher = pattern.matcher(text);

	    while (urlMatcher.find())
	    {
	        containedUrls.add(text.substring(urlMatcher.start(0),
	                urlMatcher.end(0)));
	    }

	    return containedUrls;
	}
	
	
	
	public List<Tweet> GetTweetsByTouristSpot(String spot){
		spot = spot.replace(",", " ");
		//spot = spot.split("\\(")[0];
		spot = spot.replace("'", "\\'");
		spot = spot.replace("\\(", "");
		spot = spot.replace("\\)", "");
		populateTweets();
		List<Tweet> result = new ArrayList<Tweet>();
		String queryRequest = " select DISTINCT ?data ?media ?hashTag where { ?tweet sp:has_tweetData ?data; "
				+ "sp:has_media ?media;"
				+ "sp:has_hashTag ?hashTag;"
				+"sp:has_touristSpot '" + spot + "'.}";
				//+ "sp:has_touristSpot ?spot."
				//+ "FILTER regex(?spot, '"+spot+"')}";
		
		StringBuffer queryStr = new StringBuffer();
		System.out.println(queryRequest);
		  // Establish Prefixes
		  //Set default Name space first
		  queryStr.append("PREFIX sp" + ": <" + defaultNameSpace + "> ");
		  queryStr.append("PREFIX rdfs" + ": <" +  
		                  "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		  queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
		  queryStr.append("PREFIX foaf" + ": <" + "http://xmlns.com/foaf/0.1/" 
		                   + "> ");
				
		  //Now add query
		  queryStr.append(queryRequest);
		  Query query = QueryFactory.create(queryStr.toString());
		  QueryExecution qexec = QueryExecutionFactory.create(query, yowlObj);
				
		  try 
		  {
			ResultSet response = qexec.execSelect();
			while( response.hasNext())
			{
				QuerySolution soln = response.nextSolution();
				RDFNode data = soln.get("?data");
				RDFNode media = soln.get("?media");
				RDFNode hashTag = soln.get("?hashTag");
				if( data != null && data.toString() != "absent"){
					//System.out.println( "Hello to " + name.toString() );
					Tweet temp = new Tweet();
					temp.setTouristSpot(spot);
					temp.setData(data.toString());
					if(media != null)
						temp.setMedia(media.toString());
					if(hashTag != null)
						temp.setHashTag(hashTag.toString());
					result.add(temp);
				}
				else
					System.out.println("No Friends found!");
				
			} 				
		  }
		  finally { qexec.close();}
		  for (int i =0; i < result.size(); i++){
			  System.out.println(result.get(i).getTouristSpot());
			  System.out.println(result.get(i).getHashTag());
			  System.out.println(result.get(i).getData());
			  // prepare media
			  
			  List<String> extactMedia = extractUrls(result.get(i).getData());
			  StringBuffer mediaUrls = new StringBuffer();
			  Iterator<String> it = extactMedia.iterator();
			  while(it.hasNext()) {
				  mediaUrls.append(it.next());
			  }
			  System.out.println("Printing mediaurls");
			  System.out.println(mediaUrls.toString());
			  
			  
			  if(result.get(i).getMedia().equals("Absent")) {
				  
				  if(mediaUrls.toString().length()==0) {
					  result.get(i).setMedia("Absent");
					  
				  } else {
					  result.get(i).setMedia(mediaUrls.toString());
				  }
			  } 
			  
			  
			  
		  }
		  return result;
		}
	


}
