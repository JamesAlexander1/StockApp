package chart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import http.StringJsonURL;
/**
 * Week.java takes in a url string and uses the class StringJsonURL to extract the Json element.
 * 
 * @author Johnson Xu
 *
 */
public class week {
	String query;
	public static ArrayList<dailyData> keep = new ArrayList<dailyData>();
	
	public week(String url) {
		query = url;
		String data = new StringJsonURL(query).getResponse();
		JsonElement element = new JsonParser().parse(data);
		JsonObject oneDay = element.getAsJsonObject();
		JsonObject jsonChild = oneDay.getAsJsonObject("Time Series (Daily)");
		Iterator<Entry<String, JsonElement>> iterator = jsonChild.entrySet().iterator();
		int counter = 0;
		while(iterator.hasNext() && counter <= 7) {
			Entry<String, JsonElement> entry = iterator.next();
			JsonObject child = oneDay.getAsJsonObject("Time Series (Daily)").getAsJsonObject(entry.getKey());
			Iterator<Entry<String, JsonElement>> nextIterator = child.entrySet().iterator();
			
			//iterates through the json object and extraccts the price and date for the past 7 trading days. 
			while(nextIterator.hasNext()) {
				Entry<String, JsonElement> nextEntry = nextIterator.next();
				if (nextEntry.getKey().equals("4. close")) {
					dailyData temp = new dailyData(entry.getKey(), Double.parseDouble(nextEntry.getValue().getAsString().replaceAll("\"", "")));
					keep.add(temp);
					counter++;
				}
			}
		}
	}
	
	public static ArrayList<dailyData> getDataString() {
		return keep;
	}
}
