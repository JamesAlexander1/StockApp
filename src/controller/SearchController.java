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
import factory.dao.ISpecificTimeDAOFactory;
import factory.dao.IndicatorDAOFactory;
import factory.dao.MacdDAOFactory;
import model.DateClosingPricePoint;
import model.macdDateClosingPrice;
import model.NumeratedTimePeriods;



@WebServlet("/search")
public class SearchController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    private SectorPerformDAO sectorDao = new SectorPerformDAO("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
  
    private IDAOFactory<DataAndPriceDAO<DateClosingPricePoint>> daoFactory = DataPriceDAOFactory.getInstance();
    private ISpecificTimeDAOFactory<DataAndPriceDAO<DateClosingPricePoint>> indicatorFactory = IndicatorDAOFactory.getInstance();
    private MacdDAOFactory macdFactory = MacdDAOFactory.getInstance();
    
    private DataAndPriceDAO<DateClosingPricePoint> dao = null;
    private DataAndPriceDAO<DateClosingPricePoint> rsiDao = null;
    private DataAndPriceDAO<DateClosingPricePoint> smaDao = null;
    private DataAndPriceDAO<macdDateClosingPrice> macdDao = null;
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String code = request.getParameter("search_code");
        
        //default graph is the yearly graph when the user just enters the code through the search bar.
        if (code != null) {
            
        	    
        	    
        	    dao = daoFactory.instantiateDAO("YEARLY");
        	    rsiDao = indicatorFactory.instantiateDAO("RSI", NumeratedTimePeriods.YEARLY.name());
        	    smaDao = indicatorFactory.instantiateDAO("SMA", NumeratedTimePeriods.YEARLY.name());
        	    macdDao = macdFactory.instantiateDAO(NumeratedTimePeriods.YEARLY.name());
        	    
        	    request.setAttribute("company", code);
        	    request.setAttribute("time", NumeratedTimePeriods.YEARLY.name());
        	    
            request.setAttribute("yearly_list", dao.queryData(code));
            request.setAttribute("rsi_chart", rsiDao.queryData(code));
            request.setAttribute("macd_chart", macdDao.queryData(code));
            request.setAttribute("sma_chart", smaDao.queryData(code));
            
        //otherwise they must've pressed a time period button
        } else {
            
            String company = request.getParameter("company");
            String time_period = request.getParameter("time_period");
        
            dao = daoFactory.instantiateDAO(time_period);
            rsiDao = indicatorFactory.instantiateDAO("RSI", time_period);
            smaDao = indicatorFactory.instantiateDAO("SMA", time_period);
            macdDao = macdFactory.instantiateDAO(time_period);
            
            request.setAttribute("company", company);  
            request.setAttribute("time", time_period);
            
            request.setAttribute("yearly_list", dao.queryData(company));
            request.setAttribute("rsi_chart", rsiDao.queryData(company));
            request.setAttribute("macd_chart", macdDao.queryData(company));
            request.setAttribute("sma_chart", smaDao.queryData(company));
            

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
