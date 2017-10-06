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
        
        String code = request.getParameter("search_code");
        
        request.setAttribute("sector_list", sectorDao.sectorQuery());
        request.setAttribute("week_list", weekDao.queryData(code));
        request.setAttribute("month_list", monthDao.queryData(code));
        request.setAttribute("quarterly_list", quarterlyDao.queryData(code));
        request.setAttribute("halfYearly_list", halfYearlyDao.queryData(code));
        request.setAttribute("yearly_list", yearlyDao.queryData(code));
        request.setAttribute("company", code);
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/" + "search.jsp");
       
        rd.forward(request, response);

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request, response);
    }

}
