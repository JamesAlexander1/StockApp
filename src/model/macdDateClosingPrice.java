package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class macdDateClosingPrice {
	private GregorianCalendar shortDate;
    private Double macdPrice;
    private Double macdHistPrice;
    private Double macdSignalPrice;
    
    
    public macdDateClosingPrice(GregorianCalendar newDate, Double macd, Double macdHist, Double macdSignal){
        
        shortDate = newDate;
        macdPrice = macd;
        macdHistPrice = macdHist;
        macdSignalPrice = macdSignal;
        
    }
    public GregorianCalendar getDate() {
       
        return shortDate;
    }

    public void setDate(GregorianCalendar newDate) {
        shortDate = newDate;

    }

    public Double getMacdPrice() {
        return macdPrice;
    }
    
    public Double getMacdHistPrice() {
        return macdHistPrice;
    }
    
    public Double getMacdSignalPrice() {
        return macdSignalPrice;
    }
    
    public int compareTo(DateClosingPricePoint o) {
       
        return o.getDate().get(Calendar.DAY_OF_MONTH) - shortDate.get(Calendar.DAY_OF_MONTH);
    }
    
    public Integer getYear(){
        return shortDate.get(Calendar.YEAR);
    }
    
    public Integer getMonth(){
        return shortDate.get(Calendar.MONTH);
    }
    
    public Integer getDay(){
        return shortDate.get(Calendar.DAY_OF_MONTH);
    }

}
