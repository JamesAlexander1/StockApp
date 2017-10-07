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
         */
        String code = request.getParameter("search_code");
        //default graph is the yearly graph when the user just enters the code through the search bar.
        if (code != null) {
        	request.setAttribute("company", code);
            request.setAttribute("yearly_list", yearlyDao.queryData(code));
        //otherwise they must've pressed a time period button
        } else {
            if (request.getParameter("half_year") != null) {
                request.setAttribute("company", request.getParameter("half_year"));
                request.setAttribute("yearly_list", halfYearlyDao.queryData(request.getParameter("half_year")));
            
            } else if (request.getParameter("yearly") != null) {
                request.setAttribute("company", request.getParameter("yearly"));
                request.setAttribute("yearly_list", yearlyDao.queryData(request.getParameter("yearly")));
            
            } else if (request.getParameter("quarterly") != null) {
                request.setAttribute("company", request.getParameter("quarterly"));
                request.setAttribute("yearly_list", quarterlyDao.queryData(request.getParameter("quarterly")));
            
            } else if (request.getParameter("monthly") != null) {
                request.setAttribute("company", request.getParameter("monthly"));
            	request.setAttribute("yearly_list", monthDao.queryData(request.getParameter("monthly")));
            
            } else if (request.getParameter("weekly") != null) {
                request.setAttribute("company", request.getParameter("weekly"));
            	request.setAttribute("yearly_list", weekDao.queryData(request.getParameter("weekly")));
            
           //not sure how to do this is it even possible to have an else here?
            } else {
                request.setAttribute("company", request.getParameter("half_year"));
                request.setAttribute("yearly_list", yearlyDao.queryData(request.getParameter("half_year")));
            }

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
