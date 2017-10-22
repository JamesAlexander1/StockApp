package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DataAndPriceDAO;

import dao.SectorPerformDAO;


import factory.dao.DataPriceDAOFactory;
import factory.dao.IDAOFactory;

import model.DateClosingPricePoint;

import model.NumeratedTimePeriods;



@WebServlet("/compare")
public class compareController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    private SectorPerformDAO sectorDao = new SectorPerformDAO("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
  
    private IDAOFactory<DataAndPriceDAO<DateClosingPricePoint>> daoFactory = DataPriceDAOFactory.getInstance();
    private IDAOFactory<DataAndPriceDAO<DateClosingPricePoint>> seconddaoFactory = DataPriceDAOFactory.getInstance();

    private DataAndPriceDAO<DateClosingPricePoint> originalChart = null;
    private DataAndPriceDAO<DateClosingPricePoint> compareChart = null;
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String compareCode = request.getParameter("companyCompare");
        String originalCode = request.getParameter("company");

        //default graph is the yearly graph when the user just enters the code through the search bar.
        if (compareCode != null) {
            
            String time_period = request.getParameter("time_period");
            
            if (time_period != null) {
                
            		originalChart = daoFactory.instantiateDAO(time_period);
	     	    compareChart = seconddaoFactory.instantiateDAO(time_period);
	
	     	    if (originalChart.queryData(originalCode).isEmpty() || compareChart.queryData(compareCode).isEmpty()) {
	     	        
        	     	    	request.setAttribute("sector_list", sectorDao.sectorQuery());
        	     	    	RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "error.jsp");
        	     	    	rd.forward(request, response);
        	     	    	return;
	     	    }

	     	  
	     	    request.setAttribute("time", time_period);
		     	    
	            request.setAttribute("yearly_list", originalChart.queryData(originalCode));
		        request.setAttribute("second_yearly_list", compareChart.queryData(compareCode));
		        request.setAttribute("company", originalCode);
	     	    request.setAttribute("companyCompare", compareCode);
	     	    
	     	    
            } else {
	        	 	originalChart = daoFactory.instantiateDAO("YEARLY");
	     	    compareChart = seconddaoFactory.instantiateDAO("YEARLY");
    
	     	   if(originalChart.queryData(originalCode).isEmpty() || compareChart.queryData(compareCode).isEmpty()) {
	     	       
        	     	    	request.setAttribute("sector_list", sectorDao.sectorQuery());
        	     	    	RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "error.jsp");
        	     	    	rd.forward(request, response);
        	     	    	return;
	     	    }

	     	    
     	       request.setAttribute("company", originalCode);
	     	   request.setAttribute("companyCompare", compareCode);
	     	   request.setAttribute("time", NumeratedTimePeriods.YEARLY.name());
		     	    
	           request.setAttribute("yearly_list", originalChart.queryData(originalCode));
	           request.setAttribute("second_yearly_list", compareChart.queryData(compareCode));
            }
        	   
        	   
        } 
            

        
        
	    	
        request.setAttribute("sector_list", sectorDao.sectorQuery());
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "compare.jsp");
        rd.forward(request, response);

    }
    
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request, response);
    }

}
