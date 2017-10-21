package factory.dao;

import dao.DataAndPriceDAO;
import dao.MACDChartDAO;
import model.macdDateClosingPrice;


public class MacdDAOFactory {

    public DataAndPriceDAO<macdDateClosingPrice> instantiateDAO(String time_period) {
        
        return new MACDChartDAO(time_period);
        
    }
    
    public static MacdDAOFactory getInstance() {
        
        return new MacdDAOFactory();
    }
    
}
