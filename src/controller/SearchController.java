package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WeekClosingPriceDAO;

@WebServlet("/search")
public class SearchController extends HttpServlet {

    WeekClosingPriceDAO dao = new WeekClosingPriceDAO();
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String search_code = request.getParameter("search_value");    
        request.setAttribute("list", dao.queryData(search_code));
        
        
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/search.jsp");
        rd.forward(request, response);
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        doPost(request, response);
    }

}
