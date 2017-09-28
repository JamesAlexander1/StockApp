package model;


import java.util.GregorianCalendar;

public interface DatePricePoint<T extends Comparable<T>>{

    GregorianCalendar getDate();
    void setDate(GregorianCalendar newDate);
    
    Double getPrice();
    void setPrice(Double newPrice);
}
