package dao;

import java.util.ArrayList;

import model.DatePricePoint;

public interface DataAndPriceDAO<T> {

      ArrayList<DatePricePoint<T>> queryData(String companyCode);
        
    
}
