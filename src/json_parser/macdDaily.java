package json_parser;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.DatePricePoint;
import model.NumeratedTimePeriods;
import model.MacdDateClosingPrice;

public class macdDaily {
public static ArrayList<DatePricePoint<MacdDateClosingPrice>> parseJson(String json, String period){
        
        
        ArrayList<DatePricePoint<MacdDateClosingPrice>> list = new ArrayList<DatePricePoint<MacdDateClosingPrice>>();
        list.ensureCapacity(50);
        
        JsonElement element = new JsonParser().parse(json);
        JsonObject yearlyClosingPrice = element.getAsJsonObject();
        yearlyClosingPrice = yearlyClosingPrice.getAsJsonObject("Technical Analysis: MACD");
        Iterator<Entry<String, JsonElement>> iterator = yearlyClosingPrice.entrySet().iterator();
        
        int temp = 0;
        int timePeriod = 0;
        
        if (period.equals(NumeratedTimePeriods.QUARTERLY.name())){
        		timePeriod = 100;
    	        list.ensureCapacity(119);
        }
        if (period.equals(NumeratedTimePeriods.MONTHLY.name())) {
    			timePeriod = 28;
    			list.ensureCapacity(30);

        }
        if (period.equals(NumeratedTimePeriods.WEEKLY.name())) {
    			timePeriod = 7;
    			list.ensureCapacity(8);

        }
        while(iterator.hasNext()){
            
            if(temp >= timePeriod){ break;  }
            
            Entry<String, JsonElement> entry = iterator.next();
            String[] timeAsString = entry.getKey().split("-");
            Integer[] time = new Integer[3];
            
            for(int i = 0; i < 3; i++){

                time[i] = Integer.parseInt(timeAsString[i]);
            }
            
            list.add(new MacdDateClosingPrice(new GregorianCalendar(time[0], time[1], time[2]), entry.getValue().getAsJsonObject().get("MACD").getAsDouble(), 
            		entry.getValue().getAsJsonObject().get("MACD_Hist").getAsDouble(), entry.getValue().getAsJsonObject().get("MACD_Signal").getAsDouble()));
            //System.out.println(entry.getValue().getAsJsonObject().get("SMA").getAsDouble());
            temp ++;
        }
        
        return list;
    }
}
