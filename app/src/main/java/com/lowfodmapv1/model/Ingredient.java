package com.lowfodmapv1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* 
This class represents an ingredient with properties like name, quantity, and unit.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
    private String name;
    private double quantity;
    private String unit;

    public Ingredient() {
    }

    // perhaps I need multiple constructors if some of the fields are missing from
    // the json recipes file
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
        return unit != null ? unit : "";
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Returns a formatted string representing the quantity and unit of the
     * ingredient.
     * The quantity is formatted as a decimal with one digit after the decimal
     * point,
     * and the unit is appended to the end of the string.
     * For example, if the quantity is 0.3 and the unit is "kg", the resulting
     * string
     * would be "0.3kg".
     * 
     * @return The formatted string representing the quantity and unit of the
     *         ingredient.
     */
    public String getQuantityString() {
        return String.format("%.1f %s", quantity, unit);
    }

}
