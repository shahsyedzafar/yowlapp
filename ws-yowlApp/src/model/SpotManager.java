package model;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class SpotManager {

	public SpotManager(){}
	private static final SpotManager spotManager = new SpotManager();
	
	public static SpotManager getInstance(){
		return spotManager;
	}
	
	static public String defaultNameSpace = "http://www.semanticweb.org/group5/ontologies/semanticProject/";
	
	public Model yowlObj = null;
	public Model schema = null;
	public InfModel inferredObj = null;
	
	public void populateSpot()
	{
		yowlObj = ModelFactory.createOntologyModel();
		InputStream inFoafInstance = 
             FileManager.get().open("model/Ontologies/Spot.rdf");
		yowlObj.read(inFoafInstance,defaultNameSpace);
		try {
			inFoafInstance.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<TouristSpot> GetSpotByLocation(String location){
		populateSpot();
		List<TouristSpot> result = new ArrayList<TouristSpot>();
		String queryRequest = " select DISTINCT ?name ?img ?location ?comment where { ?spot sp:has_name ?name;"
				 
                + "sp:has_review ?comment."
                + "OPTIONAL{?spot sp:located_in ?location}"
                + "OPTIONAL{?spot sp:has_picture ?img}"
                + "FILTER regex(?location, '"+location +"')}";
		
		StringBuffer queryStr = new StringBuffer();
		
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
				RDFNode spotName = soln.get("?name");
				RDFNode img_path = soln.get("?img_path");
				RDFNode address = soln.get("?location");
				RDFNode comment = soln.get("?comment");
				if( spotName != null ){
					//System.out.println( "Hello to " + name.toString() );
					TouristSpot temp = new TouristSpot();
					temp.setName(spotName.toString());
					if(img_path != null)
						temp.setImg_path(img_path.toString());
					if(address != null)
						temp.setLocation(address.toString());
					if(comment != null)
						temp.setComment(comment.toString());
					result.add(temp);
				}
				else
					System.out.println("No Friends found!");
				
			} 				
		  }
		  finally { qexec.close();}
		  for (int i =0; i < result.size(); i++){
			  System.out.println(result.get(i).getName());
			  System.out.println(result.get(i).getLocation());
			  System.out.println(result.get(i).getComment());
		  }
		  return result;
		}
	public TouristSpot GetSpotByName(String Name){
		populateSpot();
		TouristSpot result = new TouristSpot();
		String queryRequest = " select DISTINCT ?name ?img ?location ?comment where { ?spot sp:has_name ?name;"
				 
                + "sp:has_review ?comment."
                + "OPTIONAL{?spot sp:located_in ?location}"
                + "OPTIONAL{?spot sp:has_picture ?img}"
                + "FILTER regex(?name, '"+Name +"')}";
        //System.out.println(queryRequest);
		
		StringBuffer queryStr = new StringBuffer();
		
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
				RDFNode spotName = soln.get("?name");
				RDFNode img_path = soln.get("?img_path");
				RDFNode address = soln.get("?location");
				RDFNode comment = soln.get("?comment");
				if( spotName != null ){
					//System.out.println( "Hello to " + name.toString() );
					result.setName(spotName.toString());
					if(img_path != null)
						result.setImg_path(img_path.toString());
					if(address != null)
						result.setLocation(address.toString());
					if(comment != null)
						result.setComment(comment.toString());
				}
				else
					System.out.println("No Friends found!");
				
			} 				
		  }
		  finally { qexec.close();}
		  System.out.println(result.getName());
		  System.out.println(result.getLocation());
		  System.out.println(result.getComment());
		  return result;
		}

	
	  public static void main(String[] args){
    SpotManager.getInstance().GetSpotByLocation("California");
    //SpotManager.getInstance().GetSpotByName("Jesus Maria, California");
    
}
}
