package model;

public class Sector implements Comparable<Sector>{

    private String name;
    private Double value;
    
    /**
     * 
     * @param nameInput
     * @param valueInput
     */
    public Sector(String nameInput, Double valueInput){
        
        name = nameInput;
        value = valueInput;
    }
    
    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * 
     * @return
     */
    public Double getValue() {
        return value;
    }

    /**
     * 
     * @param value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Sector o) {
        
        return Double.compare(o.getValue(), this.getValue());
    }

}
