package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chart.dailyData;
import chart.week;
import dao.SectorPerformDAO;


/**
 * LandingController extends the HttpServlet class and implements the Controller pattern as part of MVC.
 * Servlets take in HTTP Requests (GET, POST, etc) and return HTTP Responses, Landing Controller handles this for all requests...
 * ...to the StockApp/ and StockApp/home URL requests.
 * @author james
 *
 */
@WebServlet({"/home", ""})
public class LandingController extends HttpServlet{

    static private SectorPerformDAO dao = new SectorPerformDAO("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
    //url for microsoft charting price.
    static private week chartWeek = new week("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=CR72JXL4TE7T2WF4");
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * doPost method handles HTTP POST requests to the / and /home URLs.
     * Parameters:  <code>HttpServletRequest request</code>, <code>HttpServletResponse response</code>
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (dailyData instance : chartWeek.getDataString()) {
        	System.out.println(instance.getDate() + " " + instance.getValue());
        }
        /**
         * Adding query results as attribute, to be placed in html as part of jsp file.
         * Adding query results for chart data here as well.
         */
        request.setAttribute("sector_list", dao.sectorQuery());
        request.setAttribute("chart_data", chartWeek.getDataString());
        
        /**
         * In this case. we return the HTML in landing.jsp  in a http response to the clients browser. This is taken care of behind the scenes.
         */
        
        /**
         * NOTE: jsp files are simply html files with functionality to directly access attributes (values) stored with the response object in this method.
         */
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "landing.jsp");
        /**
         * The default file directory root is WebContent/
         */
        rd.forward(request, response);
    }
    
    /**
     * doGet method handles HTTP GET requests to the / and /home URLs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /**
         * In this case, we simply handle the request the same way as a POST i.e returning the html in landing.html
         */
        doPost(request, response);
    }
}
