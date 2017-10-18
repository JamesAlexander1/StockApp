package model;

/**
 * Enumerated Type for time period intervals for graph creation and display.
 * @author James Alexander
 *
 */
public enum NumeratedTimePeriods {

    /**
     * 0. YEARLY
     */
    YEARLY (0),
    
    /**
     * 1. HALF_YEARLY
     */
    HALF_YEARLY (1),
    
    /**
     * 2. QUARTERLY
     */
    QUARTERLY (2),
    
    /**
     * 3. MONTHLY
     */
    MONTHLY (3),
    
    /**
     * 4. WEEKLY
     */
    WEEKLY (4);
    
    private Integer value;
    
    NumeratedTimePeriods(Integer n ) {
     value = n;   
    }
    
    /**
     * Function returning Integer corresponding to specific Time Period.
     * @return Integer value:    0-4
     */
    public Integer getValue() {
        return value;
    }
}
