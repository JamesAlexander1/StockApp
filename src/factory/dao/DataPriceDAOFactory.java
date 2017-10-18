package factory.dao;

import dao.DataAndPriceDAO;
import dao.HalfYearlyClosingPriceDAO;
import dao.MonthClosingPriceDAO;
import dao.QuarterlyClosingPriceDAO;
import dao.WeekClosingPriceDAO;
import dao.YearlyClosingPriceDAO;
import model.DateClosingPricePoint;

public class DataPriceDAOFactory implements IDAOFactory<DataAndPriceDAO<DateClosingPricePoint>>{

    
    public DataAndPriceDAO<DateClosingPricePoint> instantiateDAO(String daoType){
        
        DataAndPriceDAO<DateClosingPricePoint> dao = null;
        if(daoType.contains("half_year")) {
            
            dao = new HalfYearlyClosingPriceDAO();
            
        }else if(daoType.contains("yearly")){
            
            dao = new YearlyClosingPriceDAO();
            
        }else if(daoType.contains("quarterly")){
            
            dao = new QuarterlyClosingPriceDAO();
            
        }else if(daoType.contains("monthly")){
            
            dao = new MonthClosingPriceDAO();
            
        }else if(daoType.contains("weekly")){
            
            dao = new WeekClosingPriceDAO();
        }else {
            //ERROR
        }
        return dao;
    }
}
