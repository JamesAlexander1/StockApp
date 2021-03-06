package json_parser;

import model.DatePricePoint;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map.Entry;


import com.google.gson.*;

import model.DateClosingPricePoint;
public abstract class YearlyClosingPriceParser {

    
    public static ArrayList<DatePricePoint<DateClosingPricePoint>> parseJson(String json){
        
        
        ArrayList<DatePricePoint<DateClosingPricePoint>> list = new ArrayList<DatePricePoint<DateClosingPricePoint>>();
        list.ensureCapacity(50);
        
        JsonElement element = new JsonParser().parse(json);
        JsonObject yearlyClosingPrice = element.getAsJsonObject();
        yearlyClosingPrice = yearlyClosingPrice.getAsJsonObject("Weekly Time Series");
        
        Iterator<Entry<String, JsonElement>> iterator = yearlyClosingPrice.entrySet().iterator();
        
        int temp = 0;
        while(iterator.hasNext()){
            
            if(temp >= 48){
                break;
            }
            Entry<String, JsonElement> entry = iterator.next();
            String[] timeAsString = entry.getKey().split("-");
            Integer[] time = new Integer[3];
            for(int i = 0; i < 3; i++){

                time[i] = Integer.parseInt(timeAsString[i]);
            }
            
            list.add(new DateClosingPricePoint(new GregorianCalendar(time[0], time[1], time[2]), entry.getValue().getAsJsonObject().get("4. close").getAsDouble()));
            temp ++;
        }
        return list;
    }
}
