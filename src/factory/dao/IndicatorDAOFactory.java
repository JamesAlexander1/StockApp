package factory.dao;

import dao.DataAndPriceDAO;
import dao.RSIChartDAO;
import dao.SMAChartDAO;
import model.DateClosingPricePoint;

/**
 * Factory class for instantiating DAO objects that return technical indicator chart data that can depend on the specified time interval.
 * @author James Alexander
 *
 */
public class IndicatorDAOFactory implements ISpecificTimeDAOFactory<DataAndPriceDAO<DateClosingPricePoint>> {

    @Override
    public DataAndPriceDAO<DateClosingPricePoint> instantiateDAO(String type, String time_interval) {
        
        if(type.matches("RSI")) {
            
            return new RSIChartDAO(time_interval);
            
        }else if(type.matches("SMA")) {
            
            return new SMAChartDAO(time_interval);
            
        }else {
            //TODO: throw exception.
            return null;
        }
    }

}
