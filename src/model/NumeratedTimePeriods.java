package model;

public enum NumeratedTimePeriods {

    YEARLY (0),
    HALF_YEARLY (1),
    QUARTERLY (2),
    MONTHLY (3),
    WEEKLY (4);
    
    private Integer value;
    
    NumeratedTimePeriods(Integer n ) {
     value = n;   
    }
    public Integer getValue() {
        return value;
    }
}
