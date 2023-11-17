package com.lowfodmapv1.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// This class represents a single special item with properties like name, brand, category, shop, price, and description.
@Document(collection = "SpecialItem")
public class SpecialItem {
    @Id
    private String id;
    private String name;
    private String brand;
    private String category;
    private String shop;
    private BigDecimal price;
    private String description;

    // Default constructor
    public SpecialItem() {
    }

    // Constructor with parameters
    public SpecialItem(String name, String brand, String category, String shop, String description, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.shop = shop;
        this.description = description;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method for easy logging
    @Override
    public String toString() {
        return "SpecialItems [id=" + id + ", name=" + name + ", brand=" + brand + ", category=" + category + ", shop="
                + shop + ", price=" + price + ", description=" + description + "]";
    }

}