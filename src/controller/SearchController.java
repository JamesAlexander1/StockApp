package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.SectorPerformDAO;
import dao.WeekClosingPriceDAO;
import model.DateClosingPricePoint;
import model.DatePricePoint;

@WebServlet("/search")
public class SearchController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    private SectorPerformDAO sectorDao = new SectorPerformDAO("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
    private WeekClosingPriceDAO weekDao = new WeekClosingPriceDAO();
    
    public static final String VALID = "valid";
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	// The search code has been passed to us as a parameter
        String code = request.getParameter("search_code");
        
        boolean validCode = true;
        
        // All valid codes are less than length 6
        if (code.length() > 5) {
        	// if it's too long, it's invalid
        	request.setAttribute("failure_reason", "you need to search for a stock code (5 characters or less)");
        	validCode = false;
        } else { 
        	// otherwise, it's valid!
        	request.setAttribute("valid_search", "valid");
        	request.setAttribute("sector_list", sectorDao.sectorQuery());
        	
        	ArrayList<DatePricePoint<DateClosingPricePoint>> weekData = weekDao.queryData(code);
        	if (weekData != null) {
				request.setAttribute("week_list", weekData);
        	} else {
        		validCode = false;
				request.setAttribute("failure_reason", "there's no stock called " + code);
        		
        	}
        }
        
        RequestDispatcher rd;
        
        if (validCode) {
			rd = request.getRequestDispatcher("WEB-INF/" + "search.jsp");
        } else {
        	rd = request.getRequestDispatcher("WEB-INF/" + "search_failed.jsp");
        }
       
        rd.forward(request, response);

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request, response);
    }

}
