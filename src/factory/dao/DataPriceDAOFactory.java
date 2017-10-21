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
        if(daoType.contains("HALF_YEARLY")) {
            
            dao = new HalfYearlyClosingPriceDAO();
            
        }else if(daoType.contains("YEARLY")){
            
            dao = new YearlyClosingPriceDAO();
            
        }else if(daoType.contains("QUARTERLY")){
            
            dao = new QuarterlyClosingPriceDAO();
            
        }else if(daoType.contains("MONTHLY")){
            
            dao = new MonthClosingPriceDAO();
            
        }else if(daoType.contains("WEEKLY")){
            
            dao = new WeekClosingPriceDAO();
        }else {
            dao = new HalfYearlyClosingPriceDAO();
        }
        return dao;
    }
    
    public static DataPriceDAOFactory getInstance() {
        return new DataPriceDAOFactory();
    }
}
