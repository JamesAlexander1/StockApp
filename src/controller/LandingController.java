package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * LandingController extends the HttpServlet class and implements the Controller pattern as part of MVC.
 * Servlets take in HTTP Requests (GET, POST, etc) and return HTTP Responses, Landing Controller handles this for all requests...
 * ...to the StockApp/ and StockApp/home URL requests.
 * @author james
 *
 */
@WebServlet({"/home", ""})
public class LandingController extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * doPost method handles HTTP POST requests to the / and /home URLs.
     * Parameters:  <code>HttpServletRequest request</code>, <code>HttpServletResponse response</code>
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        /**
         * In this case. we return the HTML in landing.html in a http response to the clients browser. This is taken care of behind the scenes.
         */
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "landing.html");
        /**
         * The default file directory root is WebContent/
         */
        rd.forward(request, response);
    }
    
    /**
     * doGet method handles HTTP GET requests to the / and /home URLs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        /**
         * In this case, we simply handle the request the same way as a POST i.e returning the html in landing.html
         */
        doPost(request, response);
    }
}
