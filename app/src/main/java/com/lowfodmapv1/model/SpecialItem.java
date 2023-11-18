package com.lowfodmapv1.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// This class represents a single special item with properties like name, brand, category, shop, price, and note.
@Document(collection = "SpecialItem")
public class SpecialItem {
    @Id
    private String id;
    private String name;
    private String brand;
    private String size;
    private String category;
    // private String shop;
    private ArrayList<String> shop;
    // private BigDecimal price;
    private ArrayList<BigDecimal> price;
    private String note;

    // Default constructor
    public SpecialItem() {
    }

    // Constructor with parameters
    public SpecialItem(String name, String brand, String size, String category, ArrayList<String> shop, String note,
            ArrayList<BigDecimal> price) {
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.category = category;
        this.shop = shop;
        this.note = note;
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getShop() {
        return shop;
    }

    public void setShop(ArrayList<String> shop) {
        this.shop = shop;
    }

    public ArrayList<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<BigDecimal> price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // toString method for easy logging
    @Override
    public String toString() {
        return "SpecialItems [id=" + id + ", name=" + name + ", brand=" + brand + ", size" + size + ", category="
                + category + ", shop=" + shop + ", price=" + price + ", note=" + note + "]";
    }

}