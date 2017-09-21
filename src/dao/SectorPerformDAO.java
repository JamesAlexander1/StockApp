package dao;

import java.util.List;

import http.StringJsonURL;
import json_parser.SingleDaySectorParser;
import model.OneDaySector;
import model.Sector;

/**
 * 
 * @author james
 *
 */
public class SectorPerformDAO {

    /**
     * 
     */
    String query;
    
    /**
     * 
     * @param url
     */
    public SectorPerformDAO(String url){
        query = url;
    }
    
    /**
     * 
     * @return
     */
    public List<Sector> sectorQuery(){
        
        List<Sector> sectors = null;                                //
        
        sectors = SingleDaySectorParser.parseJson(new StringJsonURL(query).getResponse()).getSectors();
        
                                                                    //
        
        sectors = OneDaySector.getFiveBestAndWorstSectors(OneDaySector.sortSectors(sectors));
        
        return sectors;
    }
}
