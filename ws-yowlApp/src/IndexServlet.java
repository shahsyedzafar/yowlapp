


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SpotManager;
import model.TouristSpot;
import model.Tweet;
import model.TweetManager;
import model.University;
import model.UniversityManager;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("Hello World");
		RequestDispatcher rd = null;
		TweetManager tm = null;
		SpotManager sm = null;
		
		if(request.getParameter("univName") != null) {
			String univName = request.getParameter("univName");
			UniversityManager univManager = new UniversityManager();
			University univ = univManager.GetUniversityByName(univName);
			request.setAttribute("pojo", univ);
			
			
			//get tourist spots present in the university's state
			sm = new SpotManager();
			List<TouristSpot> touristSpots = sm.GetSpotByLocation(univ.getState());
			List<String> ts_names = null;
			ts_names = new ArrayList<String>();
			
			
			if(touristSpots.size()>0) {
				
				Iterator<TouristSpot> it = touristSpots.iterator();
				while(it.hasNext()) {
					TouristSpot each_Spot = it.next();
					System.out.println("Printing it.next");
					System.out.println(each_Spot);
					
					if(null != each_Spot && !(ts_names.contains(each_Spot.getName()))) {
						ts_names.add(each_Spot.getName());
					}
					
				}
				request.setAttribute("touristSpots", ts_names);
				
				
			} else {
				request.setAttribute("touristSpots", null);
			}
			
			
			String googleUrl;
			googleUrl = univName.replaceAll("\\s", "+");
			System.out.println("Google Url"+googleUrl);
			request.setAttribute("GoogleUrl", googleUrl);
			
			
			rd = request.getRequestDispatcher("WEB-INF/displayUniversity.jsp");
			rd.forward(request, response);
			
		}
		
		
		
		
		///////////Tourist Spot -------------
		if(request.getParameter("touristSpotName") != null) {
	
			String spotName = request.getParameter("touristSpotName");

			sm = new SpotManager();
			TouristSpot spot = sm.GetSpotByName(spotName);
			System.out.println("Printing Name---------");
			System.out.println(spot.getName());
			request.setAttribute("spot", spot);
		
			tm = new TweetManager();
			List<Tweet> tweets = tm.GetTweetsByTouristSpot(spotName);
			
			if(tweets.size()==0) {
				tweets = tm.GetTweetsByTouristSpot(spot.getLocation());
			}
			
			
		
			request.setAttribute("tweets", tweets);
			
			System.out.println("Printing tweet objects");
			for(int i=0;i<tweets.size();i++) {
				System.out.println(tweets.get(i));
			}
			
			//sending google map url
			
			String name_spot = request.getParameter("touristSpotName");
			if(request.getParameter("value") != null) {
				String univ_Name = request.getParameter("value");
				request.setAttribute("value",univ_Name);
				
			}
		
			rd = request.getRequestDispatcher("WEB-INF/displaySpot.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("inputname")!=null) {
			System.out.println("Ajax request!----");
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
