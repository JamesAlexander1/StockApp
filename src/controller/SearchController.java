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
  
    private IDAOFactory<DataAndPriceDAO<DateClosingPricePoint>> daoFactory = DataPriceDAOFactory.getInstance();
    private DataAndPriceDAO<DateClosingPricePoint> dao = null;
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
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
            String time_period = request.getParameter("time_period");
        
            dao = daoFactory.instantiateDAO(time_period);
            
            request.setAttribute("yearly_list", dao.queryData(company));
            request.setAttribute("company", company);
            
            request.setAttribute("time", time_period);
            

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
