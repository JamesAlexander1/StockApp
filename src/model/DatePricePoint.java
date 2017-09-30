package model;


import java.util.GregorianCalendar;

public interface DatePricePoint<T> extends Comparable<T>{

    public GregorianCalendar getDate();
    public void setDate(GregorianCalendar newDate);
    
    public Double getPrice();
    public void setPrice(Double newPrice);
    
    Integer getYear();
    Integer getMonth();
    Integer getDay();
}
