package factory.dao;

import dao.DataAndPriceDAO;
import dao.MACDChartDAO;
import model.MacdDateClosingPrice;


public class MacdDAOFactory {

    public DataAndPriceDAO<MacdDateClosingPrice> instantiateDAO(String time_period) {
        
        return new MACDChartDAO(time_period);
        
    }
    
    public static MacdDAOFactory getInstance() {
        
        return new MacdDAOFactory();
    }
    
}
