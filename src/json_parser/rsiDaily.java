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

public class rsiDaily {
	 public static ArrayList<DatePricePoint<DateClosingPricePoint>> parseJson(String json, String period){
		 /**
	         * 0 = yearly
	         * 1 = half yearly
	         * 2 = quarterly
	         * 3 = monthly
	         * 4 = weekly
	         */
	        ArrayList<DatePricePoint<DateClosingPricePoint>> list = new ArrayList<DatePricePoint<DateClosingPricePoint>>();
	        
	        JsonElement element = new JsonParser().parse(json);
	        JsonObject weekClosingPrice = element.getAsJsonObject();
	        weekClosingPrice = weekClosingPrice.getAsJsonObject("Technical Analysis: RSI");
	        
	        Iterator<Entry<String, JsonElement>> iterator = weekClosingPrice.entrySet().iterator();
	        
	        int temp = 0;
	        int timePeriod = 0;
	        if (period.equals("2")) {
	        		timePeriod = 100;
	    	        list.ensureCapacity(119);
	        }
	        if (period.equals("3")) {
        			timePeriod = 28;
        			list.ensureCapacity(30);

	        }
	        if (period.equals("4")) {
        			timePeriod = 7;
        			list.ensureCapacity(8);

	        }
	        while(iterator.hasNext()){
	            
	            if(temp >= timePeriod){
	                break;
	            }
	            Entry<String, JsonElement> entry = iterator.next();
	            String[] timeAsString = entry.getKey().split("-");
	            Integer[] time = new Integer[3];
	            for(int i = 0; i < 3; i++){

	                time[i] = Integer.parseInt(timeAsString[i]);
	            }

	            list.add(new DateClosingPricePoint(new GregorianCalendar(time[0], time[1], time[2]), entry.getValue().getAsJsonObject().get("RSI").getAsDouble()));
	            temp ++;
	        }
	        return list;
	    }
}
