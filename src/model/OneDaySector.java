package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author james
 *
 */
public class OneDaySector {

    /**
     * 
     */
    List<Sector> sectors;
    
    
    /**
     * 
     * @param sectorInput
     */
    public OneDaySector(List<Sector> sectorInput){
        sectors = sectorInput;
        
    }
    
    
    /**
     * 
     * @return
     */
    public List<Sector> getSectors(){
        
        return sectors;
    }
    
    
    /**
     * 
     * @param list
     */
    public void setSectors(List<Sector> list){
        sectors = list;
    }
    
    
    /**
     * 
     * @param s
     */
    public void addSector(Sector s){
        sectors.add(s);
    }
    
    
    /**
     * 
     * @param unsortedSectors
     * @return
     */
    public static List<Sector> sortSectors(List<Sector> unsortedSectors){
        
        
         Collections.sort(unsortedSectors);
         return unsortedSectors;
    }
    
    /**
     * 
     * @param sortedSectors
     * @return
     */
    public static List<Sector> getFiveBestAndWorstSectors(List<Sector> sortedSectors){
        
        List<Sector> fiveSectors = new ArrayList<Sector>();
        
        fiveSectors.add(sortedSectors.get(0));
        fiveSectors.add(sortedSectors.get(1));
        fiveSectors.add(sortedSectors.get(2));
        fiveSectors.add(sortedSectors.get(sortedSectors.size() - 2));
        fiveSectors.add(sortedSectors.get(sortedSectors.size() - 1));
        
        return fiveSectors;
    }
}
