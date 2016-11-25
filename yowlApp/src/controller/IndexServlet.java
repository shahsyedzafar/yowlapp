package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Box;

import model.ExtractTweets;
import model.GenerateCSVFile;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public IndexServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getParameter("university") != null) {
			
			String universityName = request.getParameter("university");
			
			GenerateCSVFile csf = new GenerateCSVFile("university_tweets.csv");
	
			System.out.println("in University with name: "+universityName);
			ExtractTweets et = new ExtractTweets();
			et.getData(universityName, csf);
				
	
		} else if(request.getParameter("touristSpot") != null) {
			
			System.out.println("In tourist Spot");
	
			String touristSpot = request.getParameter("touristSpot");
			
			GenerateCSVFile csf = new GenerateCSVFile("touristSpots_tweets.csv");
	
			System.out.println("in City with name: "+touristSpot);
			ExtractTweets et = new ExtractTweets();
			et.getData(touristSpot, csf);
		}
		
		
		
		
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
