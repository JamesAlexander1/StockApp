package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateClosingPricePoint implements DatePricePoint<DateClosingPricePoint>{

    private GregorianCalendar shortDate;
    private Double price;
    
    
    public DateClosingPricePoint(GregorianCalendar newDate, Double newPrice){
        
        shortDate = newDate;
        price = newPrice;
        
    }
    @Override
    public GregorianCalendar getDate() {
       
        return shortDate;
    }

    @Override
    public void setDate(GregorianCalendar newDate) {
        shortDate = newDate;

    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double newPrice) {
        price = newPrice;
    }
    
    @Override
    public int compareTo(DateClosingPricePoint o) {
       
        return o.getDate().get(Calendar.DAY_OF_MONTH) - shortDate.get(Calendar.DAY_OF_MONTH);
    }
    
    @Override
    public Integer getYear(){
        return shortDate.get(Calendar.YEAR);
    }
    
    @Override
    public Integer getMonth(){
        return shortDate.get(Calendar.MONTH);
    }
    
    @Override
    public Integer getDay(){
        return shortDate.get(Calendar.DAY_OF_MONTH);
    }

}
