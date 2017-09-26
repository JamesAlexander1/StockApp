package dao;

import java.util.List;

import http.StringJsonURL;
import json_parser.SingleDaySectorParser;
import model.OneDaySector;
import model.Sector;

/**
 * Data Access Object for retrieving collections of stock sector performance indicators from API.
 * @author James Alexander
 *
 */
public class SectorPerformDAO {

    /**
     * URL for API queries
     */
    private String query;
    
    /**
     * 
     * @param url   - API query URL as a String.
     */
    public SectorPerformDAO(String url){
        query = url;
    }
    
    /**
     * Method retrieves a List of Sector objects comprised of 5 best and worst performing sectors by movement in closing price for yesterday.
     * @return  List<Sector> sectors
     */
    public List<Sector> sectorQuery(){
        
        List<Sector> sectors = null;                                //Retrieves JSON response from specified URL as a String object, calls parseJson...
                                                                    //...on SingleDaySectorObject to retrieve wrapper object of Sector Collection, then calls...
                                                                    //...getSectors() to retrieve underlying Collections object. (in this case a List<>)
        
        sectors = SingleDaySectorParser.parseJson(new StringJsonURL(query).getResponse()).getSectors();
        
                                                                    //Then, we sort Sector Collection and retrieve the best and worst performers for display.
        
        sectors = OneDaySector.getFiveBestAndWorstSectors(OneDaySector.sortSectors(sectors));
        
        return sectors;
    }
}
