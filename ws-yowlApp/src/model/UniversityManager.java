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

public class UniversityManager {
	public UniversityManager(){}
	private static final UniversityManager universityManager = new UniversityManager();
	
	public static UniversityManager getInstance(){
		return universityManager;
	}
	
	static public String defaultNameSpace = "http://www.semanticweb.org/group5/ontologies/semanticProject/";
	
	public Model yowlObj = null;
	public Model schema = null;
	public InfModel inferredObj = null;
	
	public void populateUniversity()
	{
		yowlObj = ModelFactory.createOntologyModel();		
		InputStream inFoafInstance = 
             FileManager.get().open("model/Ontologies/University.rdf");
		yowlObj.read(inFoafInstance,defaultNameSpace);
		try {
			inFoafInstance.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<University> GetUniversityByCity(String City){
		populateUniversity();
		List<University> result = new ArrayList<University>();
		String queryRequest = " select DISTINCT ?name ?homepage ?chairPerson ?state ?mapURL where { ?university sp:has_name ?name; "
				+ "sp:has_homePage ?homepage;"
				+ "sp:in_state ?state;"
				+ "sp:has_chairPerson ?chairPerson;"
				 + "sp:has_mapURL ?mapURL;"
				+ "sp:located_in ?city. ?city sp:has_name '"+ City +"'.}";
		
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
				RDFNode name = soln.get("?name");
				RDFNode homepage = soln.get("?homepage");
				RDFNode chairPerson = soln.get("?chairPerson");
				RDFNode state = soln.get("?state");
				RDFNode mapURL = soln.get("?mapURL");
				if( name != null ){
					//System.out.println( "Hello to " + name.toString() );
					University temp = new University();
					temp.setName(name.toString());
					if(homepage != null)
						temp.setHomePage(homepage.toString());
					if(chairPerson != null)
						temp.setChairPerson(chairPerson.toString());
					if(state != null)
						temp.setState(state.toString());
					temp.setLocation(City);
					temp.setMapURL(mapURL.toString());
					result.add(temp);
				}
				else
					System.out.println("Not found!");
				
			} 				
		  }
		  finally { qexec.close();}
		  for (int i =0; i < result.size(); i++){
			  System.out.println(result.get(i).getName());
			  System.out.println(result.get(i).getHomePage());
			  System.out.println(result.get(i).getChairPerson());
		  }
		  return result;
		}
	public University GetUniversityByName(String name){
		populateUniversity();
		University result = new University();
		String queryRequest = " select DISTINCT ?name ?homepage ?chairPerson ?cityName ?state ?mapURL where { ?university sp:has_name ?name;"
				+ "sp:has_homePage ?homepage;"
				+ "sp:in_state ?state;"
				+ "sp:has_chairPerson ?chairPerson;"
				+ "sp:has_mapURL ?mapURL;"
				+ "sp:located_in ?city. ?city sp:has_name ?cityName."
				+ "FILTER regex(?name, '"+name +"')}";
		
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
				RDFNode UniversityName = soln.get("?name");
				RDFNode city = soln.get("?cityName");
				RDFNode homepage = soln.get("?homepage");
				RDFNode chairPerson = soln.get("?chairPerson");
				RDFNode state = soln.get("?state");
				RDFNode mapURL = soln.get("?mapURL");
				if( UniversityName != null ){
					//System.out.println( "Hello to " + name.toString() );
					result.setName(UniversityName.toString());
					if(city != null)
						result.setLocation(city.toString());
					if(homepage != null)
						result.setHomePage(homepage.toString());
					if(chairPerson != null)
						result.setChairPerson(chairPerson.toString());
					if(state != null)
						result.setState(state.toString());
					result.setMapURL(mapURL.toString());
				}
				else
					System.out.println("No Friends found!");
				
			} 				
		  }
		  finally { qexec.close();}
		  System.out.println(result.getName());
		  System.out.println(result.getHomePage());
		  System.out.println("State");
		  System.out.println(result.getState());
		  System.out.println("City");
		  System.out.println(result.getLocation());
		  System.out.println(result.getChairPerson());
		  System.out.println(result.getMapURL());
		  return result;
		}
	
	 
   

}
