package json_parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.*;

import model.OneDaySector;
import model.Sector;

/**
 * JSON parser class for sector performance response from AlphaVantage API.
 * @author James Alexander
 *
 */
public abstract class SingleDaySectorParser {

    /**
     * static sector list to return in case of JSON parsing error or exception.
     */
    private static OneDaySector errorSectors = new OneDaySector(new ArrayList<Sector>());
    static {
        for(int i = 0; i < 5; i ++){
            errorSectors.addSector(new Sector("Error", 0.0));
        }
    }
    
    
    /**
     * Static method for parsing a String for 1 day performance of S&P stock sectors.
     * preconditions:   JSON String contains Sector data as defined by AlphaVantage API
     * @param json
     * @return
     */
    public static OneDaySector parseJson(String json){
        
        
        OneDaySector oneDaySectorResults = new OneDaySector(new ArrayList<Sector>());
        
        
        JsonElement element = new JsonParser().parse(json);                                         //First, convert JSON String into tree of JSON objects,      
        if(! element.isJsonObject()){ return errorSectors;}
        
        try{
            JsonObject oneDayObject = element.getAsJsonObject();
           
            oneDayObject = oneDayObject.getAsJsonObject("Rank B: 1 Day Performance");               //retrieve yesterdays performance attributes as a JSON object.
            
            Set<Map.Entry<String,JsonElement>> set = oneDayObject.entrySet();                       //convert into set of individual key,value pairs.
            if(set.isEmpty() || set == null){ return errorSectors; }
            
            Iterator<Entry<String, JsonElement>> iterator = set.iterator();  
            
            while(iterator.hasNext()){
                
                Entry<String, JsonElement> entry = iterator.next();                                 // Iterate through set and convert into Sector model object.
                oneDaySectorResults.addSector(new Sector(entry.getKey(), Double.parseDouble( entry.getValue().getAsString().replaceAll("%", ""))));
               
            }
            return oneDaySectorResults;
        
        }catch(IllegalStateException e){                                                            //If any part of JSON parsing method should fail, return error message collections.
            //log
            return errorSectors;
        }
        
        
    }
    
    
    
}
