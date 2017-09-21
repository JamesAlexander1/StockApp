package json_parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.*;

import model.OneDaySector;
import model.Sector;

/*
 * 
 */
public abstract class SingleDaySectorParser {

    /**
     * 
     * @param json
     * @return
     */
    public static OneDaySector parseJson(String json){
        
        
        OneDaySector oneDaySectorResults = new OneDaySector(new ArrayList<Sector>());
        
        
        JsonElement element = new JsonParser().parse(json);                                     //
        JsonObject oneDayObject = element.getAsJsonObject();
        oneDayObject = oneDayObject.getAsJsonObject("Rank B: 1 Day Performance");               //
        
        
        Iterator<Entry<String, JsonElement>> iterator = oneDayObject.entrySet().iterator();     
                    
        while(iterator.hasNext()){
            Entry<String, JsonElement> entry = iterator.next();                                 //
            
            oneDaySectorResults.addSector(new Sector(entry.getKey(), Double.parseDouble( entry.getValue().getAsString().replaceAll("%", ""))));
           
        }
        
        return oneDaySectorResults;
    }
}
