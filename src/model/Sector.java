package model;

/**
 * Sector class modeling a stock sector with a name (e.g utilities) and a value stored as a double, usually used to describe
 * either a percentage or price. Implements Comparable so as to enable sorting on a Collection of Sector objects.
 * @author James Alexander
 *
 */
public class Sector implements Comparable<Sector>{

    private String name;
    private Double value;
    
    /**
     * 
     * @param nameInput     - name of object as a String
     * @param valueInput    - value of object as a Double (not a double primitive !)
     */
    public Sector(String nameInput, Double valueInput){
        
        name = nameInput;
        value = valueInput;
    }
    
    /**
     * Getter function for String name.
     * @return  String name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter function for String name.
     * @param name  -   name of object as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * Getter function for Double value.
     * @return  Double value
     */
    public Double getValue() {
        return value;
    }

    /**
     * Setter function for Double value
     * @param value -   value of object as a Double
     */
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Sector o) {
        
        return Double.compare(o.getValue(), this.getValue());
    }

}
