package lowfodmapv1.model;

/* 
This class represents an ingredient with properties like name, quantity, and unit.
 */

public class Ingredient {
    private String name;
    private double quantity;
    private String unit;


    // perhaps I need multiple constructors if some of the fields are missing from the json recipes file
    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Returns a formatted string representing the quantity and unit of the ingredient.
     * The quantity is formatted as a decimal with one digit after the decimal point,
     * and the unit is appended to the end of the string.
     * For example, if the quantity is 0.3 and the unit is "kg", the resulting string
     * would be "0.3kg".
     * @return The formatted string representing the quantity and unit of the ingredient.
     */
    public String getQuantityString() {
        return String.format("%.1f%s", quantity, unit);
    }
    

}


