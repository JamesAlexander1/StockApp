package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DataAndPriceDAO;
import dao.HalfYearlyClosingPriceDAO;
import dao.MonthClosingPriceDAO;
import dao.QuarterlyClosingPriceDAO;
import dao.SectorPerformDAO;
import dao.WeekClosingPriceDAO;
import dao.YearlyClosingPriceDAO;
import dao.smaChart;
import factory.dao.DataPriceDAOFactory;
import factory.dao.IDAOFactory;
import model.DateClosingPricePoint;
import model.NumeratedTimePeriods;
@WebServlet("/search")
public class SearchController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    private SectorPerformDAO sectorDao = new SectorPerformDAO("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
    
  
    private YearlyClosingPriceDAO yearlyDao = new YearlyClosingPriceDAO();
    
    private IDAOFactory<DataAndPriceDAO<DateClosingPricePoint>> daoFactory = DataPriceDAOFactory.getInstance();
    private DataAndPriceDAO<DateClosingPricePoint> dao = null;
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * First check if the price was directed from the search bar. If it was then load the yearly chart.
         * If the page was not directed from the search button, then user must have clicked on one of the time
         * period buttons. If that was the case we check which button was clicked, then load that relevant time
         * period. Button handling is handled by the else statement
         * for time period 
         * 0 = yearly
         * 1 = half yearly
         * 2 = quarterly
         * 3 = monthly
         * 4 = weekly
         */
	    
   
        String code = request.getParameter("search_code");
        //default graph is the yearly graph when the user just enters the code through the search bar.
        if (code != null) {
            
        	    
        	    
        	    dao = daoFactory.instantiateDAO("yearly");
        	    
        	    request.setAttribute("company", code);
            request.setAttribute("yearly_list", dao.queryData(code));
            request.setAttribute("time", NumeratedTimePeriods.YEARLY.getValue());
        //otherwise they must've pressed a time period button
        } else {
            
            String company = request.getParameter("company");
            
            request.setAttribute("company", company);
            
            String time_period = request.getParameter("time_period");
            dao = daoFactory.instantiateDAO(time_period);
            request.setAttribute("yearly_list", dao.queryData(company));
            
            if (time_period.matches("half_yearly")) {
              
               
                request.setAttribute("time", NumeratedTimePeriods.HALF_YEARLY.getValue());
            
            } else if (time_period.matches("yearly")) {
                
              
                request.setAttribute("time", NumeratedTimePeriods.YEARLY.getValue());
      
            } else if (time_period.matches("quarterly")) {
            
                
                request.setAttribute("time", NumeratedTimePeriods.QUARTERLY.getValue());

            } else if (time_period.matches("monthly")) {
             
            		request.setAttribute("time", NumeratedTimePeriods.MONTHLY.getValue());

            } else if (time_period.matches("weekly")) {
                
                request.setAttribute("time", NumeratedTimePeriods.WEEKLY.getValue());

            }

        }
        
        //if user wants to see sma (simple moving average) tech indicator
	    	if (request.getParameter("sma") != null) {
	    		String time = request.getParameter("timePeriod");
	    		smaChart sma = new smaChart(time);
	    		request.setAttribute("sma_chart", sma.queryData(request.getParameter("sma")));
	    		request.setAttribute("yearly_list", yearlyDao.queryData(request.getParameter("sma")));
	    	}
        request.setAttribute("sector_list", sectorDao.sectorQuery());

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "search.jsp");
       
        rd.forward(request, response);

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request, response);
    }

}
