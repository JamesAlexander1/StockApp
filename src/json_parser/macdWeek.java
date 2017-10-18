package json_parser;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.DateClosingPricePoint;
import model.DatePricePoint;
import model.macdDateClosingPrice;

public class macdWeek {
public static ArrayList<macdDateClosingPrice> parseJson(String json, String period){
        
        
        ArrayList<macdDateClosingPrice> list = new ArrayList<macdDateClosingPrice>();
        list.ensureCapacity(50);
        JsonElement element = new JsonParser().parse(json);
        JsonObject yearlyClosingPrice = element.getAsJsonObject();
        yearlyClosingPrice = yearlyClosingPrice.getAsJsonObject("Technical Analysis: MACD");
        Iterator<Entry<String, JsonElement>> iterator = yearlyClosingPrice.entrySet().iterator();
        int temp = 0;
        int timePeriodCounter = 0;
        if (period.equals("0")) {
        		timePeriodCounter = 48;
        } else {
        		timePeriodCounter = 24;
        }
        while(iterator.hasNext()){
            
            if(temp >= timePeriodCounter){
                break;
            }
            Entry<String, JsonElement> entry = iterator.next();
            String[] timeAsString = entry.getKey().split("-");
            Integer[] time = new Integer[3];
            for(int i = 0; i < 3; i++){

                time[i] = Integer.parseInt(timeAsString[i]);
            }
            
            list.add(new macdDateClosingPrice(new GregorianCalendar(time[0], time[1], time[2]), entry.getValue().getAsJsonObject().get("MACD").getAsDouble(), 
            		entry.getValue().getAsJsonObject().get("MACD_Hist").getAsDouble(), entry.getValue().getAsJsonObject().get("MACD_Signal").getAsDouble()));
            //System.out.println(entry.getValue().getAsJsonObject().get("SMA").getAsDouble());
            temp ++;
        }
        
        return list;
    }
}
