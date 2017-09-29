package json_parser;

import model.DatePricePoint;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map.Entry;


import com.google.gson.*;

import model.DateClosingPricePoint;
public abstract class WeeklyClosingPriceParser {

    
    public static ArrayList<DatePricePoint<DateClosingPricePoint>> parseJson(String json){
        
        
        ArrayList<DatePricePoint<DateClosingPricePoint>> list = new ArrayList<DatePricePoint<DateClosingPricePoint>>();
        list.ensureCapacity(10);
        
        JsonElement element = new JsonParser().parse(json);
        JsonObject weekClosingPrice = element.getAsJsonObject();
        weekClosingPrice = weekClosingPrice.getAsJsonObject("Time Series (Daily)");
        
        Iterator<Entry<String, JsonElement>> iterator = weekClosingPrice.entrySet().iterator();
        
        int temp = 0;
        while(iterator.hasNext()){
            
            if(temp >= 7){
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
