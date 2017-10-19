package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MACDDateClosingPrice implements DatePricePoint<MACDDateClosingPrice>{
	private GregorianCalendar shortDate;
    private Double macdPrice;
    private Double macdHistPrice;
    private Double macdSignalPrice;
    
    
    public MACDDateClosingPrice(GregorianCalendar newDate, Double macd, Double macdHist, Double macdSignal){
        
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
    
    public Integer getYear(){
        return shortDate.get(Calendar.YEAR);
    }
    
    public Integer getMonth(){
        return shortDate.get(Calendar.MONTH);
    }
    
    public Integer getDay(){
        return shortDate.get(Calendar.DAY_OF_MONTH);
    }
    @Override
    public int compareTo(MACDDateClosingPrice o) {
        
        return o.getDate().get(Calendar.DAY_OF_MONTH) - this.shortDate.get(Calendar.DAY_OF_MONTH);
    }
    @Override
    public Double getPrice() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void setPrice(Double newPrice) {
        // TODO Auto-generated method stub
        
    }

}
