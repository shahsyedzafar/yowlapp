


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
			List<String> ts_names = new ArrayList<String>();
			
			
			Iterator<TouristSpot> it = touristSpots.iterator();
			
			//System.out.println("Printing tourist spot names");
			while(it.hasNext()) {
				if(!ts_names.contains(it.next().getName())) {
					ts_names.add(it.next().getName());
				}
				
			}
			System.out.println("Printing clean names!");
			Iterator<String> it2 = ts_names.iterator();;
			while(it2.hasNext()) {
				System.out.println(it2.next());
				
			}
			
			
			request.setAttribute("touristSpots", ts_names);
			rd = request.getRequestDispatcher("WEB-INF/displayUniversity.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("touristSpotName") != null) {
			String spotName = request.getParameter("touristSpotName");
			sm = new SpotManager();
			TouristSpot spot = sm.GetSpotByName(spotName);
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
		
			rd = request.getRequestDispatcher("WEB-INF/displaySpot.jsp");
			rd.forward(request, response);
			
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
