package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HalfYearlyClosingPriceDAO;
import dao.MonthClosingPriceDAO;
import dao.QuarterlyClosingPriceDAO;
import dao.SectorPerformDAO;
import dao.WeekClosingPriceDAO;
import dao.YearlyClosingPriceDAO;
import dao.macdChart;
import dao.rsiChart;
import dao.smaChart;
import jdk.management.resource.ResourceRequest;

@WebServlet("/search")
public class SearchController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    private SectorPerformDAO sectorDao = new SectorPerformDAO("https://www.alphavantage.co/query?function=SECTOR&apikey=CR72JXL4TE7T2WF4");
    private WeekClosingPriceDAO weekDao = new WeekClosingPriceDAO();
    private MonthClosingPriceDAO monthDao = new MonthClosingPriceDAO();
    private QuarterlyClosingPriceDAO quarterlyDao = new QuarterlyClosingPriceDAO();
    private HalfYearlyClosingPriceDAO halfYearlyDao = new HalfYearlyClosingPriceDAO();
    private YearlyClosingPriceDAO yearlyDao = new YearlyClosingPriceDAO();
    
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
    		//getting SMA chart
    		
	   
        String code = request.getParameter("search_code");
        
        //default graph is the yearly graph when the user just enters the code through the search bar.
        if (code != null) {
        	    request.setAttribute("company", code);
            request.setAttribute("yearly_list", yearlyDao.queryData(code));
            request.setAttribute("time", 0);
            rsiChart rsiNewChart = new rsiChart("0");
            request.setAttribute("rsi_chart", rsiNewChart.queryData(code));
            macdChart macdNewChart = new macdChart("0");
            request.setAttribute("macd_chart", macdNewChart.queryData(code));
            
        //otherwise they must've pressed a time period button
        } else {
            if (request.getParameter("half_year") != null) {
                request.setAttribute("company", request.getParameter("half_year"));
                request.setAttribute("yearly_list", halfYearlyDao.queryData(request.getParameter("half_year")));
                request.setAttribute("time", 1);
                rsiChart rsiNewChart = new rsiChart("1");
                request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("half_year")));
                macdChart macdNewChart = new macdChart("1");
                request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("half_year")));

            } else if (request.getParameter("yearly") != null) {
                request.setAttribute("company", request.getParameter("yearly"));
                request.setAttribute("yearly_list", yearlyDao.queryData(request.getParameter("yearly")));
                request.setAttribute("time", 0);
                rsiChart rsiNewChart = new rsiChart("0");
                request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("yearly")));
                macdChart macdNewChart = new macdChart("0");
                request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("yearly")));
            } else if (request.getParameter("quarterly") != null) {
                request.setAttribute("company", request.getParameter("quarterly"));
                request.setAttribute("yearly_list", quarterlyDao.queryData(request.getParameter("quarterly")));
                request.setAttribute("time", 2);
                rsiChart rsiNewChart = new rsiChart("2");
                request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("quarterly")));
                macdChart macdNewChart = new macdChart("2");
                request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("quarterly")));
            } else if (request.getParameter("monthly") != null) {
                request.setAttribute("company", request.getParameter("monthly"));
                request.setAttribute("yearly_list", monthDao.queryData(request.getParameter("monthly")));
            		request.setAttribute("time", 3);
            		rsiChart rsiNewChart = new rsiChart("3");
                 request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("monthly")));
                 macdChart macdNewChart = new macdChart("3");
                 request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("monthly")));
            } else if (request.getParameter("weekly") != null) {
                request.setAttribute("company", request.getParameter("weekly"));
            	    request.setAttribute("yearly_list", weekDao.queryData(request.getParameter("weekly")));
                request.setAttribute("time", 4);
                rsiChart rsiNewChart = new rsiChart("4");
                request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("weekly")));
                macdChart macdNewChart = new macdChart("4");
                request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("weekly")));
           //not sure how to do this is it even possible to have an else here?
            } /*else {
                request.setAttribute("company", request.getParameter("half_year"));
                request.setAttribute("yearly_list", yearlyDao.queryData(request.getParameter("half_year")));
                request.setAttribute("time", 1);

            }*/

        }
      if (request.getParameter("removesma") == null) {
    	  
      
	    	if (request.getParameter("sma") != null || request.getParameter("clicksma") != null) {
	    		String time = "0";
	    		if (request.getParameter("yearly") != null) {
	    			time = "0";
	    		} else if (request.getParameter("half_year") != null) {
	    			time = "1";
	    		} else if (request.getParameter("quarterly") != null) {
	    			time = "2";
	    		} else if (request.getParameter("monthly") != null) {
	    			time = "3";
	    		} else if (request.getParameter("weekly") != null) {
	    			time = "4";
	    		}
	    		request.setAttribute("smaClick", "yes");
	    		request.setAttribute("company", request.getParameter("company"));
	    		smaChart sma = new smaChart(time);
	    		request.setAttribute("sma_chart", sma.queryData(request.getParameter("company")));
	    		request.setAttribute("time", time);
	    		if (time.equals("0")) {
	    			request.setAttribute("yearly_list", yearlyDao.queryData(request.getParameter("company")));
	    			rsiChart rsiNewChart = new rsiChart("0");
	            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
	            macdChart macdNewChart = new macdChart("0");
	            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
	    		}
	    		if (time.equals("1")) {
	    			request.setAttribute("yearly_list", halfYearlyDao.queryData(request.getParameter("company")));
	    			rsiChart rsiNewChart = new rsiChart("1");
	            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
	            macdChart macdNewChart = new macdChart("1");
	            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
	    		}
	    		if (time.equals("2")) {
	    			request.setAttribute("yearly_list", quarterlyDao.queryData(request.getParameter("company")));
	    			rsiChart rsiNewChart = new rsiChart("2");
	            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
	            macdChart macdNewChart = new macdChart("2");
	            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
	    		}
	    		if (time.equals("3")) {
	    			request.setAttribute("yearly_list", monthDao.queryData(request.getParameter("company")));
	    			rsiChart rsiNewChart = new rsiChart("3");
	            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
	            macdChart macdNewChart = new macdChart("3");
	            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
	    		}
	    		if (time.equals("4")) {
	    			request.setAttribute("yearly_list", weekDao.queryData(request.getParameter("company")));
	    			rsiChart rsiNewChart = new rsiChart("4");
	            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
	            macdChart macdNewChart = new macdChart("4");
	            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
	    		}
	    	}
      }
	    	
     	if (request.getParameter("removesma") != null) {
    		String time = request.getParameter("timePeriod");
    		request.setAttribute("smaClick", null);
    		request.setAttribute("company", request.getParameter("company"));
    		request.setAttribute("time", time);
    		if (time.equals("0")) {
    			request.setAttribute("yearly_list", yearlyDao.queryData(request.getParameter("company")));
    			rsiChart rsiNewChart = new rsiChart("0");
            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
            macdChart macdNewChart = new macdChart("0");
            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
    		}
    		if (time.equals("1")) {
    			request.setAttribute("yearly_list", halfYearlyDao.queryData(request.getParameter("company")));
    			rsiChart rsiNewChart = new rsiChart("1");
            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
            macdChart macdNewChart = new macdChart("1");
            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
    		}
    		if (time.equals("2")) {
    			request.setAttribute("yearly_list", quarterlyDao.queryData(request.getParameter("company")));
    			rsiChart rsiNewChart = new rsiChart("2");
            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
            macdChart macdNewChart = new macdChart("2");
            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
    		}
    		if (time.equals("3")) {
    			request.setAttribute("yearly_list", monthDao.queryData(request.getParameter("company")));
    			rsiChart rsiNewChart = new rsiChart("3");
            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
            macdChart macdNewChart = new macdChart("3");
            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
    		}
    		if (time.equals("4")) {
    			request.setAttribute("yearly_list", weekDao.queryData(request.getParameter("company")));
    			rsiChart rsiNewChart = new rsiChart("4");
            request.setAttribute("rsi_chart", rsiNewChart.queryData(request.getParameter("company")));
            macdChart macdNewChart = new macdChart("4");
            request.setAttribute("macd_chart", macdNewChart.queryData(request.getParameter("company")));
    		}
    	}
        //if user wants to see sma (simple moving average) tech indicator
	    	
        request.setAttribute("sector_list", sectorDao.sectorQuery());
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "search.jsp");
        rd.forward(request, response);

    }
    
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request, response);
    }

}
